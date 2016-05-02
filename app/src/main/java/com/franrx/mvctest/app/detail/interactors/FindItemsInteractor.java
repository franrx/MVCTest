package com.franrx.mvctest.app.detail.interactors;

import com.franrx.mvctest.app.detail.listeners.OnFinishedListener;

public interface FindItemsInteractor {

    void findItems(OnFinishedListener listener);
}
