package com.franrx.wallapop.app.detail.presenters;

import com.franrx.wallapop.app.detail.views.DetailView;
import com.franrx.wallapop.app.domain.Result;

import java.util.Random;

public class DetailPresenterImpl implements DetailPresenter {
    private DetailView detailView;
    private Result comic;

    public DetailPresenterImpl(DetailView detailView) {
        this.detailView = detailView;
    }


    @Override public void onResume() {
        if (detailView != null) {
            detailView.showProgress();
        }

        if(comic.getDescription() != null) {
            detailView.setDescription(comic.getDescription());
        }

        if(comic.getTitle() != null) {
            detailView.setTitle(comic.getTitle());
        }

        String url;
        if(comic.getImages().size() > 0) {
            Random random = new Random();
            int index = random.nextInt(comic.getImages().size());
            url = comic.getImages().get(index).getPath().concat(".").concat(comic.getImages().get(index).getExtension());
        } else {
            url = comic.getThumbnail().getPath().concat(".").concat(comic.getThumbnail().getExtension());
        }
        detailView.setPhoto(url);
    }

    @Override public void onDestroy() {
        detailView = null;
    }

    @Override
    public void initializePresenter(Result comic) {
        this.comic = comic;
    }

}
