package com.example.android_cinema_management.Handler;

import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.Model.MovieDetail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MovieHandler {
    public static void getMovieData(String url, ArrayList<Movie> list) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements data = doc.select("div#tab_onshow > div:first-child > div.watchmovie-item");
            for (int i = 0; i < data.size(); i++) {
                String movieImage = data.select("div.article-watchmovie")
                        .select("img").eq(i).attr("src");
                String englishTitle = data.select("div.title-watchmovie > h4:first-child").eq(i).text();
                String vietnameseTitle = data.select("div.title-watchmovie > h4.vn.upper-text").eq(i).text();
                System.out.println(vietnameseTitle);
                String movieDetails = data.select("div.article-watchmovie").select("a").eq(i).attr("href");
                if (vietnameseTitle.equals("Ám Thuật: Xác Sống Săn Mồi")) {
                    continue;
                }
                System.out.println("movideLink" + movieDetails);
                list.add(new Movie(vietnameseTitle, englishTitle, movieImage, movieDetails));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getMovieDetails(String url, ArrayList<MovieDetail> list) {
        Document doc;
        String category = "";
        String director = "";
        String producer = "";
        String actors = "";
        String country = "";
        String date = "";
        {
            try {
                doc = Jsoup.connect(url).get();
                Elements data = doc.select("div.detail-info-row");
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).select("label").text().equals("Đạo diễn:")) {
                        director = data.get(i).select("div.detail-info-right").select("a").text();
                    }
                    if (data.get(i).select("label").text().equals("Nhà sản xuất:")) {
                        producer = data.get(i).select("div.detail-info-right").select("a").text();
                    }
                    if (data.get(i).select("label").text().equals("Diễn viên:")) {
                        actors = data.get(i).select("div.detail-info-right").select("a").text();
                    }
                    if (data.get(i).select("label").text().equals("Quốc gia:")) {
                        country = data.get(i).select("div.detail-info-right").select("a").text();
                    }
                    if (data.get(i).select("label").text().equals("Ngày:")) {
                        date = data.get(i).select("div.detail-info-right").text();
                    }
                    if (data.get(i).select("label").text().equals("Thể loại:")) {
                        category = data.get(i).select("div.detail-info-right").select("a").text();
                    }
                }
                Elements data2 = doc.select("div.content-text > section#info > div.content-text");
                String trailer = doc.select("div.play-bt > galaxy-watch-trailer").attr("ng-trailer");
                String content = data2.select("p > span").text();
                String rate = doc.select("div.rating-value.detail > strong").text();
                String scale = doc.select("div.rating-value.detail > span").text();
                String[] temp = rate.split(" ");
                String finalRate = temp[2].substring(1, 4) + scale;
                String duration = doc.select("div.detail-rating > span").text();
                list.add(new MovieDetail(director, actors, producer, country, date, trailer, category, duration, finalRate, content));
                System.out.println(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
