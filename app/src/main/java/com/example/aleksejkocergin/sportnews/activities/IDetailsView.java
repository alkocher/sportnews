package com.example.aleksejkocergin.sportnews.activities;

import com.example.aleksejkocergin.sportnews.model.ArticleDetailsData;

import java.util.List;

public interface IDetailsView {
    void setTeamsData(String team1, String team2);
    void setTimeData(String timeData);
    void setTournamentData(String tournamentData);
    void setPlaceData(String placeData);
    void setPrediction(String prediction);
    void setArticleList(List<ArticleDetailsData> articleDetailsDataList);
    void hideLoadingIndicator();
}
