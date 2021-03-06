package com.franrx.mvctest.app.masterlist.views;

import com.franrx.mvctest.app.domain.Result;
import java.util.List;

public interface MasterListView {
    void showProgress();

    void hideProgress();

    void bindComicsList(List<Result> comics);

    void showDetailScreen(Result comic);

    void showError();
}
