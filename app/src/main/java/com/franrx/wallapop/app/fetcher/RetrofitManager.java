package com.franrx.wallapop.app.fetcher;

import android.content.Context;

import com.franrx.wallapop.app.BuildConfig;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitManager {
    //Network manager instace to get better performance with continous network work
    private static RetrofitManager instance;
    //Queue with all request that we need to dispatch
    private static Retrofit retrofit;
    private static MarvelApiService apiService;
    private OkHttpClient httpClient;
    private Context context;

    private RetrofitManager(Context context) {
        this.context = context;
        getHttpClient();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BuildConfig.SERVER_URL);
        retrofit = builder.addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    private void getHttpClient() {
        httpClient = new OkHttpClient();

        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        Cache cache = new Cache(httpCacheDirectory, 10240 * 1024);
        httpClient.setCache(cache);
        httpClient.setReadTimeout(15, TimeUnit.SECONDS);
        httpClient.setConnectTimeout(15, TimeUnit.SECONDS);
        httpClient.setFollowSslRedirects(false);
        httpClient.setFollowRedirects(false);
    }

    public static synchronized MarvelApiService getApiServiceInstance() {
        if (apiService == null) {
            apiService = retrofit.create(MarvelApiService.class);
        }
        return apiService;
    }

    public static synchronized RetrofitManager getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitManager(context);
        }
        return instance;
    }

}