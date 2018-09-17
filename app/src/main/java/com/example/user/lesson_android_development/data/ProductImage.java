package com.example.user.lesson_android_development.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "productimage_table")
public class ProductImage implements Serializable{

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name = "product_id")
    private long mProductId;

    @ColumnInfo(name = "pictures")
    private String mPictures;

    public ProductImage(Integer id, long productId, String pictures) {
        mId = id;
        mProductId = productId;
        mPictures = pictures;
    }

    @Ignore
    public ProductImage(long productId, String pictures) {
        mId = null;
        mProductId = productId;
        mPictures = pictures;
    }


    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public long getProductId() {
        return mProductId;
    }

    public void setProductId(long productId) {
        mProductId = productId;
    }

    public String getPictures() {
        return mPictures;
    }

    public void setPictures(String pictures) {
        mPictures = pictures;
    }
}
