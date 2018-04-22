package com.example.aleksejkocergin.sportnews.model;

import rx.Observable;

public interface IMikonatouriApi {

    Observable<SportNewsData> getSportNews(String category);

    Observable<ArticleDetailsData> getArticleDetails(String article);
}
