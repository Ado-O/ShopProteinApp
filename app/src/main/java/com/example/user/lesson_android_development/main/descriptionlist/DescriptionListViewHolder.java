package com.example.user.lesson_android_development.main.descriptionlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.user.lesson_android_development.data.DescriptionList;
import com.example.user.lesson_android_development.data.ProductDescription;
import com.example.user.lesson_android_development.databinding.DescriptionListItemBinding;

public class DescriptionListViewHolder extends RecyclerView.ViewHolder {

    private DescriptionListItemBinding mDescriptionListItemBinding;

    public DescriptionListViewHolder(@NonNull DescriptionListItemBinding descriptionListItemBinding) {
        super(descriptionListItemBinding.getRoot());
        mDescriptionListItemBinding = descriptionListItemBinding;
    }

    public void setup(ProductDescription productDescription) {
        mDescriptionListItemBinding.setProductDescription(productDescription
        );
    }
}
