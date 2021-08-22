package com.tim.newsfeed.feature.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.tim.newsfeed.R;
import com.tim.newsfeed.feature.home.CategoryOnClick;
import com.tim.newsfeed.feature.home.viewholder.ConditionViewHolder;
import com.tim.newsfeed.pojo.Category;

public class CategoryAdapter extends ListAdapter<Category, ConditionViewHolder> {
    CategoryOnClick categoryOnClick;

    public CategoryAdapter(@NonNull DiffUtil.ItemCallback<Category> diffCallback) {
        super(diffCallback);
    }

    public void setCategoryOnClick(CategoryOnClick categoryOnClick) {
        this.categoryOnClick = categoryOnClick;
    }

    @NonNull
    @Override
    public ConditionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.condition_viewholder, parent, false);
        ConditionViewHolder holder = new ConditionViewHolder(view);
        holder.itemView.setOnClickListener(v -> {
            Category condition = getItem(holder.getAdapterPosition());
            condition.setSelected(!condition.isSelected());
            notifyItemChanged(holder.getAdapterPosition());
            if (categoryOnClick != null) {
                categoryOnClick.onClick(condition);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConditionViewHolder holder, int position) {
        Category category = getItem(position);
        holder.binding.title.setText(category.getTitle());
        holder.binding.title.setSelected(category.isSelected());
    }
}
