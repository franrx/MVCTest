package com.franrx.wallapop.app.masterlist.listeners;

import com.franrx.wallapop.app.domain.Comic;

public interface OnComicsFetchFinishedListener {

    void onError();

    void onSuccess(Comic comic);
}
