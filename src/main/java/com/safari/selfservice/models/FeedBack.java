package com.safari.selfservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class FeedBack {

    @Id
    private Integer id;
    private Integer userId;
    private String activity;
    private Integer rating;
    private Date date;
    private String feedBack;

    public FeedBack() {
    }

    public FeedBack(String activity, Integer rating, Date date, String feedBack, Integer userId) {
        this.activity = activity;
        this.rating = rating;
        this.date = date;
        this.feedBack = feedBack;
        this.userId = userId;
    }

    public FeedBack(Integer id, String activity, Integer rating, Date date, String feedBack, Integer userId) {
        this.id = id;
        this.activity = activity;
        this.rating = rating;
        this.date = date;
        this.feedBack = feedBack;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }
}
