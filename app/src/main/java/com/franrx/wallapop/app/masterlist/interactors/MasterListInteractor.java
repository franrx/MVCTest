package com.franrx.wallapop.app.masterlist.interactors;

import android.content.Context;

import com.franrx.wallapop.app.masterlist.listeners.OnComicsFetchFinishedListener;

public interface MasterListInteractor {
    void getComics(String characterId, Integer offset, Context context, OnComicsFetchFinishedListener listener);
}
