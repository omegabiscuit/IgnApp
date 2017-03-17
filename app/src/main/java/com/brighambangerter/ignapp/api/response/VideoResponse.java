package com.brighambangerter.ignapp.api.response;

import com.brighambangerter.ignapp.model.Video;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Response from API that contains the videos
 */
public class VideoResponse {
    @SerializedName("count")
    private int mCount;
    @SerializedName("startIndex")
    private int mStartIndex;
    @SerializedName("data")
    private ArrayList<Video> mData;

    public int getCount() {
        return mCount;
    }

    public int getStartIndex() {
        return mStartIndex;
    }

    public ArrayList<Video> getData() {
        return mData;
    }
}
