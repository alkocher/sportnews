package com.example.aleksejkocergin.sportnews.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.CollapsingToolbarLayout;
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
    private String category = "football";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()){
            if (presenter == null) presenter = new FeedPresenter(this);
                presenter.getSportNewsData(false, category);
        } else {
            showNoConnectionMessage();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.category_football:
                category = "football";
                imageCategories.setImageResource(R.drawable.football_image);
                presenter.getSportNewsData(false, category);
                break;
            case R.id.category_hockey:
                category = "hockey";
                imageCategories.setImageResource(R.drawable.hockey_image);
                presenter.getSportNewsData(false, category);
                break;
            case R.id.category_tennis:
                category = "tennis";
                imageCategories.setImageResource(R.drawable.tennis_image);
                presenter.getSportNewsData(false, category);
                break;
            case R.id.category_volleyball:
                category = "volleyball";
                imageCategories.setImageResource(R.drawable.volleyball_image);
                presenter.getSportNewsData(false, category);
                break;
            case R.id.category_cybersport:
                category = "cybersport";
                imageCategories.setImageResource(R.drawable.cybersport);
                presenter.getSportNewsData(false, category);
                break;
            default:
                presenter.getSportNewsData(false, category);
        }
        collapsingToolbar.setTitle(category);
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

    @Override
    public void setEmptyResponseText(String text) {

    }
}
