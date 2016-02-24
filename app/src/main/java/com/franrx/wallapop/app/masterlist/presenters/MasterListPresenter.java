package com.franrx.wallapop.app.masterlist.presenters;

import android.content.Context;

public interface MasterListPresenter {
    void getComics();

    void onResume(Context context);

    void onDestroy();

    void onElementClick(int position);
}
