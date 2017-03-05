package com.brighambangerter.ignapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Article metadata
 */
public class MetaData {
    @SerializedName("headline")
    private String mHeadline;
    @SerializedName("networks")
    private ArrayList<String> mNetworks;
    @SerializedName("state")
    private String mState;
    @SerializedName("slug")
    private String mSlug;
    @SerializedName("subheadline")
    private String mSubHeadline;
    @SerializedName("publishDate")
    private String mPublishDate;
    @SerializedName("articleType")
    private String mArticleType;

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

    public String getArticleType() {
        return mArticleType;
    }
}
