package com.example.android_cinema_management.Handler;

import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.Model.MovieDetail;

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
                System.out.println(vietnameseTitle);
                String movieDetails = data.select("div.article-watchmovie").select("a").eq(i).attr("href");
                if (vietnameseTitle.equals("Ám Thuật: Xác Sống Săn Mồi")){
                    continue;
                }
                System.out.println("movideLink" + movieDetails);
                list.add(new Movie(vietnameseTitle, englishTitle, movieImage,movieDetails));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getMovieDetails(String url, ArrayList<MovieDetail> list){
        Document doc;
        {
            try {
                doc = Jsoup.connect(url).get();
                Elements data = doc.select("div.row");
                String trailer = doc.select("div.play-bt > galaxy-watch-trailer").attr("ng-trailer");
                String director = data.select("div:first-child > div.detail-info-right").select("a").text();
                String actor = data.select("div:nth-child(2) > div.detail-info-right").select("a").text();
                String producer = data.select("div:nth-child(3) > div.detail-info-right").select("a").text();
                String country = data.select("div:nth-child(4) > div.detail-info-right").select("a").text();
                String category = data.select("div:nth-child(5) > div.detail-info-right").select("a").text();
                String date = data.select("div:nth-child(6) > div.detail-info-right").text();
                list.add(new MovieDetail(director,actor,producer,country,date,trailer,category));
                System.out.println(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
