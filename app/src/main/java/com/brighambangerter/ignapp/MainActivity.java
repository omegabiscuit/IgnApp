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

    private RecyclerView mRecyclerView;
    private ArticlesAdapter mArticlesAdapter;


    private RecyclerView mVideoRecyclerView;
    private VideoAdapter mVideoAdapter;

    private static final String IgnUrl = "http://www.ign.com/";
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mArticlesAdapter = new ArticlesAdapter();
        mRecyclerView.setAdapter(mArticlesAdapter);

        LinearLayoutManager videoLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mVideoRecyclerView = (RecyclerView) findViewById(R.id.list2);
        mVideoRecyclerView.setLayoutManager(videoLayout);
        mVideoAdapter = new VideoAdapter();

        loadVideos();
        loadArticles();


        Timber.tag("LifeCycles");
        Timber.d("Activity Created");
    }

    public void loadVideos(){
        IgnClient.instance().getVideos().enqueue(new Callback<ArticleResponse>() {
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

    public void loadArticles(){
        IgnClient.instance().getArticles().enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                if (response.isSuccessful()) {
                    mArticlesAdapter.setArticles(response.body().getData(), mVideoAdapter.getVideos());
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
