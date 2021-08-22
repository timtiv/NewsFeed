package com.tim.newsfeed.feature.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tim.newsfeed.pojo.News;
import com.tim.newsfeed.usecase.NewsUseCase;

import java.util.List;

public class HistoryViewModel extends ViewModel {
    public LiveData<List<News>> getHistoryNewsLiveData() {
        return new NewsUseCase().getHistoryNewsLiveData();
    }
}
