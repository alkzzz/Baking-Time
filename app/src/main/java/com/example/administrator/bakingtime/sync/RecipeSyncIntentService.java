package com.example.administrator.bakingtime.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class RecipeSyncIntentService extends IntentService {

    public RecipeSyncIntentService() {
        super("RecipeSyncIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        RecipeSyncTask.syncRecipe(this);
    }
}
