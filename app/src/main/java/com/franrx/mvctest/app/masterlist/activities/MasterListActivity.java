package com.franrx.mvctest.app.masterlist.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.franrx.mvctest.app.R;
import com.franrx.mvctest.app.detail.activities.DetailActivity;
import com.franrx.mvctest.app.domain.Result;
import com.franrx.mvctest.app.masterlist.adapters.MasterListAdapter;
import com.franrx.mvctest.app.masterlist.presenters.MasterListPresenter;
import com.franrx.mvctest.app.masterlist.presenters.MasterListPresenterImpl;
import com.franrx.mvctest.app.masterlist.views.MasterListView;
import com.franrx.mvctest.app.masterlist.listeners.RecyclerClickListener;
import com.franrx.mvctest.app.masterlist.utils.RecyclerInsetsDecoration;
import com.franrx.mvctest.app.masterlist.utils.TransitionHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MasterListActivity extends Activity implements MasterListView {

    private static final int THRESHOLD = 5;
    @Bind(R.id.progress)
    ProgressBar progressBar;
    @Bind(R.id.comicList)
    RecyclerView comicList;

    private MasterListPresenter presenter;
    private MasterListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeUI();
        initializePresenter();
        initializeRecyclerView();
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
        progressBar.setVisibility(View.GONE);
    }

    private void initializeUI() {
        setContentView(R.layout.master_list_activity);
        ButterKnife.bind(this);
    }

    private void initializePresenter() {
        presenter = new MasterListPresenterImpl(this);
        presenter.onResume(this);
    }

    private void initializeRecyclerView() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        comicList.setLayoutManager(layoutManager);
        comicList.addItemDecoration(new RecyclerInsetsDecoration(this));
        comicList.addOnScrollListener(mOnScrollListener);
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
            int visibleItemsCount = layoutManager.getChildCount();
            int totalItemsCount = layoutManager.getItemCount();
            int[] positions = new int[2];
            layoutManager.findFirstVisibleItemPositions(positions);
            int firstVisibleItemPos = positions[0];

            if (visibleItemsCount + firstVisibleItemPos >= totalItemsCount - THRESHOLD) {
                presenter.getComics();
            }
        }
    };

    @Override
    public void bindComicsList(List<Result> comics) {
        if(adapter == null) {
            adapter = new MasterListAdapter(this, comics, new RecyclerClickListener() {
                @Override
                public void onElementClick(int position) {
                    presenter.onElementClick(position);
                }
            });
            comicList.setAdapter(adapter);
        } else {
            adapter.setItems(comics);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void showDetailScreen(Result comic) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide enterTransition = new Slide();
            enterTransition.setSlideEdge(Gravity.RIGHT);

            Slide exitTransition = new Slide();
            exitTransition.setSlideEdge(Gravity.LEFT);
            enterTransition.setDuration(500L);
            getWindow().setEnterTransition(enterTransition);
            getWindow().setExitTransition(exitTransition);
            getWindow().setReenterTransition(enterTransition);
            getWindow().setReturnTransition(exitTransition);
        }

        Intent intent = new Intent(this, DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("comic", comic);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
            ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
            startActivity(intent, transitionActivityOptions.toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
    }
}
