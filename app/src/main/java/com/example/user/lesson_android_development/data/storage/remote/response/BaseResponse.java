package com.example.user.lesson_android_development.data.storage.remote.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BaseResponse implements Serializable {

    @SerializedName("suplements")
    @Expose
    private List<SupplementsResponse> mSuplements;

    @SerializedName("products")
    @Expose
    private List<ProductResponse> mProducts;

    @SerializedName("tags")
    @Expose
    private List<TagsResponse> mTagsResponses;

    @SerializedName("most_sold")
    @Expose
    private List<Integer> mMostSoldItem;

    public List<SupplementsResponse> getSuplements() {
        return mSuplements;
    }

    public void setSuplements(List<SupplementsResponse> suplements) {
        mSuplements = suplements;
    }

    public List<ProductResponse> getProducts() {
        return mProducts;
    }

    public void setProducts(List<ProductResponse> products) {
        mProducts = products;
    }

    public List<TagsResponse> getTagsResponses() {
        return mTagsResponses;
    }

    public void setTagsResponses(List<TagsResponse> tagsResponses) {
        mTagsResponses = tagsResponses;
    }

    public List<Integer> getMostSoldItem() {
        return mMostSoldItem;
    }

    public void setMostSoldItem(List<Integer> mostSoldItem) {
        mMostSoldItem = mostSoldItem;
    }
}
