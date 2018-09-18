package com.example.user.lesson_android_development.main.description;


import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.user.lesson_android_development.R;
import com.example.user.lesson_android_development.data.DescriptionList;
import com.example.user.lesson_android_development.data.Product;
import com.example.user.lesson_android_development.data.ProductDescription;
import com.example.user.lesson_android_development.data.ProductImage;
import com.example.user.lesson_android_development.databinding.DescriptionActBinding;
import com.example.user.lesson_android_development.main.descriptionlist.DescriptionListAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DescriptionActivity extends AppCompatActivity {

    private static final String TAG = DescriptionActivity.class.getSimpleName();

    private SlideImageAdapter mSlideImageAdapter;
    private DescriptionActBinding mDescriptionActBinding;

    private String mImg, mNam, mDsc, mPrc;

    private List<ProductDescription> Desclist = new ArrayList<ProductDescription>();
    private List<ProductImage> imageList = new ArrayList<>();


    public static void startActivity(Activity activity, Product product) {

        Intent intent = new Intent(activity, DescriptionActivity.class);
        intent.putExtra("image", product.getPictures());
        intent.putExtra("name", product.getTitle());
        intent.putExtra("discount", product.getDiscounte());
        intent.putExtra("price", product.getPrice());
        intent.putExtra("dscList", (Serializable) product.getProductDescriptions());
        intent.putExtra("dscImage", (Serializable) product.getProductImages());

        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_act);

        mDescriptionActBinding = DataBindingUtil.setContentView(this, R.layout.description_act);

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

        mNam = getIntent().getExtras().getString("name");
        mDsc = getIntent().getExtras().getString("discount");
        mPrc = getIntent().getExtras().getString("price");
        mImg = getIntent().getExtras().getString("image");
        Desclist = (List<ProductDescription>) getIntent().getSerializableExtra("dscList");

        DescriptionList descriptionList = new DescriptionList(mNam, mPrc, mDsc, Desclist);
        mDescriptionActBinding.setDescriptionList(descriptionList);

        //strikethrough discount
        mDescriptionActBinding.tvDesDiscount.setPaintFlags(
                mDescriptionActBinding.tvDesDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

    }

    /**
     * add image and slideImageAdapter
     */
    private void setupImage() {

        imageList = (List<ProductImage>) getIntent().getSerializableExtra("dscImage");

        mSlideImageAdapter = new SlideImageAdapter(DescriptionActivity.this, imageList);
        mDescriptionActBinding.viewPager.setAdapter(mSlideImageAdapter);
        mDescriptionActBinding.indicator.setupWithViewPager(mDescriptionActBinding.viewPager, true);

    }

    /**
     * add descriptionList
     */
    private void setupDescriptionList() {
        DescriptionListAdapter descriptionListAdapter = new DescriptionListAdapter(DescriptionActivity.this, Desclist);
        mDescriptionActBinding.rvDesList.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        ));
        mDescriptionActBinding.rvDesList.setAdapter(descriptionListAdapter);

    }

}