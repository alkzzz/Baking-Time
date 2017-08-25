package com.example.administrator.bakingtime.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;
import com.example.administrator.bakingtime.model.Step;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class DetailActivity extends AppCompatActivity {
    private List<Ingredient> mIngredientsList;
    private List<Step> mStepList;
    private static final String TAG_INGREDIENT_FRAGMENT = "DetailFragment";
    private static final String TAG_STEP_FRAGMENT = "StepFragment";
    private DetailFragment mDetailFragment;
    private LinearLayout mLinearLayout;
    private List<CheckBox> mChecboxList = new ArrayList<>();
    private boolean[] stateList;
    private Realm realm;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        int recipe_id = getIntent().getIntExtra("recipe_id", 0);

        realm = Realm.getDefaultInstance();

        recipe = realm.where(Recipe.class).equalTo("id", recipe_id).findFirst();

        ImageView recipeImage = (ImageView) findViewById(R.id.iv_recipe_image);
        TextView servingText = (TextView) findViewById(R.id.tv_recipe_servings);

        String image = recipe.getImage();
        if (!TextUtils.isEmpty(image)) {
            recipeImage.setVisibility(View.VISIBLE);
            Picasso.with(this)
                    .load(image)
                    .into(recipeImage);
        }

        servingText.setText(getString(R.string.servings, recipe.getServings()));

        mIngredientsList = recipe.getIngredients();
        mStepList = recipe.getSteps();

        mLinearLayout = (LinearLayout) findViewById(R.id.ll_ingredient_list);
        generateIngredientChecbox();
        if (mChecboxList.size() > 0) {
            for (int i = 0; i < mChecboxList.size(); i++) {
                mLinearLayout.addView(mChecboxList.get(i));
            }
        }

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(recipe.getName());
        actionbar.setDisplayHomeAsUpEnabled(true);

        boolean isTwoPane = findViewById(R.id.step_two_pane).getVisibility() != View.GONE;

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            mDetailFragment = (DetailFragment) fragmentManager.findFragmentByTag(TAG_INGREDIENT_FRAGMENT);

            if(mDetailFragment == null) {
                mDetailFragment = new DetailFragment();
                mDetailFragment.setRecipeId(recipe_id);
                mDetailFragment.setStepList(mStepList);

                fragmentManager.beginTransaction()
                        .add(R.id.step_list, mDetailFragment, null)
                        .commit();

                if (isTwoPane) {
                    StepFragment stepFragment = new StepFragment();
                    stepFragment.setStepList(mStepList);
                    stepFragment.setIndex(0);
                    fragmentManager.beginTransaction()
                            .add(R.id.step_two_pane, stepFragment, TAG_STEP_FRAGMENT)
                            .commit();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.wiget_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_to_widget:
                addToWidget();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addToWidget() {
        SharedPreferences sharedPref = getSharedPreferences("WIDGET_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("recipe_id", recipe.getId());
        editor.apply();

        Toast.makeText(this, "Add "+recipe.getName()+" to Widget", Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        intent.setAction("com.example.administrator.bakingtime.ADD_TO_WIDGET");
        sendBroadcast(intent);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
