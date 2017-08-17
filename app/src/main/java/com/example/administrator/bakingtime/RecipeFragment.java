package com.example.administrator.bakingtime;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.bakingtime.adapter.RecipeAdapter;
import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

public class RecipeFragment extends Fragment implements RecipeAdapter.OnItemClickListener {
    private static final String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private Moshi moshi;
    private RecipeAdapter mRecipeAdapter;
    private RecyclerView mRecyclerView;

    public RecipeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recipe);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        try {
            getRecipe();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    private void getRecipe() throws IOException {
        OkHttpClient client = new OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                final String jsonResponse = response.body().string();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        moshi = new Moshi.Builder().build();
                        Type type = Types.newParameterizedType(List.class, Recipe.class);
                        JsonAdapter<List<Recipe>> adapter = moshi.adapter(type);
                        try {
                            List<Recipe> recipes = adapter.fromJson(jsonResponse);
                            if (recipes != null) {
                                List<Recipe> mRecipeList = new ArrayList<>();
                                mRecipeList.addAll(recipes);
                                mRecipeAdapter = new RecipeAdapter(mRecipeList, RecipeFragment.this);
                                mRecyclerView.setAdapter(mRecipeAdapter);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void OnItemClick(Recipe recipe) {
        Log.d("Recipe", recipe.getName());
        for (Ingredient ing: recipe.getIngredients()) {
            Log.d("Recipe", ing.getIngredient());
        }
        Intent intent = new Intent(getActivity().getApplicationContext(), DetailActivity.class);
        intent.putExtra("recipe", recipe);
        getActivity().startActivity(intent);
    }
}
