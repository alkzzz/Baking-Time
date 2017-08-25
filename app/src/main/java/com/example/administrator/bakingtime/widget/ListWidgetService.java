package com.example.administrator.bakingtime.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;

import java.util.List;

import io.realm.Realm;

public class ListWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListWidgetFactory(this.getApplicationContext());
    }

    private class ListWidgetFactory implements RemoteViewsFactory {
        private Context mContext;
        private List<Ingredient> mIngredientList;

        public ListWidgetFactory(Context context) {
            mContext = context;
        }

        @Override
        public void onCreate() {
            setIngredientListData();
        }

        @Override
        public void onDataSetChanged() {
            setIngredientListData();
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
            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.listview_recipe);
            String measure = mIngredientList.get(position).getMeasure();
            double quantity = mIngredientList.get(position).getQuantity();
            String ingredient = mIngredientList.get(position).getIngredient();

            views.setTextViewText(R.id.textview_ingredient, quantity+" "+measure+" of "+ingredient);
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
            return false;
        }

        private void setIngredientListData() {
            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("WIDGET_PREF", 0);
            int recipe_id = sharedPref.getInt("recipe_id", 1);

            Realm realm = Realm.getDefaultInstance();

            mIngredientList = realm.copyFromRealm(realm.where(Recipe.class)
                    .equalTo("id", recipe_id).findFirst().getIngredients());

            realm.close();
        }
    }
}
