package com.example.aleksejkocergin.sportnews.model;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class MikonaturiApi implements IMikonatouriApi {

    private final static String BASE_URL = "http://mikonatoruri.win/";
    private IMikonatouriService mikonatouriService = getRetrofit().create(IMikonatouriService.class);

    private Retrofit getRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .build();
    }

    @Override
    public Observable<SportNewsData> getSportNews(String category) {
        return mikonatouriService.getSportNewsData(category);
    }

    @Override
    public Observable<ArticleDetailsData> getArticleDetails(String article) {
        return mikonatouriService.getArticleDetails(article);
    }
}
