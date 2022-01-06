package com.example.android_cinema_management.Model;

import java.util.HashMap;

public class Review {

    private String reviewId;
    private String movieName;
    private String rateMovie;
    private String reviewContent;
    private HashMap<String, Object> user;


    public Review() {
    }

    public Review(String reviewId, String movieName, String rateMovie, String reviewContent, HashMap<String, Object> user) {
        this.reviewId = reviewId;
        this.movieName = movieName;
        this.rateMovie = rateMovie;
        this.reviewContent = reviewContent;
        this.user = user;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getRateMovie() {
        return rateMovie;
    }

    public void setRateMovie(String rateMovie) {
        this.rateMovie = rateMovie;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public HashMap<String, Object> getUser() {
        return user;
    }

    public void setUser(HashMap<String, Object> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + reviewId + '\'' +
                ", movieName='" + movieName + '\'' +
                ", rateMovie='" + rateMovie + '\'' +
                ", reviewContent='" + reviewContent + '\'' +
                ", user=" + user +
                '}';
    }
}
