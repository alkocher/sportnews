package com.example.aleksejkocergin.sportnews.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aleksejkocergin.sportnews.R;
import com.example.aleksejkocergin.sportnews.adapter.FeedAdapter;
import com.example.aleksejkocergin.sportnews.model.SportNewsData;
import com.example.aleksejkocergin.sportnews.presenter.FeedPresenter;
import com.example.aleksejkocergin.sportnews.presenter.IFeedPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedActivity extends AppCompatActivity implements IFeedView {

    private FeedAdapter mAdapter;
    private IFeedPresenter presenter;
    private String CATEGORY = "football";

    @BindView(R.id.feed_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_feed)
    ProgressBar progressFeed;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_view)
    ImageView imageCategories;
    @BindView(R.id.feedCoordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        refreshLayout.setOnRefreshListener(this::initialize);
        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mRecyclerView.setVisibility(View.GONE);
        progressFeed.setVisibility(View.VISIBLE);
        switch (item.getItemId()) {
            case R.id.category_football:
                CATEGORY = "Football";
                break;
            case R.id.category_hockey:
                CATEGORY = "Hockey";
                break;
            case R.id.category_tennis:
                CATEGORY = "Tennis";
                break;
            case R.id.category_basketball:
                CATEGORY = "Basketball";
                break;
            case R.id.category_volleyball:
                CATEGORY = "Volleyball";
                break;
            case R.id.category_cybersport:
                CATEGORY = "Cybersport";
                break;
        }
        collapsingToolbar.setTitle(CATEGORY);
        if (isOnline()) presenter.getSportNewsData(false, CATEGORY.toLowerCase());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setSportNewsListData(List<SportNewsData> sportNewsDataList) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration decoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        mAdapter = new FeedAdapter(sportNewsDataList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(decoration);

        mAdapter.setOnItemClickListener((view, position, article) -> {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("article", article);
            startActivity(intent);
        });
    }

    @Override
    public void updateSportNewsListData(List<SportNewsData> sportNewsDataList) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideLoadingIndicator() {
        progressFeed.setVisibility(View.GONE);
    }

    @Override
    public void showNoConnectionMessage() {
        Toast.makeText(this, "Error connection", Toast.LENGTH_LONG).show();
    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    @Override
    public void setEmptyResponseText(String text) {

    }

    @Override
    public void hideRefreshLayout() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showContent() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void initialize() {
        if (isOnline()){
            if (presenter == null) presenter = new FeedPresenter(this);
            presenter.getSportNewsData(false, CATEGORY);
        } else {
            showNoConnectionMessage();
        }

        refreshLayout.setColorSchemeResources(
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }
}
