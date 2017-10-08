package com.newsfeed.Networking;

import com.newsfeed.Models.NewsFeed;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ankit on 08/10/17.
 */

public interface ApiClient {

    @GET("search")
    Observable<NewsFeed> getNews(@Query("query") String query, @Query("page") int pageToBeFetched);
}
