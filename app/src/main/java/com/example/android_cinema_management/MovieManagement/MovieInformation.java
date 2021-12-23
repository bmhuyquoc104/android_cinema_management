package com.example.android_cinema_management.MovieManagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.android_cinema_management.Handler.MovieHandler;
import com.example.android_cinema_management.Model.MovieDetail;
import com.example.android_cinema_management.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class MovieInformation extends AppCompatActivity {
    //Declare login and register button
    Button loginAndRegister;
    // Declare textview
    TextView vietnameseTitle,englishTitle,releaseDate;
    // Declare imageView
    ImageView movieImage;
    //Declare youtubeView
    YouTubePlayerView movieTrailer;
    //Declare String for video URL
    String movieDetailUrl;
    // Declare new handlerThread
    HandlerThread ht = new HandlerThread("MyHandlerThread");
    // Declare ArrayList
    ArrayList<MovieDetail>movieInformation;
    //Declare class
    MovieDetail movie;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);
        // Binding with xml layout
        loginAndRegister = findViewById(R.id.loginAndRegister);
        vietnameseTitle = findViewById(R.id.mi_vietnameseTitle);
        englishTitle = findViewById(R.id.mi_englishTitle);
        releaseDate = findViewById(R.id.mi_releaseDate);
        movieImage = findViewById(R.id.mi_movieImage);
        movieTrailer = findViewById(R.id.mi_movieTrailer);
        //Initialize movieURL
        movieDetailUrl = "";
        // Initialize list and class
        movieInformation = new ArrayList<>();
        movie = new MovieDetail();
        // Create spannalbe String
        SpannableStringBuilder builder = new SpannableStringBuilder();

        // Text and color for string 1
        SpannableString str1= new SpannableString("Login / ");
        str1.setSpan(new ForegroundColorSpan(Color.rgb(161,161,161)), 0, str1.length(), 0);
        builder.append(str1);

        // Text and color for string 2
        SpannableString str2= new SpannableString("Sign Up");
        str2.setSpan(new ForegroundColorSpan(Color.rgb(222,22,25)), 0, str2.length(), 0);
        builder.append(str2);

        // Set text for button
        loginAndRegister.setText( builder, Button.BufferType.SPANNABLE);

        // Get intent from other activities
        Intent intent = getIntent();

        if(intent != null) {
            if (intent.hasExtra("vietnameseTitle")) {
                String vnTitle = intent.getStringExtra("vietnameseTitle");
                // Set text for vietnamese title textview
                vietnameseTitle.setText("VnTitle: " + vnTitle);
            }
            if (intent.hasExtra("englishTitle")) {
                String enTitle = intent.getStringExtra("englishTitle");
                // Set text for english title textview
                englishTitle.setText("Entitle: " +enTitle);
            }

            if (intent.hasExtra("imageUrl")) {
                String imageUrl = intent.getStringExtra("imageUrl");
                // Set image for image view
                Picasso.get().load(imageUrl).into(movieImage);
            }

            // Get movie detail url from main activity
            if( intent.hasExtra("movieDetailUrl")){
                movieDetailUrl = intent.getStringExtra("movieDetailUrl");
            }
        }
        fetchMovieDetails();
    }

    private void fetchMovieDetails (){
        //Start Handler Thread
        ht.start();
        //Initialize new handler
        Handler asHandler = new Handler(ht.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                //Handle function after receiving message
                renderMovieDetail();
            }
        };
            //Initialize new message
            Message msg = new Message();
            // Scraping data from movieDetailURL
            Runnable runnable = () ->{
            String baseUrl = "https://www.galaxycine.vn";
            String url = baseUrl + movieDetailUrl;
            MovieHandler.getMovieDetails(url,movieInformation);
            System.out.println("huy ne" + movieInformation);
            msg.obj = movieInformation;
            asHandler.sendMessage(msg);
        };
            asHandler.post(runnable);
    }

    @SuppressLint("SetTextI18n")
    // Render movie after getting the data
    private void renderMovieDetail() {
        // Initialize new handler to handle UIThread
        Handler uiThreadHandler = new Handler(getMainLooper());
        uiThreadHandler.post(() -> {
            // Set and load video into youtube view
            getLifecycle().addObserver(movieTrailer);
            movieTrailer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer movieTrailer) {
                    // Get video Id by modifying video URL
                    String videoId = movie.getTrailers(movieInformation);
                    System.out.println("cho t coi cai id" +videoId);
                    // Option to start video by start button
                    movieTrailer.cueVideo(videoId, 0);
                }

            });
            //Get release date in movieInformation list
            for (MovieDetail movie:movieInformation){
                releaseDate.setText("Release Date:" + " " + movie.getReleaseDate());
            }
        });
    }

}