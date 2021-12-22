package com.example.android_cinema_management.Handler;

import com.example.android_cinema_management.Model.Movie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MovieHandler {
    public static void getMovieData(String url, ArrayList<Movie> list){
        try {
            Document doc = Jsoup.connect(url).get();
            Elements data = doc.select("div#tab_onshow > div:first-child > div.watchmovie-item");
            for (int i = 0; i <data.size(); i++) {
                String movieImage = data.select("div.article-watchmovie")
                        .select("img").eq(i).attr("src");
                String englishTitle = data.select("div.title-watchmovie > h4:first-child").eq(i).text();
                String vietnameseTitle = data.select("div.title-watchmovie > h4.vn.upper-text").eq(i).text();
                String movieDetails = data.select("div.article-watchmovie").select("a").attr("href");
                list.add(new Movie(vietnameseTitle, englishTitle, movieImage,movieDetails));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
