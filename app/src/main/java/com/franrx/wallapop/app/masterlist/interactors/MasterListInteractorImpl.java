package com.franrx.wallapop.app.masterlist.interactors;

import android.content.Context;

import com.franrx.wallapop.app.BuildConfig;
import com.franrx.wallapop.app.domain.Comic;
import com.franrx.wallapop.app.fetcher.RetrofitManager;
import com.franrx.wallapop.app.masterlist.listeners.OnComicsFetchFinishedListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MasterListInteractorImpl implements MasterListInteractor {

    public void getComics(String characterId, final Integer offset, Context context, final OnComicsFetchFinishedListener listener) {
        Call<Comic> call = RetrofitManager.getInstance(context).getApiServiceInstance().getComics(characterId, offset, 1, BuildConfig.PUBLIC_KEY, getAuthHash());
        call.enqueue(new Callback<Comic>() {
            @Override
            public void onResponse(Response<Comic> response, Retrofit retrofit) {
                if(response.errorBody() != null) {
                    listener.onError();
                } else {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onError();
            }
        });
    }

    private static String getAuthHash() {
        final String MD5 = "MD5";
        final String keys = "1".concat(BuildConfig.PRIVATE_KEY).concat(BuildConfig.PUBLIC_KEY);
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(keys.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
