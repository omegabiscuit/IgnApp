package com.brighambangerter.ignapp.model;


import java.util.ArrayList;

/**
 * Created by Brigham on 3/16/2017.
 */

public class Content {
    ArrayList<Article> articles;
    ArrayList<Video> videos;

    public Content(ArrayList<Article> articles, ArrayList<Video> videos) {
        this.articles = articles;
        this.videos = videos;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }
}
