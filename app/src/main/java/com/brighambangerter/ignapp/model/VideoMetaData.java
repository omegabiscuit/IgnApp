package com.brighambangerter.ignapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Brigham on 3/7/2017.
 */

public class VideoMetaData {
    @SerializedName("name")
    private String mHeadline;
    @SerializedName("description")
    private String mState;
    @SerializedName("publishDate")
    private String mPublishDate;
    @SerializedName("longTitle")
    private String mSubHeadline;
    @SerializedName("duration")
    private int mDuration;
    @SerializedName("url")
    private String url;
    @SerializedName("slug")
    private String mSlug;
    @SerializedName("networks")
    private ArrayList<String> mNetworks;
    @SerializedName("state")
    private String state;


    public String getHeadline() {
        return mHeadline;
    }

    public ArrayList<String> getNetworks() {
        return mNetworks;
    }

    public String getState() {
        return mState;
    }

    public String getSlug() {
        return mSlug;
    }

    public String getSubHeadline() {
        return mSubHeadline;
    }

    public String getPublishDate() {
        return mPublishDate;
    }
}
