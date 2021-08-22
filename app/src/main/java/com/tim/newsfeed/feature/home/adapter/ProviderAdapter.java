package com.tim.newsfeed.feature.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.tim.newsfeed.R;
import com.tim.newsfeed.feature.home.CategoryOnClick;
import com.tim.newsfeed.feature.home.ProviderOnClick;
import com.tim.newsfeed.feature.home.ProviderOnSubscribe;
import com.tim.newsfeed.feature.home.viewholder.ProviderViewHolder;
import com.tim.newsfeed.pojo.Condition;
import com.tim.newsfeed.pojo.Provider;

public class ProviderAdapter extends ListAdapter<Provider, ProviderViewHolder> {
    ProviderOnClick providerOnClick;
    ProviderOnSubscribe providerOnSubscribe;

    public ProviderAdapter(@NonNull DiffUtil.ItemCallback<Provider> diffCallback) {
        super(diffCallback);
    }

    public void setProviderOnClick(ProviderOnClick providerOnClick) {
        this.providerOnClick = providerOnClick;
    }

    public void setProviderOnSubscribe(ProviderOnSubscribe providerOnSubscribe) {
        this.providerOnSubscribe = providerOnSubscribe;
    }

    @NonNull
    @Override
    public ProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.provider_viewholder, parent, false);
        ProviderViewHolder holder = new ProviderViewHolder(view);
        holder.itemView.setOnClickListener(v -> {
            Provider provider = getItem(holder.getAdapterPosition());
            provider.setSelected(!provider.isSelected());
            notifyItemChanged(holder.getAdapterPosition());
            if (providerOnClick != null) {
                providerOnClick.onClick(provider);
            }
        });
        holder.binding.subscribe.setOnClickListener(v -> {
            Provider provider = getItem(holder.getAdapterPosition());
            provider.setSubscribed(!provider.isSubscribed());
            notifyItemChanged(holder.getAdapterPosition());
            if (providerOnSubscribe != null) {
                providerOnSubscribe.onSubscribe(provider);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderViewHolder holder, int position) {
        Provider provider = getItem(position);
        holder.binding.title.setText(provider.getTitle());
        holder.binding.layout.setSelected(provider.isSelected());
        holder.binding.subscribe.setSelected(provider.isSubscribed());
    }
}
