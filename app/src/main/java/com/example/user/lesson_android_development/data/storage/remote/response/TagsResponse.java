package com.example.user.lesson_android_development.data.storage.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TagsResponse implements Serializable{

    @SerializedName("id")
    @Expose
    private long mId;

    @SerializedName("name")
    @Expose
    private String mName;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
