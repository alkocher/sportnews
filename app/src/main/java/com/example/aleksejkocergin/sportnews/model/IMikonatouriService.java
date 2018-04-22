package com.example.aleksejkocergin.sportnews.model;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface IMikonatouriService {

    @GET("list.php?")
    Observable<SportNewsData> getSportNewsData(@Query("category") String category);

    @GET("post.php?")
    Observable<ArticleDetailsData> getArticleDetails(@Query("article") String article);
}
