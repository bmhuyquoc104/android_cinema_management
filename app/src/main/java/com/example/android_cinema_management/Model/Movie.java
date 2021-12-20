package com.example.android_cinema_management.Model;

public class Movie {
    private String vietnameseTitle;
    private String englishTitle;
    private String urlImage;
    private String link;

    public Movie(String vietnameseTitle, String englishTitle, String urlImage, String link) {
        this.vietnameseTitle = vietnameseTitle;
        this.englishTitle = englishTitle;
        this.urlImage = urlImage;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "vietnameseTitle='" + vietnameseTitle + '\'' +
                ", englishTitle='" + englishTitle + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
