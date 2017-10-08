package com.newsfeed.ViewInterfaces;

import com.newsfeed.Models.NewsFeed;

/**
 * Created by ankit on 08/10/17.
 */

public interface SearchView {
    void onNewsReceived(NewsFeed newsFeed);
    void onLastPageReached();
}
