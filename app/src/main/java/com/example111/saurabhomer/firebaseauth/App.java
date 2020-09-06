package com.example111.saurabhomer.firebaseauth;

import android.app.Application;
import android.content.Context;

import com.example111.saurabhomer.firebaseauth.DB.AppDatabase;

public class App extends Application {

    public static App appContext;

    @Override public void onCreate() {
        super.onCreate();
        appContext = this;
        AppDatabase.getInstance(appContext);
    }
}
