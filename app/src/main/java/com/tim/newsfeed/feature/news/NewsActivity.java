package com.tim.newsfeed.feature.news;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.tim.newsfeed.databinding.ActivityNewsBinding;

public class NewsActivity extends AppCompatActivity {
    ActivityNewsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NewsViewModel viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        viewModel.getNewsLiveData().observe(this, news -> {
            binding.title.setText(news.getTitle());
            binding.subtitle.setText(news.getSubtitle());
            binding.author.setText(news.getAuthor());
            binding.date.setText(viewModel.formatDate(news.getCreateDate()));
            binding.content.setText(news.getContent());
        });
        viewModel.bindNews(getIntent().getExtras());
    }
}
