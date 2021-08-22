package com.tim.newsfeed.feature.home.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tim.newsfeed.databinding.ConditionViewholderBinding;

public class ConditionViewHolder extends RecyclerView.ViewHolder {
    public ConditionViewholderBinding binding;

    public ConditionViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = ConditionViewholderBinding.bind(itemView);
    }
}
