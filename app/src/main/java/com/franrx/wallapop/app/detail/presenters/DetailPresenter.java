package com.franrx.wallapop.app.detail.presenters;

import com.franrx.wallapop.app.domain.Result;

public interface DetailPresenter {

    void onResume();

    void onDestroy();

    void initializePresenter(Result comic);
}
