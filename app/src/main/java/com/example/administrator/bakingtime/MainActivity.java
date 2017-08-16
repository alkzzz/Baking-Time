package com.example.administrator.bakingtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeFragment recipeFragment = new RecipeFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.recipe_container, recipeFragment, null)
                .commit();
    }
}
