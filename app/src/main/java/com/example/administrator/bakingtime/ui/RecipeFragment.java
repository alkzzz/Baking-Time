package com.example.administrator.bakingtime.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.adapter.RecipeAdapter;
import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;
import com.example.administrator.bakingtime.model.Step;
import com.example.administrator.bakingtime.network.RecipeJson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeFragment extends Fragment implements RecipeAdapter.OnItemClickListener, RecipeJson.RecipeCallback {
    private RecipeAdapter mRecipeAdapter;
    private RecyclerView mRecyclerView;

    public RecipeFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recipe);
        mRecyclerView.setHasFixedSize(true);
        if (getString(R.string.layout_type).equals("default")) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
        }

        return rootView;
    }

    @Override
    public void OnItemClick(Recipe recipe) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient ingredient: recipe.getIngredients()) {
            ingredients.add(ingredient);
        }
        List<Step> steps = new ArrayList<>();
        for (Step step: recipe.getSteps()) {
            steps.add(step);
        }
        Intent intent = new Intent(getActivity().getApplicationContext(), DetailActivity.class);
        intent.putExtra("recipe", recipe);
        intent.putParcelableArrayListExtra("ingredients", (ArrayList<? extends Parcelable>) ingredients);
        intent.putParcelableArrayListExtra("steps", (ArrayList<? extends Parcelable>) steps);
        getActivity().startActivity(intent);
    }

    @Override
    public void onSuccess(final List<Recipe> recipes) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecipeAdapter = new RecipeAdapter(recipes, RecipeFragment.this);
                mRecyclerView.setAdapter(mRecipeAdapter);
            }
        });
    }
}
