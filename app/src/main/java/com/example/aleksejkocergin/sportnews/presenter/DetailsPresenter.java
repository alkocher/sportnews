package com.example.aleksejkocergin.sportnews.presenter;

import com.example.aleksejkocergin.sportnews.activities.IDetailsView;
import com.example.aleksejkocergin.sportnews.model.ArticleDetailsData;
import com.example.aleksejkocergin.sportnews.model.MikonaturiApi;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailsPresenter implements IDetailsPresenter {

private final MikonaturiApi mikonaturiApi;
private final IDetailsView view;

    public DetailsPresenter(IDetailsView view) {
        mikonaturiApi = new MikonaturiApi();
        this.view = view;
    }

    @Override
    public void getArticleData(String article) {
        Observable<ArticleDetailsData> dataObservable = mikonaturiApi.getArticleDetails(article);

        dataObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articleDetailsData ->
                {
                    view.hideLoadingIndicator();
                    view.setTeamsData(articleDetailsData.getTeam1(),
                            articleDetailsData.getTeam2());
                    view.setTimeData(articleDetailsData.getTime());
                    view.setTournamentData(articleDetailsData.getTournament());
                    view.setPlaceData(articleDetailsData.getPlace());
                    view.setArticleList(articleDetailsData.getArticle());
                    view.setPrediction(articleDetailsData.getPrediction());
                });
    }
}
