package com.newsfeed.Repos;

import android.content.Context;

import com.newsfeed.Models.NewsFeed;
import com.newsfeed.Networking.ApiAdapter;
import com.newsfeed.Networking.ApiClient;

import io.reactivex.Observable;

/**
 * Created by ankit on 08/10/17.
 */

public class SearchRepository {
    ApiClient apiClient;

    public SearchRepository(Context context) {
        apiClient = ApiAdapter.getApiClient(context);
    }

    public Observable<NewsFeed> getNewsForQuery(String query, int page) {
        return apiClient.getNews(query, page);
    }
}
