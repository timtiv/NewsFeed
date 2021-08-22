package com.tim.newsfeed.feature.home.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tim.newsfeed.databinding.ProviderViewholderBinding;

public class ProviderViewHolder extends RecyclerView.ViewHolder {
    public ProviderViewholderBinding binding;

    public ProviderViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = ProviderViewholderBinding.bind(itemView);
    }
}
