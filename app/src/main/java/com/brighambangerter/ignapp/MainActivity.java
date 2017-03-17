package com.brighambangerter.ignapp;

import android.app.ProgressDialog;
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
                    index += INCREMENT;
                    loadContent();
                }
            }
        });

        LinearLayoutManager videoLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mVideoRecyclerView = (RecyclerView) findViewById(R.id.list2);
        mVideoRecyclerView.setLayoutManager(videoLayout);
        mVideoAdapter = new VideoAdapter();
        loadContent();


        Timber.tag("LifeCycles");
        Timber.d("Activity Created");
    }

    public void loadContent() {
        IgnClient.getContent(index).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Content>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Content content) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this,"DANGER!", Toast.LENGTH_LONG).show();
                    }
                });
    }


}
