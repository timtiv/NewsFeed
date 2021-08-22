package com.tim.newsfeed.feature.home.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tim.newsfeed.databinding.NewsViewholderBinding;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    public NewsViewholderBinding binding;
    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = NewsViewholderBinding.bind(itemView);
    }
}
