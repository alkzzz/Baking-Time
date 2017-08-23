package com.example.administrator.bakingtime.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.sync.RecipeSync;


public class MainActivity extends AppCompatActivity {
    private static final String TAG_RECIPE_FRAGMENT = "RecipeFragment";
    private RecipeFragment mRecipeFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeSync.initialize(this);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            mRecipeFragment = (RecipeFragment) fragmentManager.findFragmentByTag(TAG_RECIPE_FRAGMENT);

            if (mRecipeFragment == null) {
                mRecipeFragment = new RecipeFragment();
                fragmentManager.beginTransaction().add(mRecipeFragment, TAG_RECIPE_FRAGMENT).commit();
            }
        }
    }

}
