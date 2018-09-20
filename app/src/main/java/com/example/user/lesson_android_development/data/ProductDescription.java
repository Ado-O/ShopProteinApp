package com.example.user.lesson_android_development.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@Entity(tableName = "productdescription_table")
public class ProductDescription implements Parcelable{

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name = "product_id")
    private long mProductId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "description")
    private String mDescription;

    public ProductDescription(Integer id, long productId, String name, String description) {
        mId = id;
        mProductId = productId;
        mName = name;
        mDescription = description;
    }
    @Ignore
    public ProductDescription(long productId, String name, String description) {
        mId = null;
        mProductId = productId;
        mName = name;
        mDescription = description;
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

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    protected ProductDescription(Parcel in) {
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readInt();
        }
        mProductId = in.readLong();
        mName = in.readString();
        mDescription = in.readString();
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
        dest.writeString(mName);
        dest.writeString(mDescription);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductDescription> CREATOR = new Creator<ProductDescription>() {
        @Override
        public ProductDescription createFromParcel(Parcel in) {
            return new ProductDescription(in);
        }

        @Override
        public ProductDescription[] newArray(int size) {
            return new ProductDescription[size];
        }
    };
}
