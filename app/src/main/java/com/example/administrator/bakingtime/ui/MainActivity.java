package com.example.administrator.bakingtime.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Recipe;
import com.example.administrator.bakingtime.network.RecipeJson;

import java.util.List;


public class MainActivity extends AppCompatActivity implements RecipeJson.RecipeCallback {
    private static final String URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private static final String TAG_RECIPE_FRAGMENT = "RecipeFragment";
    private List<Recipe> mRecipeList;
    private RecipeFragment mRecipeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            mRecipeFragment = (RecipeFragment) fragmentManager.findFragmentByTag(TAG_RECIPE_FRAGMENT);

            if (mRecipeFragment == null) {
                mRecipeFragment = new RecipeFragment();
                fragmentManager.beginTransaction().add(mRecipeFragment, TAG_RECIPE_FRAGMENT).commit();
            }
        }

//        RecipeJson recipeJson = new RecipeJson(URL, this);
//        recipeJson.fetchRecipeData();
    }

    @Override
    public void onSuccess(List<Recipe> recipes) {
//        mRecipeList = recipes;
    }

}
