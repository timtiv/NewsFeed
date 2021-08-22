package com.tim.newsfeed.feature.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.tim.newsfeed.R;
import com.tim.newsfeed.feature.home.NewsOnclick;
import com.tim.newsfeed.feature.home.viewholder.NewsViewHolder;
import com.tim.newsfeed.pojo.News;

import java.text.SimpleDateFormat;

public class NewsAdapter extends ListAdapter<News, NewsViewHolder> {
    static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    NewsOnclick newsOnclick;

    public NewsAdapter(@NonNull DiffUtil.ItemCallback<News> diffCallback) {
        super(diffCallback);
    }

    public void setNewsOnclick(NewsOnclick newsOnclick) {
        this.newsOnclick = newsOnclick;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_viewholder, parent, false);
        NewsViewHolder holder = new NewsViewHolder(view);
        holder.itemView.setOnClickListener(v -> {
            if (newsOnclick != null) {
                newsOnclick.onClick(getItem(holder.getAdapterPosition()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = getItem(position);
        holder.binding.title.setText(news.getTitle());
        holder.binding.subtitle.setText(news.getSubtitle());
        holder.binding.category.setText(news.getCategory().getTitle());
        holder.binding.date.setText(sdf.format(news.getCreateDate()));
        holder.binding.provider.setText(news.getProvider().getTitle());
    }
}
