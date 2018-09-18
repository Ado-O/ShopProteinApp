package com.example.user.lesson_android_development.main.descriptionlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.lesson_android_development.data.DescriptionList;
import com.example.user.lesson_android_development.data.ProductDescription;
import com.example.user.lesson_android_development.databinding.DescriptionListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class DescriptionListAdapter extends RecyclerView.Adapter {

    private List<ProductDescription> mList;
    private LayoutInflater mInflater;

    public DescriptionListAdapter(Context context, List<ProductDescription> list) {
        mInflater = LayoutInflater.from(context);
        mList = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DescriptionListViewHolder(DescriptionListItemBinding.inflate(
                mInflater,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((DescriptionListViewHolder) holder).setup(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
