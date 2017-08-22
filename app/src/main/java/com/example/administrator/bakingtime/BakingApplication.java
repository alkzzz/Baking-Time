package com.example.administrator.bakingtime;

import android.app.Application;
import io.realm.Realm;

public class BakingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
