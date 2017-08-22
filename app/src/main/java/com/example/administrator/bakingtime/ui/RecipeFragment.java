package com.example.administrator.bakingtime.ui;


import android.content.Intent;
import android.os.Bundle;
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
import com.example.administrator.bakingtime.model.Recipe;

import org.parceler.Parcels;
import java.util.List;

import io.realm.Realm;


public class RecipeFragment extends Fragment implements RecipeAdapter.OnItemClickListener {
    private List<Recipe> mRecipeList;

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

        Realm realm = Realm.getDefaultInstance();

        mRecipeList = realm.where(Recipe.class).findAll();

        if (mRecipeList != null) {
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recipe);
            recyclerView.setHasFixedSize(true);

            if (getString(R.string.layout_type).equals("default")) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
            }
            RecipeAdapter recipeAdapter = new RecipeAdapter(mRecipeList, this);
            recyclerView.setAdapter(recipeAdapter);
        }

        return rootView;
    }

    @Override
    public void OnItemClick(Recipe recipe) {
        Intent intent = new Intent(getActivity().getApplicationContext(), DetailActivity.class);
        intent.putExtra("recipe", Parcels.wrap(recipe));
        getActivity().startActivity(intent);
    }

    public void setRecipeList(List<Recipe> recipeList) {
        mRecipeList = recipeList;
    }
}
