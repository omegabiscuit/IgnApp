package com.brighambangerter.ignapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * An article
 */
public class Article {
    @SerializedName("thumbnails")
    private String mThumbnail;
    @SerializedName("metadata")
    private MetaData mMetaData;

    public String getThumbnail() {
        return mThumbnail;
    }

    public MetaData getMetaData() {
        return mMetaData;
    }
}
