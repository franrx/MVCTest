package com.franrx.wallapop.app.masterlist.presenters;

import android.content.Context;

import com.franrx.wallapop.app.domain.Comic;
import com.franrx.wallapop.app.domain.Result;
import com.franrx.wallapop.app.masterlist.interactors.MasterListInteractor;
import com.franrx.wallapop.app.masterlist.interactors.MasterListInteractorImpl;
import com.franrx.wallapop.app.masterlist.listeners.OnComicsFetchFinishedListener;
import com.franrx.wallapop.app.masterlist.views.MasterListView;

import java.util.ArrayList;
import java.util.List;

public class MasterListPresenterImpl implements MasterListPresenter, OnComicsFetchFinishedListener {
    private Context context;
    private Integer offset = 0;
    private MasterListView masterListView;
    private MasterListInteractor masterListInteractor;
    private static String CHARACTER_ID = "1009368";
    private boolean isRequestRunning = false;
    private List<Result> comics;

    public MasterListPresenterImpl(MasterListView masterListView) {
        this.masterListView = masterListView;
        this.masterListInteractor = new MasterListInteractorImpl();
    }

    @Override
    public void getComics() {
        if (masterListView != null && comics.size() == 0) {
            masterListView.showProgress();
        }
        if (!isRequestRunning) {
            askForComics(offset);
        }
    }

    private void askForComics(Integer offset) {
        isRequestRunning = true;
        masterListInteractor.getComics(CHARACTER_ID, offset, context, this);
    }

    @Override
    public void onResume(Context context) {
        this.context = context;
        comics = new ArrayList<>();
        getComics();
    }

    @Override
    public void onDestroy() {
        masterListView = null;
    }

    @Override
    public void onError() {
        isRequestRunning = false;
        if (masterListView != null) {
            masterListView.hideProgress();
            masterListView.showError();
        }
    }

    @Override
    public void onSuccess(Comic comic) {
        isRequestRunning = false;
        offset = comic.getData().getOffset() + comic.getData().getLimit();
        comics.addAll(comic.getData().getResults());
        if (masterListView != null) {
            masterListView.hideProgress();
            masterListView.bindComicsList(comics);
        }
    }

    @Override
    public void onElementClick(int position) {
        Result comic = comics.get(position);
        masterListView.showDetailScreen(comic);
    }
}
