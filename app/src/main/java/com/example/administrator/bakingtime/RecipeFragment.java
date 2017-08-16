package com.example.administrator.bakingtime;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.bakingtime.adapter.RecipeAdapter;
import com.example.administrator.bakingtime.model.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class RecipeFragment extends Fragment {
    private RecipeAdapter mRecipeAdapter;
    private RecyclerView mRecyclerView;
    private List<Recipe> mRecipeList;
    private List<Recipe.Ingredient> mIngredientList;
    private List<Recipe.Step> mStepList;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recipe);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

        mRecipeList = new ArrayList<>();
        mIngredientList = new ArrayList<>();
        mStepList = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject recipeObj = response.getJSONObject(i);
                                JSONArray ingredientsArray = recipeObj.getJSONArray("ingredients");
                                for (int j = 0; j < ingredientsArray.length(); j++) {
                                    JSONObject ingredientObj = ingredientsArray.getJSONObject(i);
                                    Recipe.Ingredient ingredient = Recipe.Ingredient.builder()
                                            .quantity(ingredientObj.getInt("quantity"))
                                            .measure(ingredientObj.getString("measure"))
                                            .ingredient(ingredientObj.getString("ingredient"))
                                            .build();
                                    mIngredientList.add(ingredient);
                                }
                                JSONArray stepsArray = recipeObj.getJSONArray("steps");
                                for (int k = 0; k < stepsArray.length(); k++) {
                                    JSONObject stepObj = stepsArray.getJSONObject(i);
                                    Recipe.Step step = Recipe.Step.builder()
                                            .id(stepObj.getInt("id"))
                                            .shortDescription(stepObj.getString("shortDescription"))
                                            .description(stepObj.getString("description"))
                                            .videoURL(stepObj.getString("videoURL"))
                                            .thumbnailURL(stepObj.getString("thumbnailURL"))
                                            .build();
                                    mStepList.add(step);
                                }
                                Recipe recipe = Recipe.builder()
                                        .id(recipeObj.getInt("id"))
                                        .name(recipeObj.getString("name"))
                                        .servings(recipeObj.getInt("servings"))
                                        .image(recipeObj.getString("image"))
                                        .ingredients(mIngredientList)
                                        .steps(mStepList)
                                        .build();
                                mRecipeList.add(recipe);
                            }
                            mRecipeAdapter = new RecipeAdapter(mRecipeList);
                            mRecyclerView.setAdapter(mRecipeAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsonArrayRequest);

        return rootView;
    }
}
