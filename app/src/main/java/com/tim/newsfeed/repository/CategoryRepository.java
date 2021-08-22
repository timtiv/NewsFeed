package com.tim.newsfeed.repository;

import androidx.lifecycle.MutableLiveData;

import com.tim.newsfeed.mock.Generator;
import com.tim.newsfeed.pojo.Category;
import com.tim.newsfeed.pojo.Condition;

import java.util.List;

public class CategoryRepository {
    private static final CategoryRepository ourInstance = new CategoryRepository();

    public static CategoryRepository getInstance() {
        return ourInstance;
    }

    private CategoryRepository() {
        categoryLiveData = new MutableLiveData<>(Generator.getCategoryList());
    }

    MutableLiveData<List<Category>> categoryLiveData;

    public MutableLiveData<List<Category>> getCategoryLiveData() {
        return categoryLiveData;
    }


}
