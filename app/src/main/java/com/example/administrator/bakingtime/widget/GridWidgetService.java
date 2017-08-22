package com.example.administrator.bakingtime.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Recipe;

import java.util.List;

public class GridWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        //List<Recipe> recipes = intent.getParcelableArrayListExtra("recipes");
//        return new GridRemoteViewsFactory(this.getApplicationContext(), recipes);
        return null;
    }

    class GridRemoteViewsFactory implements RemoteViewsFactory {
        Context context;
        List<Recipe> recipeList;

        public GridRemoteViewsFactory(Context context, List<Recipe> recipeList) {
            this.context = context;
            this.recipeList = recipeList;
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
            return recipeList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
            views.setTextViewText(R.id.textview_recipe ,recipeList.get(position).getName());
            return views;
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
            return true;
        }
    }
}
