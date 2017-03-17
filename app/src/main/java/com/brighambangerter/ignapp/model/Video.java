package com.brighambangerter.ignapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * An video :D
 */
public class Video {
    @SerializedName("thumbnails")
    private ArrayList<Thumbnail> mThumbnails;
    @SerializedName("metadata")
    private MetaData mMetaData;

    public ArrayList<Thumbnail> getThumbnails() {
        return mThumbnails;
    }

    public MetaData getMetaData() {
        return mMetaData;
    }
}
