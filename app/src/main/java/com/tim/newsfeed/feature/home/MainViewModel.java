package com.tim.newsfeed.feature.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tim.newsfeed.pojo.Category;
import com.tim.newsfeed.pojo.Condition;
import com.tim.newsfeed.pojo.News;
import com.tim.newsfeed.pojo.Provider;
import com.tim.newsfeed.usecase.CategoryUseCase;
import com.tim.newsfeed.usecase.NewsUseCase;
import com.tim.newsfeed.usecase.ProviderUseCase;
import com.tim.newsfeed.util.Event;

import java.util.List;

public class MainViewModel extends ViewModel {

    public MainViewModel() {

    }

    public LiveData<List<News>> getNewsLiveData() {
        return new NewsUseCase().getNewsListLiveData();
    }

    public LiveData<List<Category>> getCategoryLiveData() {
        return new CategoryUseCase().getCategoryLiveData();
    }

    public LiveData<List<Provider>> getProviderLiveData() {
        return new ProviderUseCase().getProviderListLiveData();
    }

    public void categoryControl(Category category) {
        if (category.isSelected()) {
            new NewsUseCase().addCategory(category);
        } else {
            new NewsUseCase().removeCategory(category);
        }
    }

    public void providerControl(Provider provider) {
        if (provider.isSelected()) {
            new NewsUseCase().addProvider(provider);
        } else {
            new NewsUseCase().removeProvider(provider);
        }
    }

    public void providerSubscribeControl(Provider provider) {
        if (provider.isSubscribed()) {
            new ProviderUseCase().subscribe(provider);
        } else {
            new ProviderUseCase().unSubscribe(provider);
        }
    }

}
