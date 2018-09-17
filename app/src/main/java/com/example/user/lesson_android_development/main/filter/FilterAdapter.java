package com.example.user.lesson_android_development.main.filter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.lesson_android_development.data.Tag;
import com.example.user.lesson_android_development.databinding.FilterItemBinding;
import com.example.user.lesson_android_development.util.RecyclerViewClickListenerTag;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter {

    public final ArrayList<Tag> mList = new ArrayList<>();
    public LayoutInflater mInflater;
    private RecyclerViewClickListenerTag mListener;

    public FilterAdapter(Context context, RecyclerViewClickListenerTag listener){
        mInflater = LayoutInflater.from(context);
        mListener = listener;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new FilterViewHolder(FilterItemBinding.inflate(mInflater, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((FilterViewHolder)holder).setup(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setItem(List list){
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }
}
