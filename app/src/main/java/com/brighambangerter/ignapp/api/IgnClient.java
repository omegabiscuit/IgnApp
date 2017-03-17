package com.brighambangerter.ignapp.api;

import com.brighambangerter.ignapp.api.response.ArticleResponse;
import com.brighambangerter.ignapp.api.response.VideoResponse;
import com.brighambangerter.ignapp.model.Content;

import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Connects with IGN
 */
public class IgnClient {

    private static final String API_URL = "http://ign-apis.herokuapp.com/";

    private static Ign sIgn;

    public interface Ign {
        @GET("articles")
        Call<ArticleResponse> getArticles(@Query("startIndex") int index);

        @GET("videos")
        Call<VideoResponse> getVideos(@Query("startIndex") int index);

    }

    public static Single<Content> getContent(final int articleIndex, final int videoIndex) {
        return Single.defer(new Callable<SingleSource<? extends Content>>() {
            @Override
            public SingleSource<? extends Content> call() throws Exception {
                Response<ArticleResponse> articleResponse = instance().getArticles(articleIndex).execute();
                if (!articleResponse.isSuccessful()) {
                    throw new HttpException(articleResponse);
                }
                Response<VideoResponse> videoResponse = instance().getVideos(videoIndex).execute();
                if (!articleResponse.isSuccessful()) {
                    throw new HttpException(articleResponse);
                }
                Content content = new Content(articleResponse.body().getData(), videoResponse.body().getData());
                return Single.just(content);
            }
        });
    }

    public static Ign instance() {
        if (sIgn == null) {
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            sIgn = restAdapter.create(Ign.class);
        }
        return sIgn;
    }
}
