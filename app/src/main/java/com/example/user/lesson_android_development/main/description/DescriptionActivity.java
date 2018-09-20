package com.example.user.lesson_android_development.main.description;


import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.user.lesson_android_development.R;
import com.example.user.lesson_android_development.data.DescriptionList;
import com.example.user.lesson_android_development.data.Product;
import com.example.user.lesson_android_development.databinding.DescriptionActBinding;
import com.example.user.lesson_android_development.main.descriptionlist.DescriptionListAdapter;
import com.example.user.lesson_android_development.main.shop.ShopViewModel;
import com.example.user.lesson_android_development.util.ActivityUtils;
import com.example.user.lesson_android_development.util.ViewModelFactory;



public class DescriptionActivity extends AppCompatActivity{

    private static final String TAG = DescriptionActivity.class.getSimpleName();

    private SlideImageAdapter mSlideImageAdapter;
    private DescriptionActBinding mDescriptionActBinding;
    private Product p;
    private ShopViewModel mShopViewModel;
    private long tadId = 0;

    public static void startActivity(Activity activity, Product product) {

        Intent intent = new Intent(activity, DescriptionActivity.class);
        intent.putExtra("product", product);
        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_act);

        mDescriptionActBinding = DataBindingUtil.setContentView(this, R.layout.description_act);

        mShopViewModel = ViewModelFactory.obtainViewModel(this, ShopViewModel.class);
        mShopViewModel.startProduct(tadId);

        p = getIntent().getExtras().getParcelable("product");

        setupData();
        setupToolbar();
        setupImage();
        setupDescriptionList();
    }

    /**
     * Setting up the toolbar, toolbar actions & title
     */
    private void setupToolbar() {
        //toolbar setup
        setSupportActionBar(mDescriptionActBinding.tlb);
        //setting up the back button on the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * OnClickListener for the toolbar back button
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * add data in layout
     */
    private void setupData() {

        DescriptionList descriptionList = new DescriptionList(
                p.getTitle(),
                p.getPrice(),
                p.getDiscounte(),
                p.getProductDescriptions()
        );
        mDescriptionActBinding.setDescriptionList(descriptionList);

        //strikethrough discount
        mDescriptionActBinding.tvDesDiscount.setPaintFlags(
                mDescriptionActBinding.tvDesDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

    }

    /**
     * add image and slideImageAdapter
     */
    private void setupImage() {

        mSlideImageAdapter = new SlideImageAdapter(DescriptionActivity.this, p.getProductImages());
        mDescriptionActBinding.viewPager.setAdapter(mSlideImageAdapter);
        mDescriptionActBinding.indicator.setupWithViewPager(mDescriptionActBinding.viewPager, true);

    }

    /**
     * add descriptionList
     */
    private void setupDescriptionList() {
        DescriptionListAdapter descriptionListAdapter = new DescriptionListAdapter(DescriptionActivity.this, p.getProductDescriptions());
        mDescriptionActBinding.rvDesList.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        ));
        mDescriptionActBinding.rvDesList.setAdapter(descriptionListAdapter);

    }


}