package com.example.administrator.bakingtime.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.bakingtime.BakingApplication;
import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.sync.RecipeSync;


public class MainActivity extends AppCompatActivity {
    private static final String TAG_RECIPE_FRAGMENT = "RecipeFragment";
    private RecipeFragment mRecipeFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isConnected()) RecipeSync.initialize(this);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            mRecipeFragment = (RecipeFragment) fragmentManager.findFragmentByTag(TAG_RECIPE_FRAGMENT);

            if (mRecipeFragment == null) {
                mRecipeFragment = new RecipeFragment();
                fragmentManager.beginTransaction().add(mRecipeFragment, TAG_RECIPE_FRAGMENT).commit();
            }
        }
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
