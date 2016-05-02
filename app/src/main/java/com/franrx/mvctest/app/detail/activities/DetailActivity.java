package com.franrx.mvctest.app.detail.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.franrx.mvctest.app.R;
import com.franrx.mvctest.app.detail.presenters.DetailPresenter;
import com.franrx.mvctest.app.detail.presenters.DetailPresenterImpl;
import com.franrx.mvctest.app.detail.views.DetailView;
import com.franrx.mvctest.app.domain.Result;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends Activity implements DetailView {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.photo_progress)
    ProgressBar progressBar;
    @Bind(R.id.photo)
    ImageView photo;

    private DetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeUI();
        initializePresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDescription(String description) {
        this.description.setText(Html.fromHtml(description));
    }

    @Override
    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void setPhoto(String url) {
        Picasso.with(this).load(url).into(photo, new Callback() {
            @Override
            public void onSuccess() {
                hideProgress();
            }

            @Override
            public void onError() {

            }
        });
    }

    private void initializeUI() {
        setContentView(R.layout.detail_activity);
        ButterKnife.bind(this);
    }

    private void initializePresenter() {
        presenter = new DetailPresenterImpl(this);
        presenter.initializePresenter((Result) this.getIntent().getSerializableExtra("comic"));
    }

}
