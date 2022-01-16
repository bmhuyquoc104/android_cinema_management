package com.example.android_cinema_management.Model;

import java.util.HashMap;

public class Review {

    private String reviewId;
    private String movieName;
    private String rateMovie;
    private String reviewContent;
    private String date;
    private String time;
    private String like;
    private String dislike;
    private HashMap<String, Object> user;


    public Review() {
    }

    public Review(String reviewId, String movieName, String rateMovie, String reviewContent, String date, String time, String like, String dislike, HashMap<String, Object> user) {
        this.reviewId = reviewId;
        this.movieName = movieName;
        this.rateMovie = rateMovie;
        this.reviewContent = reviewContent;
        this.date = date;
        this.time = time;
        this.like = like;
        this.dislike = dislike;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
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
                "reviewId='" + reviewId + '\'' +
                ", movieName='" + movieName + '\'' +
                ", rateMovie='" + rateMovie + '\'' +
                ", reviewContent='" + reviewContent + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", like='" + like + '\'' +
                ", dislike='" + dislike + '\'' +
                ", user=" + user +
                '}';
    }
}
