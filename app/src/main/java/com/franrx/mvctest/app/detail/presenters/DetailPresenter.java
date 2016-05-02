package com.franrx.mvctest.app.detail.presenters;

import com.franrx.mvctest.app.domain.Result;

public interface DetailPresenter {

    void onResume();

    void onDestroy();

    void initializePresenter(Result comic);
}
