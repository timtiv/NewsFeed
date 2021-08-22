package com.tim.newsfeed.feature.history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tim.newsfeed.databinding.ActivityHistoryBinding;
import com.tim.newsfeed.feature.home.adapter.NewsAdapter;
import com.tim.newsfeed.pojo.News;

public class HistoryActivity extends AppCompatActivity {
    ActivityHistoryBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NewsAdapter adapter = new NewsAdapter(new DiffUtil.ItemCallback<News>() {
            @Override
            public boolean areItemsTheSame(@NonNull News oldItem, @NonNull News newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull News oldItem, @NonNull News newItem) {
                return oldItem.equals(newItem);
            }
        });
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(adapter);

        HistoryViewModel historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        historyViewModel.getHistoryNewsLiveData().observe(this, adapter::submitList);
    }
}
