package com.brighambangerter.ignapp;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brighambangerter.ignapp.model.Article;
import com.brighambangerter.ignapp.model.Content;
import com.brighambangerter.ignapp.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brigham on 4/17/2016.
 */
public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int VIEW_TYPE_ARTICLE = 0;
    private static final int VIEW_TYPE_VIDEO = 1;

    private List<Object> contents;


    public ContentAdapter() {
        contents = new ArrayList<>();

    }

    public void addContent(Content content) {
        contents.addAll(content.getArticles());
        contents.add(content.getVideos());
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_ARTICLE:
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article, null);
                ArticleViewHolder holder = new ArticleViewHolder(v);

                holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView articleUrl = (TextView) v.findViewById(R.id.time);
                        String postUrl = articleUrl.getText().toString();
                        // Intent intent = new Intent(mContext, webActivity.class);
                        // intent.putExtra("url", postUrl);
                        //mContext.startActivity(intent);
                    }

                });
                return holder;
            case VIEW_TYPE_VIDEO:
                View v2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video_list, null);
                VideoListViewHolder holder2 = new VideoListViewHolder(v2);


                return holder2;
        }
        throw new RuntimeException("viewType not known");

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {
            case VIEW_TYPE_ARTICLE:
                Article article = (Article) contents.get(position);
                ArticleViewHolder articleViewHolder = (ArticleViewHolder) viewHolder;
                Picasso.with(viewHolder.itemView.getContext())
                        .load(article.getThumbnails().get(2).getUrl())
                        .into(articleViewHolder.thumbnail);


                articleViewHolder.articleTitle.setText(Html.fromHtml(article.getMetaData().getHeadline()));
                articleViewHolder.time.setText(Html.fromHtml(article.getMetaData().getPublishDate()));
                break;
            case VIEW_TYPE_VIDEO:
                ArrayList<Video> videos = (ArrayList<Video>) contents.get(position);
                VideoListViewHolder videoListViewHolder = (VideoListViewHolder) viewHolder;
                videoListViewHolder.setVideos(videos);
                break;
        }

    }


    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object object = contents.get(position);
        if (object instanceof Article) {
            return VIEW_TYPE_ARTICLE;
        } else if (object instanceof ArrayList) {
            return VIEW_TYPE_VIDEO;
        }
        throw new RuntimeException("IDK what to do if this doesn't work");
    }
}
