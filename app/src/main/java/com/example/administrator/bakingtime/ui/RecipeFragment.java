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
import com.example.administrator.bakingtime.adapter.RecipeRealmAdapter;
import com.example.administrator.bakingtime.model.Recipe;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class RecipeFragment extends Fragment implements RecipeRealmAdapter.OnItemClickListener {
    private Realm realm;
    private RealmResults<Recipe> recipes;
    private RecyclerView mRecyclerView;
    private RecipeRealmAdapter recipeRealmAdapter;

    public RecipeFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);

        realm = Realm.getDefaultInstance();

        recipes = realm.where(Recipe.class).findAll();
        if (recipes.size() > 0) {
            setupRecyclerView(rootView);
        } else {
            recipes.addChangeListener(new RealmChangeListener<RealmResults<Recipe>>() {
                @Override
                public void onChange(RealmResults<Recipe> recipes) {
                    setupRecyclerView(rootView);
                }
            });
        }

        return rootView;
    }

    private void setupRecyclerView(View view) {
        recipeRealmAdapter = new RecipeRealmAdapter(recipes, true, this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_recipe);
        mRecyclerView.setHasFixedSize(true);

        if (getString(R.string.layout_type).equals("default")) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
        }
        mRecyclerView.setAdapter(recipeRealmAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRecyclerView.setAdapter(null);
        realm.close();
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(getActivity().getApplicationContext(), DetailActivity.class);
        int recipe_id = recipes.get(position).getId();
        intent.putExtra("recipe_id", recipe_id);
        startActivity(intent);
    }
}
