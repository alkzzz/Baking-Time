package com.example.administrator.bakingtime.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.bakingtime.R;


public class MainActivity extends AppCompatActivity {
    private static final String TAG_RECIPE_FRAGMENT = "RecipeFragment";

    private RecipeFragment mRecipeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the retained fragment on activity restarts
        FragmentManager fm = getSupportFragmentManager();
        mRecipeFragment = (RecipeFragment) fm.findFragmentByTag(TAG_RECIPE_FRAGMENT);

        // create the fragment and data the first time
        if (mRecipeFragment == null) {
            // add the fragment
            mRecipeFragment = new RecipeFragment();
            fm.beginTransaction().add(mRecipeFragment, TAG_RECIPE_FRAGMENT).commit();
        }

    }
}
