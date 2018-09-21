package com.example.user.lesson_android_development.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "products_table")
public class Product implements Parcelable{

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private long mId;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "description")
    private String mDes;

    @ColumnInfo(name = "pictures")
    private String mPictures;

    @ColumnInfo(name = "price")
    private String mPrice;

    @ColumnInfo(name = "discounte")
    private String mDiscounte;

    @Ignore
    private List<ProductImage> mProductImages = new ArrayList<>();

    @Ignore
    private List<ProductDescription> mProductDescriptions = new ArrayList<>();

    @Ignore
    private List<Tag> mTags = null;

    @Ignore
    private List<CartItem> mCartItems = null;

    public Product(long id, String title, String des, String price, String discounte, String pictures) {
        mId = id;
        mTitle = title;
        mDes = des;
        mPrice = price;
        mDiscounte = discounte;
        mPictures = pictures;
    }

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

    public String getPictures() {
        return mPictures;
    }

    public void setPictures(String pictures) {
        mPictures = pictures;
    }

    public List<ProductImage> getProductImages() {
        return mProductImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        mProductImages = productImages;
    }

    public List<ProductDescription> getProductDescriptions() {
        return mProductDescriptions;
    }

    public void setProductDescriptions(List<ProductDescription> productDescriptions) {
        mProductDescriptions = productDescriptions;
    }

    public List<Tag> getTags() {
        return mTags;
    }

    public void setTags(List<Tag> tags) {
        mTags = tags;
    }

    public List<CartItem> getCartItems() {
        return mCartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        mCartItems = cartItems;
    }

    protected Product(Parcel in) {
        mId = in.readLong();
        mTitle = in.readString();
        mDes = in.readString();
        mPictures = in.readString();
        mPrice = in.readString();
        mDiscounte = in.readString();
       in.readList(this.mProductImages, ProductImage.class.getClassLoader());
    in.readList(this.mProductDescriptions, ProductDescription.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mTitle);
        dest.writeString(mDes);
        dest.writeString(mPictures);
        dest.writeString(mPrice);
        dest.writeString(mDiscounte);
        dest.writeList(mProductImages);
        dest.writeList(mProductDescriptions);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

}
