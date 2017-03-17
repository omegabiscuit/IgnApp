package com.brighambangerter.ignapp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.brighambangerter.ignapp.model.Video;

import java.util.ArrayList;


/**
 * Created by Brigham on 4/16/2016.
 */
public class VideoListViewHolder extends RecyclerView.ViewHolder {

    private RecyclerView list;
    private VideoAdapter videoAdapter;

    public VideoListViewHolder(View view) {
        super(view);
        this.list = (RecyclerView) view.findViewById(R.id.videoList);
        this.list.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        this.videoAdapter = new VideoAdapter();
        this.list.setAdapter(this.videoAdapter);

    }

    public void setVideos(ArrayList<Video> list){
        this.videoAdapter.setArticles(list);
    }
}
