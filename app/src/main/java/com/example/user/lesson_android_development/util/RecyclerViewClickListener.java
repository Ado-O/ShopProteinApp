package com.example.user.lesson_android_development.util;

import android.view.View;

import com.example.user.lesson_android_development.data.Product;
import com.example.user.lesson_android_development.data.Tag;

public interface RecyclerViewClickListener {
     void recyclerViewListClicked(View v, Product product);
     void recyclerViewListClickedFAB(View v, Product product);
}