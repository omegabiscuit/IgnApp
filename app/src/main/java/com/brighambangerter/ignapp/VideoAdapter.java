package com.brighambangerter.ignapp;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brighambangerter.ignapp.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brigham on 3/7/2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    private List<Video> mVideos;

    public VideoAdapter() {
        mVideos = new ArrayList<>();
    }

    public void setArticles(ArrayList<Video> videos) {
        mVideos.clear();
        mVideos.addAll(videos);
        notifyDataSetChanged();
    }

    public VideoViewHolder onCreateViewHolder(final ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article, null);
        VideoViewHolder holder = new VideoViewHolder(v);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView articleUrl = (TextView) v.findViewById(R.id.time);
                String postUrl = articleUrl.getText().toString();
            }

        });

        return holder;
    }


    public void onBindViewHolder(final VideoViewHolder videoViewHolder, int position) {
        Video video = mVideos.get(position);

        videoViewHolder.getLayoutPosition();
        Picasso.with(videoViewHolder.itemView.getContext())
                .load(video.getThumbnails().get(2).getUrl())
                .into(videoViewHolder.thumbnail);


        videoViewHolder.articleTitle.setText(Html.fromHtml(video.getMetaData().getHeadline()));
        videoViewHolder.time.setText(Html.fromHtml(video.getMetaData().getPublishDate()));
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }
}