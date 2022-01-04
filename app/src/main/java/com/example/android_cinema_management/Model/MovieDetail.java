package com.example.android_cinema_management.Model;

import java.util.ArrayList;
import java.util.List;

public class MovieDetail {
    private String director;
    private String actors;
    private String producers;
    private String country;
    private String releaseDate;
    private String urlVideos;
    private String category;
    private String content;
    private String rate;
    private String duration;
    private List<Cinema> cinemas;
    public MovieDetail() {
    }

    public MovieDetail(String director, String actors, String producers, String country,
                       String releaseDate, String urlVideos, String category,String duration,
                        String rate,String content) {
        this.director = director;
        this.actors = actors;
        this.producers = producers;
        this.country = country;
        this.releaseDate = releaseDate;
        this.urlVideos = urlVideos;
        this.category = category;
        this.content = content;
        this.rate = rate;
        this.duration = duration;
    }

    public MovieDetail(String director, String actors, String producers, String country, String releaseDate, String urlVideos, String category, String content, String rate, String duration, List<Cinema> cinemas) {
        this.director = director;
        this.actors = actors;
        this.producers = producers;
        this.country = country;
        this.releaseDate = releaseDate;
        this.urlVideos = urlVideos;
        this.category = category;
        this.content = content;
        this.rate = rate;
        this.duration = duration;
        this.cinemas = cinemas;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getUrlVideos() {
        return urlVideos;
    }

    public void setUrlVideos(String urlVideos) {
        this.urlVideos = urlVideos;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    // Function to get the Id in the trailer link
    public String getTrailers(ArrayList<MovieDetail> trailer) {
        String finalId = "";
        for (MovieDetail movie : trailer
        ) {
            String video = movie.getUrlVideos();
            String id;
            String[] actualLink = video.split("=");
            for (int i = 0; i < actualLink.length; i++) {
                id = actualLink[1];
                finalId = id.substring(0, id.length() - 1);
            }

        }
        return finalId;
    }


    @Override
    public String toString() {
        return "MovieDetail{" +
                "director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", producers='" + producers + '\'' +
                ", country='" + country + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", urlVideos='" + urlVideos + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", rate='" + rate + '\'' +
                ", duration='" + duration + '\'' +
                ", cinemas=" + cinemas +
                '}';
    }
}
