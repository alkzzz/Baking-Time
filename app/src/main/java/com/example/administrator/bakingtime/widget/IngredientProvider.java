package com.example.administrator.bakingtime.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;
import com.example.administrator.bakingtime.network.RecipeJson;

import java.util.ArrayList;
import java.util.List;

public class IngredientProvider implements RemoteViewsService.RemoteViewsFactory, RecipeJson.RecipeCallback {
//    List<Ingredient> mIngredientList;
    List<String> mStrings = new ArrayList<>();
    Context mContext = null;

    private static final String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    public IngredientProvider(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        initData();
//        RecipeJson recipeJson = new RecipeJson(url, this);
//        recipeJson.fetchRecipeData();
    }

    private void initData() {
        mStrings.clear();
        for (int i = 1; i < 11; i++) {
            mStrings.add("List item "+i);
        }
    }


    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mStrings.size();
//        return mIngredientList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
//        String measure = mIngredientList.get(position).getMeasure();
//        double quantity = mIngredientList.get(position).getQuantity();
//        String ingredient = mIngredientList.get(position).getIngredient();
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), android.R.layout.simple_list_item_1);
        remoteViews.setTextViewText(android.R.id.text1, mStrings.get(position));
//        remoteViews.setTextViewText(android.R.id.text1, quantity+" "+measure+" of "+ingredient);
        return remoteViews;
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
//        mIngredientList = recipes.get(0).getIngredients();
    }
}
