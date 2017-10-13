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
    private SearchRepository searchRepository;
    private String searchText;
    private int currentPage;

    public SearchPresenterImpl(SearchView searchView, SearchRepository searchRepository) {
        this.searchView = searchView;
        this.searchRepository = searchRepository;
    }

    @Override
    public void getNewsForQuery(String query) {
        searchText = query;
        Log.d(TAG, "query : " + query);
        Observable<NewsFeed> newsFeedObservable = searchRepository.getNewsForQuery(query, 0);
        newsFeedObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsFeed>() {
                    @Override
                    public void onNext(@NonNull NewsFeed newsFeed) {
                        currentPage = 0;
                        searchView.onNewsReceived(newsFeed);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "error fetching searchResults");
                        // parse your error here
                        searchView.onError(e.getMessage());
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
                        Log.d(TAG, "total page : " + newsFeed.getTotalPages());
                        Log.d(TAG, "page : " + newsFeed.getPage());

                        if (newsFeed.getPage().equals(newsFeed.getTotalPages())) {
                            searchView.onLastPageReached();
                        }
                        searchView.onMoreNews(newsFeed);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "error fetching searchResults");
                        // parse your error here
                        searchView.onError(e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "news fetched successfully");
                    }
                });
    }

    public SearchView getView() {
        return this.searchView;
    }
    public void attachView(SearchView view) {
        this.searchView = view;
    }

}
