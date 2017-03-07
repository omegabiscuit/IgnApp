package com.brighambangerter.ignapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by Brigham on 4/16/2016.
 */
public class ArticleViewHolder extends RecyclerView.ViewHolder{

    protected TextView time;
    protected TextView articleTitle;
    protected ImageView thumbnail;
    protected RelativeLayout relativeLayout;

    public ArticleViewHolder(View view){
        super(view);
        this.thumbnail = (ImageView) view.findViewById(R.id.network_image);
        this.articleTitle = (TextView) view.findViewById(R.id.articleTitle);
        this.time = (TextView) view.findViewById(R.id.time);
        this.relativeLayout = (RelativeLayout) view.findViewById(R.id.relLayout);
        view.setClickable(true);

    }
}
