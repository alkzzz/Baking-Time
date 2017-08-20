package com.example.administrator.bakingtime.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;
import com.example.administrator.bakingtime.network.RecipeJson;

import java.util.List;

public class IngredientProvider implements RemoteViewsService.RemoteViewsFactory, RecipeJson.RecipeCallback {
    List<Ingredient> mIngredientList;
    Context mContext = null;
    Intent mIntent;

    private static final String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    public IngredientProvider(Context context, Intent intent) {
        mContext = context;
        mIntent = intent;
    }

    @Override
    public void onCreate() {
        RecipeJson recipeJson = new RecipeJson(url, this);
        recipeJson.fetchRecipeData();
    }


    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mIngredientList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        return null;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public void onSuccess(List<Recipe> recipes) {
        mIngredientList = recipes.get(0).getIngredients();
    }
}
