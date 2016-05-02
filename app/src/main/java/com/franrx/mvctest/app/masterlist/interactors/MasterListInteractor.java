package com.franrx.mvctest.app.masterlist.interactors;

import android.content.Context;

import com.franrx.mvctest.app.masterlist.listeners.OnComicsFetchFinishedListener;

public interface MasterListInteractor {
    void getComics(String characterId, Integer offset, Context context, OnComicsFetchFinishedListener listener);
}
