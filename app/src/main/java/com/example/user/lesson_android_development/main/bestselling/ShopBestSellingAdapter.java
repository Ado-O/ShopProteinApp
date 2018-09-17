package com.example.user.lesson_android_development.main.bestselling;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.lesson_android_development.data.Product;
import com.example.user.lesson_android_development.databinding.ShopItemBinding;
import com.example.user.lesson_android_development.main.shop.ShopViewHolder;
import com.example.user.lesson_android_development.util.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class ShopBestSellingAdapter extends RecyclerView.Adapter {

    private final List<Product> mList=new ArrayList<>();
    private LayoutInflater mInflater;
    private RecyclerViewClickListener mListener;


    public ShopBestSellingAdapter(Context context, RecyclerViewClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mListener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopViewHolder(
                ShopItemBinding.inflate(
                        mInflater,
                        parent,
                        false
                ), mListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ShopViewHolder) holder).setup(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setItems(List list){
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

}