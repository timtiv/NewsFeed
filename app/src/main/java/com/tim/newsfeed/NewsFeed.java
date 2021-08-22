package com.tim.newsfeed;

import android.app.Application;
import android.content.Context;

public class NewsFeed extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        NewsFeed.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return NewsFeed.context;
    }
}
