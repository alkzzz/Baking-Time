package com.example.administrator.bakingtime.ui;

import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;
import com.example.administrator.bakingtime.model.Step;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private List<Ingredient> mIngredientsList;
    private List<Step> mStepList;
    private static final String TAG_INGREDIENT_FRAGMENT = "DetailFragment";
    private static final String TAG_STEP_FRAGMENT = "StepFragment";
    private DetailFragment mDetailFragment;
    private LinearLayout mLinearLayout;
    private List<CheckBox> mChecboxList = new ArrayList<>();
    private boolean[] stateList;
    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle data = getIntent().getExtras();
        Recipe recipe = data.getParcelable("recipe");

        mIngredientsList = data.getParcelableArrayList("ingredients");
        mStepList = data.getParcelableArrayList("steps");

        mLinearLayout = (LinearLayout) findViewById(R.id.ll_ingredient_list);
        generateIngredientChecbox();
        if (mChecboxList.size() > 0) {
            for (int i = 0; i < mChecboxList.size(); i++) {
                mLinearLayout.addView(mChecboxList.get(i));
            }
        }

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(recipe.getName());

        isTwoPane = findViewById(R.id.step_two_pane) != null;

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            mDetailFragment = (DetailFragment) fragmentManager.findFragmentByTag(TAG_INGREDIENT_FRAGMENT);

            if(mDetailFragment == null) {
                mDetailFragment = new DetailFragment();
                mDetailFragment.setStepList(mStepList);

                fragmentManager.beginTransaction()
                        .add(R.id.step_list, mDetailFragment, null)
                        .commit();

                if (isTwoPane) {
                    StepFragment stepFragment = new StepFragment();
                    stepFragment.setStepList(mStepList);
                    stepFragment.setIndex(0);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.step_two_pane, stepFragment, TAG_STEP_FRAGMENT)
                            .commit();
                }
            }
        }
    }

    private void generateIngredientChecbox() {
        for (Ingredient ing: mIngredientsList) {
            String measure = ing.getMeasure();
            double quantity = ing.getQuantity();
            String ingredient = ing.getIngredient();
            CheckBox cbIngredients = new CheckBox(getApplicationContext());
            cbIngredients.setText(quantity+" "+measure+" of "+ingredient);
            cbIngredients.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            mChecboxList.add(cbIngredients);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        stateList = new boolean[mChecboxList.size()];
        for (int i = 0; i < mChecboxList.size(); i++) {
            Boolean isCheck = mChecboxList.get(i).isChecked();
            stateList[i] = isCheck;
        }
        outState.putBooleanArray("cb_state", stateList);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        stateList = savedInstanceState.getBooleanArray("cb_state");
        for (int i = 0; i < mChecboxList.size(); i++) {
            mChecboxList.get(i).setChecked(stateList[i]);
        }
    }
}
