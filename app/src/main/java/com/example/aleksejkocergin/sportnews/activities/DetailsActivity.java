package com.example.aleksejkocergin.sportnews.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aleksejkocergin.sportnews.R;
import com.example.aleksejkocergin.sportnews.adapter.ArticleAdapter;
import com.example.aleksejkocergin.sportnews.model.ArticleDetailsData;
import com.example.aleksejkocergin.sportnews.presenter.DetailsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsActivity extends AppCompatActivity implements IDetailsView {

    private DetailsPresenter detailsPresenter;
    private ArticleAdapter mAdapter;

    @BindView(R.id.team1_text)
    TextView tvTeam1;
    @BindView(R.id.team2_text)
    TextView tvTeam2;
    @BindView(R.id.time_text)
    TextView tvTime;
    @BindView(R.id.tournament_text)
    TextView tvTournament;
    @BindView(R.id.place_text)
    TextView tvPlace;
    @BindView(R.id.details_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.prediction)
    TextView tvPrediction;
    @BindView(R.id.details_progress)
    ProgressBar progressBar;
    @BindView(R.id.details_content)
    RelativeLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        if (detailsPresenter == null) detailsPresenter = new DetailsPresenter(this);
        detailsPresenter.getArticleData(getIntent().getStringExtra("article"));
    }

    @Override
    public void setTeamsData(String team1, String team2) {
        tvTeam1.setText(team1);
        tvTeam2.setText(String.format(" — %s", team2));
    }

    @Override
    public void setTimeData(String timeData) {
        tvTime.setText(timeData);
    }

    @Override
    public void setTournamentData(String tournamentData) {
        tvTournament.setText(tournamentData);
    }

    @Override
    public void setPlaceData(String placeData) {
        tvPlace.setText(placeData);
    }

    @Override
    public void setPrediction(String prediction) {
        tvPrediction.setText(prediction);
    }

    @Override
    public void setArticleList(List<ArticleDetailsData> articleDetailsDataList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ArticleAdapter(articleDetailsDataList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void hideLoadingIndicator() {
        content.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
