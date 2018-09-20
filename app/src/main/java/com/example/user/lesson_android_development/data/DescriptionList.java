package com.example.user.lesson_android_development.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class DescriptionList implements Parcelable {

    private String mTitle;
    private String mPrice;
    private String mDiscount;

    private List<ProductDescription> mDesList;

    public DescriptionList(String title, String price, String discount, List<ProductDescription> descriptionList) {
        mTitle = title;
        mPrice = price;
        mDiscount = discount;
        mDesList = descriptionList;
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
        return mDesList;
    }

    public void setNameDesc(List<ProductDescription> nameDesc) {
        mDesList = nameDesc;
    }

    protected DescriptionList(Parcel in) {
        mTitle = in.readString();
        mPrice = in.readString();
        mDiscount = in.readString();
        mDesList = in.createTypedArrayList(ProductDescription.CREATOR);

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mPrice);
        dest.writeString(mDiscount);
        dest.writeTypedList(mDesList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DescriptionList> CREATOR = new Creator<DescriptionList>() {
        @Override
        public DescriptionList createFromParcel(Parcel in) {
            return new DescriptionList(in);
        }

        @Override
        public DescriptionList[] newArray(int size) {
            return new DescriptionList[size];
        }
    };
}



