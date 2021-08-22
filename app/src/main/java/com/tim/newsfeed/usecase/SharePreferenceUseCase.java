package com.tim.newsfeed.usecase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tim.newsfeed.pojo.News;
import com.tim.newsfeed.pojo.Provider;
import com.tim.newsfeed.repository.SharePreferenceRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class SharePreferenceUseCase {

    public <T> void saveList(String prefName, String key, List<T> targetList) {
        SharePreferenceRepository.getInstance().setString(prefName, key, new Gson().toJson(targetList));
    }

    public List<Provider> getProviderList(String prefName, String key) {
        String data = SharePreferenceRepository.getInstance().getString(prefName, key, null);
        if (data != null) {
            Type listType = new TypeToken<List<Provider>>() {}.getType();
            return new Gson().fromJson(data, listType);
        } else {
            return new ArrayList<>();
        }
    }

    public List<News> getHistoryList(String prefName, String key) {
        String data = SharePreferenceRepository.getInstance().getString(prefName, key, null);
        if (data != null) {
            Type listType = new TypeToken<List<News>>() {}.getType();
            return new Gson().fromJson(data, listType);
        } else {
            return new ArrayList<>();
        }
    }
}
