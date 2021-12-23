package com.example.android_cinema_management.Model;

import java.util.ArrayList;

public class MovieDetail {
    private String director;
    private String actors;
    private String producers;
    private String country;
    private String releaseDate;
    private String urlVideos;
    private String category;

    public MovieDetail() {
    }

    public MovieDetail(String director, String actors, String producers, String country, String releaseDate, String urlVideos, String category) {
        this.director = director;
        this.actors = actors;
        this.producers = producers;
        this.country = country;
        this.releaseDate = releaseDate;
        this.urlVideos = urlVideos;
        this.category = category;
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
        return "MoviesDetail{" +
                "director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", producers='" + producers + '\'' +
                ", country='" + country + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", urlVideos='" + urlVideos + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
