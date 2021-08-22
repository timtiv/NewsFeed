package com.tim.newsfeed.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tim.newsfeed.mock.Generator;
import com.tim.newsfeed.pojo.Provider;
import com.tim.newsfeed.usecase.SharePreferenceUseCase;
import com.tim.newsfeed.util.Constant;

import java.util.List;

public class ProviderRepository {
    private static final ProviderRepository ourInstance = new ProviderRepository();

    public static ProviderRepository getInstance() {
        return ourInstance;
    }

    private ProviderRepository() {
        providerList = Generator.getProviderList();
        subscribeList = new SharePreferenceUseCase().getProviderList(Constant.PREF_USER_SETTING, Constant.PREF_KEY_SUBSCRIBE_PROVIDER);
        Log.d("Tim", "subscribe list = " + subscribeList.size());
        mergeSubscribeData();
        providerLiveData = new MutableLiveData<>(providerList);
        subscribeLiveData = new MutableLiveData<>(subscribeList);
    }

    MutableLiveData<List<Provider>> providerLiveData;
    MutableLiveData<List<Provider>> subscribeLiveData;
    List<Provider> subscribeList;
    List<Provider> providerList;

    public LiveData<List<Provider>> getProviderLiveData() {
        return providerLiveData;
    }

    public LiveData<List<Provider>> getSubscribeLiveData() {
        return subscribeLiveData;
    }

    private void saveSubscribeList() {
        if (subscribeList != null) {
            Log.d("Tim", "saveSubscribeList subscribe list = " + subscribeList.size());
            new SharePreferenceUseCase().saveList(Constant.PREF_USER_SETTING, Constant.PREF_KEY_SUBSCRIBE_PROVIDER, subscribeList);
        }
    }

    public void subscribe(Provider provider) {
        if (subscribeList != null && !subscribeList.contains(provider)) {
            subscribeList.add(provider);
            saveSubscribeList();
            subscribeLiveData.postValue(subscribeList);
        }
    }

    public void unSubscribe(Provider provider) {
        if (subscribeList != null) {
            subscribeList.remove(provider);
            saveSubscribeList();
            subscribeLiveData.postValue(subscribeList);
        }
    }

    private void mergeSubscribeData() {
        for (Provider provider : providerList) {
            provider.setSubscribed(subscribeList.contains(provider));
        }
    }

}
