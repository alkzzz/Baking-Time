package com.example.administrator.bakingtime.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;
import com.example.administrator.bakingtime.model.Step;

import java.util.List;

public class IngredientActivity extends AppCompatActivity {
    private List<Ingredient> mIngredientsList;
    private List<Step> mStepList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        Bundle data = getIntent().getExtras();
        Recipe recipe = data.getParcelable("recipe");

        mIngredientsList = data.getParcelableArrayList("ingredients");
        mStepList = data.getParcelableArrayList("steps");

        IngredientFragment ingredientFragment = new IngredientFragment();
        ingredientFragment.setIngredientList(mIngredientsList);
        ingredientFragment.setStepList(mStepList);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(recipe.getName());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.ingredient_container, ingredientFragment, null)
                .commit();
    }

}
