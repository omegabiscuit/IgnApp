package com.brighambangerter.ignapp.api.response;

import com.google.gson.annotations.SerializedName;
import com.brighambangerter.ignapp.model.Article;

import java.util.ArrayList;

/**
 * Response from API that contains the articles
 */
public class ArticleResponse {
    @SerializedName("count")
    private int mCount;
    @SerializedName("startIndex")
    private int mStartIndex;
    @SerializedName("data")
    private ArrayList<Article> mData;

    public int getCount() {
        return mCount;
    }

    public int getStartIndex() {
        return mStartIndex;
    }

    public ArrayList<Article> getData() {
        return mData;
    }
}
