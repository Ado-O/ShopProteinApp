package com.example.user.lesson_android_development.main.filter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.example.user.lesson_android_development.main.bestselling.ShopBestSellingAdapter;
import com.example.user.lesson_android_development.main.shop.ShopAdapter;

import java.util.List;

public class FilterBinding {

    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:filterItems"})
    public static void setFilterItems(RecyclerView recyclerView, List filterItems){

        if (filterItems != null && filterItems.size() > 0){
            ((FilterAdapter)recyclerView.getAdapter()).setItem(filterItems);
        }
    }

}
