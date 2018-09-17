package com.example.user.lesson_android_development.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "product_tag_table")
public class ProductTag {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name = "product_id")
    private long mProductId;

    @ColumnInfo(name = "tag_id")
    private long mTagId;

    public ProductTag(Integer id, long productId, long tagId) {
        mId = id;
        mProductId = productId;
        mTagId = tagId;
    }

    @Ignore
    public ProductTag(long productId, long tagId) {
        mId = null;
        mProductId = productId;
        mTagId = tagId;
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

    public long getTagId() {
        return mTagId;
    }

    public void setTagId(long tagId) {
        mTagId = tagId;
    }
}
