package com.brighambangerter.ignapp;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Brigham on 3/6/2017.
 */

public class VideoViewHolder extends RecyclerView.ViewHolder{

    protected TextView time;
    protected TextView articleTitle;
    protected ImageView thumbnail;
    protected RelativeLayout relativeLayout;
    protected RecyclerView videolist;
    protected String slug;

    public VideoViewHolder(View view){
        super(view);
        this.thumbnail = (ImageView) view.findViewById(R.id.network_image);
        this.articleTitle = (TextView) view.findViewById(R.id.articleTitle);
        this.time = (TextView) view.findViewById(R.id.time);
        this.videolist = (RecyclerView) view.findViewById(R.id.list2);
        this.relativeLayout = (RelativeLayout) view.findViewById(R.id.relLayout);
        view.setClickable(true);

    }
}