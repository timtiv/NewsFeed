package com.tim.newsfeed.feature.news;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tim.newsfeed.pojo.News;
import com.tim.newsfeed.usecase.NewsUseCase;
import com.tim.newsfeed.util.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsViewModel extends ViewModel {
    MutableLiveData<News> newsLiveData;

    public NewsViewModel() {
        newsLiveData = new MutableLiveData<>();
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    static SimpleDateFormat sdf;

    public LiveData<News> getNewsLiveData() {
        return newsLiveData;
    }

    public void bindNews(Bundle bundle) {
        if (bundle != null) {
            News news = bundle.getParcelable(Constant.KEY_NEWS);
            if (news != null) {
                newsLiveData.postValue(news);
            }
            new NewsUseCase().saveNews(news);
        }
    }

    public String formatDate(Date date) {
        if (date != null) {
            return sdf.format(date);
        } else {
            return "";
        }
    }
}
