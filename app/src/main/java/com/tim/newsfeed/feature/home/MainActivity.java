package com.tim.newsfeed.feature.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tim.newsfeed.databinding.ActivityMainBinding;
import com.tim.newsfeed.feature.history.HistoryActivity;
import com.tim.newsfeed.feature.home.adapter.CategoryAdapter;
import com.tim.newsfeed.feature.home.adapter.NewsAdapter;
import com.tim.newsfeed.feature.home.adapter.ProviderAdapter;
import com.tim.newsfeed.feature.news.NewsActivity;
import com.tim.newsfeed.pojo.Category;
import com.tim.newsfeed.pojo.News;
import com.tim.newsfeed.pojo.Provider;
import com.tim.newsfeed.util.Constant;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    NewsAdapter adapter;
    CategoryAdapter categoryAdapter;
    ProviderAdapter providerAdapter;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new NewsAdapter(new DiffUtil.ItemCallback<News>() {
            @Override
            public boolean areItemsTheSame(@NonNull News oldItem, @NonNull News newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull News oldItem, @NonNull News newItem) {
                return oldItem.equals(newItem);
            }
        });

        categoryAdapter = new CategoryAdapter(new DiffUtil.ItemCallback<Category>() {
            @Override
            public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
                return oldItem.getTitle().equals(newItem.getTitle());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
                return false;
            }
        });

        providerAdapter = new ProviderAdapter(new DiffUtil.ItemCallback<Provider>() {
            @Override
            public boolean areItemsTheSame(@NonNull Provider oldItem, @NonNull Provider newItem) {
                return oldItem.getTitle().equals(newItem.getTitle());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Provider oldItem, @NonNull Provider newItem) {
                return false;
            }
        });

        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(adapter);
        binding.categoryList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.categoryList.setAdapter(categoryAdapter);
        binding.providerList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.providerList.setAdapter(providerAdapter);
        binding.history.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
        adapter.setNewsOnclick(news -> {
            Intent intent = new Intent(MainActivity.this, NewsActivity.class);
            Bundle args = new Bundle();
            args.putParcelable(Constant.KEY_NEWS, news);
            intent.putExtras(args);
            startActivity(intent);
        });
        categoryAdapter.setCategoryOnClick(category -> {
            viewModel.categoryControl(category);
        });
        providerAdapter.setProviderOnClick(provider -> {
            viewModel.providerControl(provider);
        });
        providerAdapter.setProviderOnSubscribe(provider -> {
            adapter.submitList(null);
            viewModel.providerSubscribeControl(provider);
        });
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getNewsLiveData().observe(this, newsList -> {
            adapter.submitList(newsList);
        });
        viewModel.getCategoryLiveData().observe(this, categories -> {
            categoryAdapter.submitList(categories);
        });
        viewModel.getProviderLiveData().observe(this, providers -> {
            providerAdapter.submitList(providers);
        });

    }
}