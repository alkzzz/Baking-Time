package com.example.administrator.bakingtime.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;
import com.example.administrator.bakingtime.ui.DetailActivity;

import java.util.List;

import io.realm.Realm;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);

        Realm realm = Realm.getDefaultInstance();

        SharedPreferences sharedPref = context.getSharedPreferences("WIDGET_PREF", 0);
        int recipe_id = sharedPref.getInt("recipe_id", 1);

        Recipe recipe = realm.copyFromRealm(realm.where(Recipe.class).equalTo("id", recipe_id).findFirst());

        views.setTextViewText(R.id.widget_recipe_name, recipe.getName());

        StringBuilder stringBuilder = new StringBuilder();
        for (Ingredient ing: recipe.getIngredients()) {
            String measure = ing.getMeasure();
            double quantity = ing.getQuantity();
            String ingredient = ing.getIngredient();
            stringBuilder.append(quantity+" "+measure+" of "+ingredient);
            stringBuilder.append("\n\n");
        }

        views.setTextViewText(R.id.widget_ingredient, stringBuilder.toString());

        appWidgetManager.updateAppWidget(appWidgetId, views);

        realm.close();
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context.getApplicationContext());
        ComponentName thisWidget = new ComponentName(context.getApplicationContext(), RecipeWidget.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        if (appWidgetIds != null && appWidgetIds.length > 0) {
            onUpdate(context, appWidgetManager, appWidgetIds);
        }
    }
}

