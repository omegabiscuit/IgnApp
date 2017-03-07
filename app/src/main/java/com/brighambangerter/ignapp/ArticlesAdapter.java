package com.brighambangerter.ignapp;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brighambangerter.ignapp.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brigham on 4/17/2016.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private List<Article> mArticles;

    public ArticlesAdapter() {
        mArticles = new ArrayList<>();
    }

    public void setArticles(ArrayList<Article> articles) {
        mArticles.clear();
        mArticles.addAll(articles);
        notifyDataSetChanged();
    }

    public ArticleViewHolder onCreateViewHolder(final ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
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
    }


    public void onBindViewHolder(final ArticleViewHolder articleViewHolder, int position) {
        Article article = mArticles.get(position);

        articleViewHolder.getLayoutPosition();
        Picasso.with(articleViewHolder.itemView.getContext())
                .load(article.getThumbnails().get(2).getUrl())
                .into(articleViewHolder.thumbnail);
                //.placeholder(R.drawable.placeholder)


        articleViewHolder.articleTitle.setText(Html.fromHtml(article.getMetaData().getHeadline()));
        articleViewHolder.time.setText(Html.fromHtml(article.getMetaData().getPublishDate()));
    }

    public void clearAdapter() {
        mArticles.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }
}
