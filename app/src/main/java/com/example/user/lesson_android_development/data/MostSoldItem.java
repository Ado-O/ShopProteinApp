package com.example.user.lesson_android_development.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "most_sold_item_table")
public class MostSoldItem {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name = "product_id")
    private long mProductId;

    public MostSoldItem(Integer id, long productId) {
        mId = id;
        mProductId = productId;
    }

    @Ignore
    public MostSoldItem(long productId) {
        mId = null;
        mProductId = productId;
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
}
