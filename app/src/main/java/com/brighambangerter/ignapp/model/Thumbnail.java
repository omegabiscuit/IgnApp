package com.brighambangerter.ignapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Brigham on 3/6/2017.
 */

public class Thumbnail {
    @SerializedName("url")
    private String mUrl;
    @SerializedName("size")
    private String mSize;
    @SerializedName("width")
    private int mWidth;
    @SerializedName("height")
    private int mHeight;


    public String getUrl() {
        return mUrl;
    }

    public String getSize() {
        return mSize;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }
}
