package com.example.android_cinema_management.Model;

public class Movie {
    private String vietnameseTitle;
    private String englishTitle;
    private String urlImage;
    private String movieDetailUrl;

    public Movie(String vietnameseTitle, String englishTitle, String urlImage, String movieDetailUrl) {
        this.vietnameseTitle = vietnameseTitle;
        this.englishTitle = englishTitle;
        this.urlImage = urlImage;
        this.movieDetailUrl = movieDetailUrl;
    }

    public Movie() {
    }

    public String getVietnameseTitle() {
        return vietnameseTitle;
    }

    public void setVietnameseTitle(String vietnameseTitle) {
        this.vietnameseTitle = vietnameseTitle;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getMovieDetailUrl() {
        return movieDetailUrl;
    }

    public void setMovieDetailUrl(String movieDetailUrl) {
        this.movieDetailUrl = movieDetailUrl;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "vietnameseTitle='" + vietnameseTitle + '\'' +
                ", englishTitle='" + englishTitle + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", movieDetailUrl='" + movieDetailUrl + '\'' +
                '}';
    }
}
