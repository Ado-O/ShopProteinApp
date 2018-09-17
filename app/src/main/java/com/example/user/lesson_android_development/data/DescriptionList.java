package com.example.user.lesson_android_development.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class DescriptionList implements Serializable {

    private String mTitle;
    private String mPrice;
    private String mDiscount;

    private List<ProductDescription> mNameDesc;

    public DescriptionList(String title, String price, String discount, List<ProductDescription> nameDesc) {
        mTitle = title;
        mPrice = price;
        mDiscount = discount;
        mNameDesc = nameDesc;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getDiscount() {
        return mDiscount;
    }

    public void setDiscount(String discount) {
        mDiscount = discount;
    }

    public List<ProductDescription> getNameDesc() {
        return mNameDesc;
    }

    public void setNameDesc(List<ProductDescription> nameDesc) {
        mNameDesc = nameDesc;
    }
}



