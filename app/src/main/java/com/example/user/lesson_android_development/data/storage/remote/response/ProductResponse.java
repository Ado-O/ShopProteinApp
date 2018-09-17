package com.example.user.lesson_android_development.data.storage.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductResponse implements Serializable{


    @SerializedName("id")
    @Expose
    private long mId;

    @SerializedName("name")
    @Expose
    private String mTitle;

    @SerializedName("description")
    @Expose
    private String mDes;

    @SerializedName("pictures")
    @Expose
    private List<String> mPictures = null;

    @SerializedName("price")
    @Expose
    private String mPrice;

    @SerializedName("discounted")
    @Expose
    private String mDiscounte;

    @SerializedName("suplements")
    @Expose
    private List<Integer> mSuplements = null;

    @SerializedName("tags")
    @Expose
    private List<Integer> tags = null;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDes() {
        return mDes;
    }

    public void setDes(String des) {
        mDes = des;
    }

    public List<String> getPictures() {
        return mPictures;
    }

    public void setPictures(List<String> pictures) {
        mPictures = pictures;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getDiscounte() {
        return mDiscounte;
    }

    public void setDiscounte(String discounte) {
        mDiscounte = discounte;
    }

    public List<Integer> getSuplements() {
        return mSuplements;
    }

    public void setSuplements(List<Integer> suplements) {
        mSuplements = suplements;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }
}
