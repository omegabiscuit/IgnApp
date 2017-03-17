package com.brighambangerter.ignapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.brighambangerter.ignapp.api.IgnClient;
import com.brighambangerter.ignapp.api.response.ArticleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "RecyclerList";
    private static final int INCREMENT = 10;

    private RecyclerView mRecyclerView;
    private ArticlesAdapter mArticlesAdapter;


    private RecyclerView mVideoRecyclerView;
    private VideoAdapter mVideoAdapter;

    private static final String IgnUrl = "http://www.ign.com/";
    private ProgressDialog progressDialog;

    int index = 0;

    boolean loading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mArticlesAdapter = new ArticlesAdapter();
        mRecyclerView.setAdapter(mArticlesAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItem + visibleItemCount >= totalItemCount && !loading) {
                    index+=INCREMENT;
                    loadArticles();
                }
            }
        });

        LinearLayoutManager videoLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mVideoRecyclerView = (RecyclerView) findViewById(R.id.list2);
        mVideoRecyclerView.setLayoutManager(videoLayout);
        mVideoAdapter = new VideoAdapter();

        loadVideos();
        loadArticles();


        Timber.tag("LifeCycles");
        Timber.d("Activity Created");
    }

    public void loadVideos() {
        IgnClient.instance().getVideos(0).enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                if (response.isSuccessful()) {
                    mVideoAdapter.setArticles(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadArticles() {
        loading = true;
        IgnClient.instance().getArticles(index).enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                loading = false;
                if (response.isSuccessful()) {
                    mArticlesAdapter.setArticles(response.body().getData());
                }
                else{
                    Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                loading = false;
                Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
