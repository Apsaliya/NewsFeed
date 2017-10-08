package com.newsfeed.PresenterImpls;

import android.util.Log;

import com.newsfeed.Models.NewsFeed;
import com.newsfeed.Presenters.SearchPresenter;
import com.newsfeed.Repos.SearchRepository;
import com.newsfeed.ViewInterfaces.SearchView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ankit on 08/10/17.
 */

public class SearchPresenterImpl implements SearchPresenter {
    private SearchView searchView;
    private final String TAG = SearchPresenterImpl.class.getSimpleName();
    private SearchRepository searchRepository = new SearchRepository();
    private String searchText;
    private int currentPage;

    @Override
    public void getNewsForQuery(String query) {
        searchText = query;
        Observable<NewsFeed> newsFeedObservable = searchRepository.getNewsForQuery(query, 0);
        newsFeedObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsFeed>() {
                    @Override
                    public void onNext(@NonNull NewsFeed newsFeed) {
                        searchView.onNewsReceived(newsFeed);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "error fetching searchResults");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "news fetched successfully");
                    }
                });
    }

    @Override
    public void fetchMoreNews() {
        Observable<NewsFeed> newsFeedObservable = searchRepository.getNewsForQuery(searchText,  currentPage + 1);
        newsFeedObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsFeed>() {
                    @Override
                    public void onNext(@NonNull NewsFeed newsFeed) {
                        currentPage += 1;
                        if (newsFeed.getPage() == newsFeed.getTotalPages()) {
                            searchView.onLastPageReached();
                        }
                        searchView.onNewsReceived(newsFeed);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "error fetching searchResults");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "news fetched successfully");
                    }
                });
    }
}
