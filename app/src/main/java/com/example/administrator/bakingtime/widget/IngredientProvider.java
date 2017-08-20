package com.example.administrator.bakingtime.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.administrator.bakingtime.model.Ingredient;

import java.util.List;

public class IngredientProvider implements RemoteViewsService.RemoteViewsFactory {
    List<Ingredient> mIngredientList;
    Context mContext;
    Intent mIntent;

    public IngredientProvider(List<Ingredient> ingredientList, Context context, Intent intent) {
        mIngredientList = ingredientList;
        mContext = context;
        mIntent = intent;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 0;
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
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
