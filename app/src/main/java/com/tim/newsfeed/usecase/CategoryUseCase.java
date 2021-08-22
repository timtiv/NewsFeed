package com.tim.newsfeed.usecase;

import androidx.lifecycle.MutableLiveData;

import com.tim.newsfeed.pojo.Category;
import com.tim.newsfeed.pojo.Condition;
import com.tim.newsfeed.repository.CategoryRepository;

import java.util.List;

public class CategoryUseCase {
    public MutableLiveData<List<Category>> getCategoryLiveData() {
        return CategoryRepository.getInstance().getCategoryLiveData();
    }
}
