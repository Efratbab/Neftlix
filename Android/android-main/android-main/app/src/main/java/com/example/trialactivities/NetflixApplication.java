package com.example.trialactivities;

import android.app.Application;
import android.content.Context;

public class NetflixApplication extends Application {
    private static NetflixApplication instance;

    public static NetflixApplication getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Application instance is null! Ensure it's registered in AndroidManifest.xml");
        }
        return instance;
    }

    public static Context getAppContext() {
        return getInstance().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;  // ✅ Assign instance here
    }
}
