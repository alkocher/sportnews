package com.example.aleksejkocergin.sportnews.activities;

import com.example.aleksejkocergin.sportnews.model.SportNewsData;

import java.util.List;

public interface IFeedView {

    void setSportNewsListData(List<SportNewsData> sportNewsDataList);
    void updateSportNewsListData(List<SportNewsData> sportNewsDataList);
    void hideLoadingIndicator();
    void showNoConnectionMessage();
    void setEmptyResponseText(String text);
    void hideRefreshLayout();
    void showContent();
}
