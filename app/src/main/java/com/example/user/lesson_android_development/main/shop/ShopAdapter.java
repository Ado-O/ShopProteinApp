package com.example.user.lesson_android_development.main.shop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.lesson_android_development.data.Product;
import com.example.user.lesson_android_development.databinding.ShopBestSellingItemBinding;
import com.example.user.lesson_android_development.databinding.ShopItemBinding;
import com.example.user.lesson_android_development.main.bestselling.ShopBestSellingViewHolder;
import com.example.user.lesson_android_development.util.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter {

    private final int BEST_SELLING = 1;
    private final int ITEM = 2;

    private LayoutInflater mInflater;
    private RecyclerViewClickListener mListener;

    private ArrayList<Product> mBestSellingItems=new ArrayList<>();
    private ArrayList<Product> mItems=new ArrayList<>();



    public ShopAdapter(Context context, RecyclerViewClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mListener = listener;
    }

    //this method returns the number according to the Vertical/Horizontal object
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BEST_SELLING;
        } else {
            return ITEM;
        }
    }

    //this method returns the holder that we've inflated according to the viewtype.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BEST_SELLING) {
            return new ShopBestSellingViewHolder(
                    ShopBestSellingItemBinding.inflate(
                            mInflater,
                            parent,
                            false
                    ), mListener
            );
        } else if (viewType == ITEM) {
            return new ShopViewHolder(
                    ShopItemBinding.inflate(
                            mInflater,
                            parent,
                            false
                    ), mListener
            );
        } else {
            throw new RuntimeException("The type has to be ONE or TWO");
        }

    }

    //here we bind view with data according to the position that we have defined.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == BEST_SELLING){
            ((ShopBestSellingViewHolder) holder).setup(mBestSellingItems);
        } else if (holder.getItemViewType() == ITEM){
            ((ShopViewHolder) holder).setup(mItems.get(position-1));
        }

    }

    @Override
    public int getItemCount() {
        return mItems.size() + 1;
    }

    public void setItems(List<Product> products, List<Product> bestSellingItems){
        mItems.clear();
        mItems.addAll(products);

        mBestSellingItems.clear();
        mBestSellingItems.addAll(bestSellingItems);

        notifyDataSetChanged();
    }

}
