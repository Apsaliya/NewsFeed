package com.newsfeed.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ankit on 08/10/17.
 */

public class NewsFeed {
    @SerializedName("hits")
    private ArrayList<NewsItem> newsItems;

    @SerializedName("nbHits")
    private Integer noOfHits;

    @SerializedName("page")
    private Integer page;

    @SerializedName("nbPages")
    private Integer totalPages;

    @SerializedName("hitsPerPage")
    private Integer noOfHitsPerPage;

    @SerializedName("query")
    private String query;

    public ArrayList<NewsItem> getNewsItems() {
        return newsItems;
    }

    public Integer getNoOfHits() {
        return noOfHits;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getNoOfHitsPerPage() {
        return noOfHitsPerPage;
    }

    public String getQuery() {
        return query;
    }
}
