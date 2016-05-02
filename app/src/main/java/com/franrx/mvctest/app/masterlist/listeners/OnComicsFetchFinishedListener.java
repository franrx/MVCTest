package com.franrx.mvctest.app.masterlist.listeners;

import com.franrx.mvctest.app.domain.Comic;

public interface OnComicsFetchFinishedListener {

    void onError();

    void onSuccess(Comic comic);
}
