package com.tim.newsfeed.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.tim.newsfeed.NewsFeed;

public class SharePreferenceRepository {
    private static SharePreferenceRepository ourInstance;

    public static SharePreferenceRepository getInstance() {
        if (ourInstance == null) {
            ourInstance = new SharePreferenceRepository();
        }
        return ourInstance;
    }

    public SharedPreferences getPreference(String name) {
        return NewsFeed.getAppContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public String getString(String name, String key, String defaultValue) {
        return getPreference(name).getString(key, defaultValue);
    }

    public Long getLong(String name, String key, long defaultValue) {
        return getPreference(name).getLong(key, defaultValue);
    }

    public void setString(String name, String key, String value) {
        getPreference(name).edit().putString(key, value).apply();
    }

    public void setLong(String name, String key, long value) {
        getPreference(name).edit().putLong(key, value).apply();
    }

    public <T extends Parcelable> void setParcelable(String name, String key, T object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        setString(name, key, json);
    }

    public <T extends Parcelable> T getParcelable(String name, String key, Class<T> cls) {
        Gson gson = new Gson();
        String json = getString(name, key, "");
        if (!TextUtils.isEmpty(json)) {
            return gson.fromJson(json, cls);
        } else {
            return null;
        }
    }

    public void removeConfig(String name, String key) {
        NewsFeed.getAppContext().getSharedPreferences(name, Context.MODE_PRIVATE).edit().remove(key).apply();
    }
}
