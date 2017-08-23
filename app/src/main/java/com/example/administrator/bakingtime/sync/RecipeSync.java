package com.example.administrator.bakingtime.sync;


import android.content.Context;
import android.content.Intent;

import com.example.administrator.bakingtime.model.Recipe;

import io.realm.Realm;

public class RecipeSync {

    private static boolean sInitialized;

    synchronized public static void initialize(final Context context) {

        if (sInitialized) return;

        sInitialized = true;

        Realm realm = Realm.getDefaultInstance();

        long count = realm.where(Recipe.class).count();

        if (count == 0) {
            startSync(context);
        }

        realm.close();

    }

    private static void startSync(Context context) {
        Intent intent = new Intent(context, RecipeSyncIntentService.class);
        context.startService(intent);
    }


}
