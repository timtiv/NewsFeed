package com.tim.newsfeed.usecase;

import androidx.lifecycle.LiveData;

import com.tim.newsfeed.pojo.Provider;
import com.tim.newsfeed.repository.ProviderRepository;

import java.util.List;

public class ProviderUseCase {
    public LiveData<List<Provider>> getProviderListLiveData() {
        return ProviderRepository.getInstance().getProviderLiveData();
    }

    public LiveData<List<Provider>> getSubscribeListLiveData() {
        return ProviderRepository.getInstance().getSubscribeLiveData();
    }

    public void subscribe(Provider provider) {
        ProviderRepository.getInstance().subscribe(provider);
    }

    public void unSubscribe(Provider provider) {
        ProviderRepository.getInstance().unSubscribe(provider);
    }

}
