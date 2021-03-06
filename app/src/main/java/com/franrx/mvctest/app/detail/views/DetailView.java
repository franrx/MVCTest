package com.franrx.mvctest.app.detail.views;

public interface DetailView {

    void showProgress();

    void hideProgress();

    void setDescription(String description);

    void setTitle(String title);

    void setPhoto(String url);
}
