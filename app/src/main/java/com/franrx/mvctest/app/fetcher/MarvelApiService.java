package com.franrx.mvctest.app.fetcher;

import com.franrx.mvctest.app.domain.Comic;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface MarvelApiService {

    @GET("v1/public/characters/{characterId}/comics")
    Call<Comic> getComics(@Path("characterId") String characterId, @Query("offset") int offset, @Query("ts") Integer ts, @Query("apikey") String apikey, @Query("hash") String hash);
}