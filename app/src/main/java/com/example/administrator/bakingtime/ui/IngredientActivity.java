package com.example.administrator.bakingtime.ui;

import android.support.v4.app.FragmentManager;
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
    private static final String TAG_INGREDIENT_FRAGMENT = "IngredientFragment";
    private IngredientFragment mIngredientFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        Bundle data = getIntent().getExtras();
        Recipe recipe = data.getParcelable("recipe");

        mIngredientsList = data.getParcelableArrayList("ingredients");
        mStepList = data.getParcelableArrayList("steps");

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(recipe.getName());

        FragmentManager fragmentManager = getSupportFragmentManager();
        mIngredientFragment = (IngredientFragment) fragmentManager.findFragmentByTag(TAG_INGREDIENT_FRAGMENT);

        if(mIngredientFragment == null) {
            mIngredientFragment = new IngredientFragment();
            mIngredientFragment.setIngredientList(mIngredientsList);
            mIngredientFragment.setStepList(mStepList);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ingredient_container, mIngredientFragment, null)
                    .commit();
        }
    }

}
