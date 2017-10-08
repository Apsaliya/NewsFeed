package com.newsfeed.Presenters;

import com.newsfeed.Models.NewsFeed;

import java.util.ArrayList;

/**
 * Created by ankit on 08/10/17.
 */

public interface SearchPresenter {
    void getNewsForQuery(String query);
    void fetchMoreNews();
}
