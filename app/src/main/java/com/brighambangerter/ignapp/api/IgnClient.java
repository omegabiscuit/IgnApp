package com.brighambangerter.ignapp.api;

import com.brighambangerter.ignapp.api.response.ArticleResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
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
        Call<ArticleResponse> getVideos(@Query("startIndex") int index);

    }

    public static Ign instance() {
        if (sIgn == null) {
            OkHttpClient client = new OkHttpClient();
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
