package com.example.administrator.bakingtime.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Recipe;

import java.util.List;

import io.realm.Realm;

public class GridWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Realm realm = Realm.getDefaultInstance();
        List<Recipe> recipeList = realm.copyFromRealm(realm.where(Recipe.class).findAll());
        return new GridRemoteViewsFactory(this.getApplicationContext(), realm, recipeList);
    }

    class GridRemoteViewsFactory implements RemoteViewsFactory {
        Context context;
        Realm realm;
        List<Recipe> recipeList;

        public GridRemoteViewsFactory(Context context, Realm realm, List<Recipe> recipeList) {
            this.context = context;
            this.realm = realm;
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
            realm.close();
        }

        @Override
        public int getCount() {
            return recipeList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            int recipe_id = recipeList.get(position).getId();

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_list_view);
            views.setTextViewText(R.id.textview_recipe, recipeList.get(position).getName());

            Bundle bundle = new Bundle();
            bundle.putInt("recipe_id", recipe_id);
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(bundle);
            views.setOnClickFillInIntent(R.id.textview_recipe, fillInIntent);

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
