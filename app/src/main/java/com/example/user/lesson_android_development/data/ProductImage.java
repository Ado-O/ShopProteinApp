package com.example.user.lesson_android_development.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@Entity(tableName = "productimage_table")
public class ProductImage implements Parcelable{

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

    protected ProductImage(Parcel in) {
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readInt();
        }
        mProductId = in.readLong();
        mPictures = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mId);
        }
        dest.writeLong(mProductId);
        dest.writeString(mPictures);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductImage> CREATOR = new Creator<ProductImage>() {
        @Override
        public ProductImage createFromParcel(Parcel in) {
            return new ProductImage(in);
        }

        @Override
        public ProductImage[] newArray(int size) {
            return new ProductImage[size];
        }
    };
}
