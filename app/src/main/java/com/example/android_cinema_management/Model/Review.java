package com.example.android_cinema_management.Model;

import java.util.Map;

public class Review {

    private String id;
    private int like;
    private int dislike;
    private String rate;
    private String review;
    Map<String, User> user;

    public Review() {
    }

    public Review(String id, int like, int dislike, String rate, String review, Map<String, User> user) {
        this.id = id;
        this.like = like;
        this.dislike = dislike;
        this.rate = rate;
        this.review = review;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Map<String, User> getUser() {
        return user;
    }

    public void setUser(Map<String, User> user) {
        this.user = user;
    }
}
