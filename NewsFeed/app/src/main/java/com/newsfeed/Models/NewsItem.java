package com.newsfeed.Models;

import com.google.gson.annotations.Expose;

/**
 * Created by ankit on 08/10/17.
 */

public class NewsItem {

    private String created_at;

    private String title;

    private String url;

    private String author;

    private Integer points;

    private String story_text;

    private String comment_text;

    private Integer num_comments;

    private String story_id;

    private String story_title;

    private String story_url;

    public String getCreated_at() {
        return created_at;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPoints() {
        return points;
    }

    public String getStory_text() {
        return story_text;
    }

    public String getComment_text() {
        return comment_text;
    }

    public Integer getNum_comments() {
        return num_comments;
    }

    public String getStory_id() {
        return story_id;
    }

    public String getStory_title() {
        return story_title;
    }

    public String getStory_url() {
        return story_url;
    }

    public String getParent_id() {
        return parent_id;
    }

    public Long getCreated_at_i() {
        return created_at_i;
    }

    private String parent_id;

    private Long created_at_i;
}


