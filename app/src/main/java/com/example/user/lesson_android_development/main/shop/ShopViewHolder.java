package com.example.user.lesson_android_development.main.shop;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.example.user.lesson_android_development.data.Product;
import com.example.user.lesson_android_development.databinding.ShopItemBinding;
import com.example.user.lesson_android_development.util.RecyclerViewClickListener;

public class ShopViewHolder extends RecyclerView.ViewHolder {

    private final ShopItemBinding mBinding;

    public ShopViewHolder(@NonNull ShopItemBinding binding, RecyclerViewClickListener listener) {
        super(binding.getRoot());

        mBinding = binding;
        mBinding.setListener(listener);

    }


    public void setup(Product product) {
        mBinding.setProduct(product);
    }

}
