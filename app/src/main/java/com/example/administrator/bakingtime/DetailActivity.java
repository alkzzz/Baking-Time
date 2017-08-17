package com.example.administrator.bakingtime;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private static ArrayList<com.example.administrator.bakingtime.model.Step> sStepList;
    private static ArrayList<Ingredient> sIngredientsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle data = getIntent().getExtras();
        Recipe recipe = data.getParcelable("recipe");
        sIngredientsList = data.getParcelableArrayList("ingredients");
        sStepList = data.getParcelableArrayList("steps");

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(recipe.getName());

        ViewPager recipePager = (ViewPager) findViewById(R.id.recipePager);
        FragmentPagerAdapter adapterViewPager = new RecipePagerAdapter(getSupportFragmentManager());
        recipePager.setAdapter(adapterViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.recipeTab);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(recipePager);

    }

    public static class RecipePagerAdapter extends FragmentPagerAdapter {

        public RecipePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return IngredientFragment.newInstance(sIngredientsList);
                default:
                    return StepFragment.newInstance(sStepList);
            }
        }

        @Override
        public int getCount() {
            return sStepList.size(); // Page for step + 1 for Ingredient Page
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
