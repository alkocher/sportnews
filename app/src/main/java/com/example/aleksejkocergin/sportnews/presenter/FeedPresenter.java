package com.example.aleksejkocergin.sportnews.presenter;

import com.example.aleksejkocergin.sportnews.activities.IFeedView;
import com.example.aleksejkocergin.sportnews.model.MikonaturiApi;
import com.example.aleksejkocergin.sportnews.model.SportNewsData;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FeedPresenter implements IFeedPresenter {

    private final MikonaturiApi mikonaturiApi;
    private final IFeedView view;

    public FeedPresenter(IFeedView view) {
        mikonaturiApi = new MikonaturiApi();
        this.view = view;
    }

    @Override
    public void getSportNewsData(boolean isUpdate, String category) {
        Observable<SportNewsData> dataObservable = mikonaturiApi.getSportNews(category);

        dataObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sportNewsData ->
                {
                   List<SportNewsData> sportNewsDataList = new ArrayList<>(sportNewsData.getEvents());
                   view.setSportNewsListData(sportNewsDataList);

                   view.hideLoadingIndicator();
                   view.hideRefreshLayout();
                   view.showContent();

                   if (sportNewsDataList.isEmpty()) view.setEmptyResponseText("Nothing found.");
                   else if(isUpdate) view.updateSportNewsListData(sportNewsDataList);
                   else view.setSportNewsListData(sportNewsDataList);
                });
    }
}