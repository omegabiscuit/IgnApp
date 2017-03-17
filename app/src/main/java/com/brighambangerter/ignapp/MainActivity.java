package com.brighambangerter.ignapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.brighambangerter.ignapp.api.IgnClient;
import com.brighambangerter.ignapp.model.Content;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private static final int ARTICLE_INCREMENT = 2;
    private static final int VIDEO_INCREMENT = 5;

    private RecyclerView mRecyclerView;
    private ContentAdapter mContentAdapter;


    int articleIndex = 0;
    int videoIndex = 0;

    boolean loading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mContentAdapter = new ContentAdapter();
        mRecyclerView.setAdapter(mContentAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItem + visibleItemCount >= totalItemCount && !loading) {
                    articleIndex += ARTICLE_INCREMENT;
                    videoIndex += VIDEO_INCREMENT;
                    loadContent();
                }
            }
        });
        loadContent();


        Timber.tag("LifeCycles");
        Timber.d("Activity Created");
    }

    public void loadContent() {
        IgnClient.getContent(articleIndex, videoIndex, ARTICLE_INCREMENT, VIDEO_INCREMENT).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Content>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Content content) {
                        mContentAdapter.addContent(content);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this,"DANGER!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                });
    }


}
