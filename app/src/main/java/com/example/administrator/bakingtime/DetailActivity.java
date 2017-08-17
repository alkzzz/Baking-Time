package com.example.administrator.bakingtime;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.bakingtime.model.Recipe;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private static List<Recipe.Step> sStepList;
    private static List<Recipe.Ingredient> sIngredientsList;
    private int pageCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle data = getIntent().getExtras();
        Recipe recipe = data.getParcelable("recipe");

        sIngredientsList = recipe.getIngredients();

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(recipe.getName());

        ViewPager recipePager = (ViewPager) findViewById(R.id.recipePager);
        FragmentPagerAdapter adapterViewPager = new RecipePagerAdapter(getSupportFragmentManager(), pageCount);
        recipePager.setAdapter(adapterViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.recipeTab);
        tabLayout.setupWithViewPager(recipePager);

    }

    public static class RecipePagerAdapter extends FragmentPagerAdapter {
        private int NUM_COUNT;

        public RecipePagerAdapter(FragmentManager fm, int pageCount) {
            super(fm);
            NUM_COUNT = pageCount;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return IngredientFragment.newInstance(sIngredientsList);
                default:
                    return IngredientFragment.newInstance(sIngredientsList);
            }
        }

        @Override
        public int getCount() {
            return NUM_COUNT + 1; // Page for step + 1 for Ingredient Page
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Ingredients";
            } else {
                return "Step "+ position;
            }

        }
    }
}
