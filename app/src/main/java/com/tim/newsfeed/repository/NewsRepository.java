package com.tim.newsfeed.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.common.collect.ComparisonChain;
import com.tim.newsfeed.mock.Generator;
import com.tim.newsfeed.pojo.Category;
import com.tim.newsfeed.pojo.News;
import com.tim.newsfeed.pojo.Provider;
import com.tim.newsfeed.usecase.ProviderUseCase;
import com.tim.newsfeed.usecase.SharePreferenceUseCase;
import com.tim.newsfeed.util.Constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsRepository {
    private static final NewsRepository ourInstance = new NewsRepository();

    public static NewsRepository getInstance() {
        return ourInstance;
    }

    private NewsRepository() {
        newsList = Generator.getNewsList();
        Collections.sort(newsList, (news, t1) -> {
            if (news.getCreateDate().before(t1.getCreateDate())) {
                return 1;
            } else if (news.getCreateDate().after(t1.getCreateDate())) {
                return -1;
            } else {
                return 0;
            }
        });
        newsListLiveData = new MutableLiveData<>(newsList);
        categoryList = new ArrayList<>();
        providerList = new ArrayList<>();
        new ProviderUseCase().getSubscribeListLiveData().observeForever(providers -> {
            subscribeList = providers;
            sortSubscribe();
        });
        historyList = new SharePreferenceUseCase().getHistoryList(Constant.PREF_USER_SETTING, Constant.PREF_KEY_NEWS_HISTORY);
        historyNewsListData = new MutableLiveData<>(historyList);
    }

    MutableLiveData<List<News>> newsListLiveData;
    List<News> newsList;

    MutableLiveData<List<News>> historyNewsListData;
    List<News> historyList;

    public LiveData<List<News>> getNewsListLiveData() {
        return newsListLiveData;
    }

    public LiveData<List<News>> getHistoryNewsLiveData() {
        return historyNewsListData;
    }

    List<Category> categoryList;
    List<Provider> providerList;
    List<Provider> subscribeList;

    public void addFilter(Category category) {
        categoryList.add(category);
        filter();
    }

    public void removeFilter(Category category) {
        categoryList.remove(category);
        filter();
    }

    public void addFilter(Provider category) {
        providerList.add(category);
        filter();
    }

    public void removeFilter(Provider category) {
        providerList.remove(category);
        filter();
    }

    public void sortSubscribe() {
        List<News> list = newsListLiveData.getValue();
        Collections.sort(list, (news, t1) -> ComparisonChain.start()
                .compareTrueFirst(subscribeList.contains(news.getProvider()), subscribeList.contains(t1.getProvider()))
                .compare(t1.getCreateDate(), news.getCreateDate())
                .result());
        newsListLiveData.postValue(list);
    }

    public void filter() {
        if (categoryList.isEmpty() && providerList.isEmpty()) {
            newsListLiveData.postValue(newsList);
        } else {
            List<News> filteredList = new ArrayList<>();
            for (News news : newsList) {
                if (!providerList.isEmpty() && !categoryList.isEmpty()) {
                    if (providerList.contains(news.getProvider())
                            && categoryList.contains(news.getCategory())) {
                        filteredList.add(news);
                    }
                } else if (!categoryList.isEmpty()) {
                    if (categoryList.contains(news.getCategory())) {
                        filteredList.add(news);
                    }
                } else if (!providerList.isEmpty()) {
                    if (providerList.contains(news.getProvider())) {
                        filteredList.add(news);
                    }
                }

            }
            newsListLiveData.postValue(filteredList);
        }

    }

    public void saveNews(News news) {
        historyList.add(0, news);
        new SharePreferenceUseCase().saveList(Constant.PREF_USER_SETTING, Constant.PREF_KEY_NEWS_HISTORY, historyList);
    }


}
