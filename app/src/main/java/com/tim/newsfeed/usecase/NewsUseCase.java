package com.tim.newsfeed.usecase;

import androidx.lifecycle.LiveData;

import com.tim.newsfeed.pojo.Category;
import com.tim.newsfeed.pojo.News;
import com.tim.newsfeed.pojo.Provider;
import com.tim.newsfeed.repository.NewsRepository;

import java.util.List;

public class NewsUseCase {
    public LiveData<List<News>> getNewsListLiveData() {
        return NewsRepository.getInstance().getNewsListLiveData();
    }

    public LiveData<List<News>> getHistoryNewsLiveData() {
        return NewsRepository.getInstance().getHistoryNewsLiveData();
    }

    public void addCategory(Category category){
        NewsRepository.getInstance().addFilter(category);
    }

    public void removeCategory(Category category){
        NewsRepository.getInstance().removeFilter(category);
    }

    public void addProvider(Provider provider){
        NewsRepository.getInstance().addFilter(provider);
    }

    public void removeProvider(Provider provider){
        NewsRepository.getInstance().removeFilter(provider);
    }

    public void saveNews(News news){
        NewsRepository.getInstance().saveNews(news);
    }
}
