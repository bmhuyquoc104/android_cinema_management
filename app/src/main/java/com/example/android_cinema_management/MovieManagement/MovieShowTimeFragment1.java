package com.example.android_cinema_management.MovieManagement;

import static android.os.Looper.getMainLooper;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.Accounts;
import com.example.android_cinema_management.Adapter.VoucherAdapter;
import com.example.android_cinema_management.CinemaManagement.CinemaFragment;
import com.example.android_cinema_management.Handler.MovieHandler;
import com.example.android_cinema_management.HomeFragment;
import com.example.android_cinema_management.MainActivity;
import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.Model.MovieDetail;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.UserReview;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;

import java.util.ArrayList;


public class MovieShowTimeFragment1 extends Fragment {
    // Declare textview
    TextView vietnameseTitle,englishTitle,duration,rate;
    // Declare imageView
    ImageView movieImage;
    //Declare youtubeView
    YouTubePlayerView movieTrailer;
    //Declare String for video URL
    String movieDetailUrl;
    // Declare new handlerThread
    HandlerThread ht = new HandlerThread("MyHandlerThread");
    // Declare public static ArrayList to use later by other classes
    public static ArrayList<MovieDetail> movieInformation;
    //Declare class
    MovieDetail movieDetail;
    //Declare login and register button
    Movie movie;
    //Declare Movie list
    ArrayList<Cinema> showTimeCinemaList;
    //Declare button
    Button review;
    public MovieShowTimeFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_show_time1, container, false);
        vietnameseTitle = view.findViewById(R.id.mi_vietnameseTitle);
        englishTitle = view.findViewById(R.id.mi_englishTitle);
        duration = view.findViewById(R.id.mi_duration);
        movieImage = view.findViewById(R.id.mi_movieImage);
        movieTrailer = view.findViewById(R.id.mi_movieTrailer);
        rate = view.findViewById(R.id.mi_rate);
        review = view.findViewById(R.id.movie_review);
        showTimeCinemaList = CinemaFragment.cinemaArrayList;
        //Initialize movieURL
        movieDetailUrl = "";
        // Initialize list and class
        movieInformation = new ArrayList<>();
        movieDetail = new MovieDetail();
        movie = MovieInfoTabLayout.currentMovie;
        FragmentManager fm = getParentFragmentManager();
        // Set fixed size for recycler view

//        showTimeAdapter = new ShowTimeAdapter(getContext(),showTimeCinemaList);
//        // use  layout manager
//        recyclerView.setLayoutManager(layoutManager);
//        // Specify an adapter
//        recyclerView.setAdapter(showTimeAdapter);
        // Function to go back to previous activity

        fetchMovieDetails();

        Spanned message = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
                .setHtml(
                        "<h3> You are currently not logged in. </p>" +
                                "<p>Please register an account and login to  leave a review for the movie " +
                                "<h3> Thank you for using our service! </h3>"));


            review.setOnClickListener(View -> {
                if (!MainActivity.isLogin) {
                    VoucherAdapter.openSuccessfulDialog(R.raw.required_login, message, getContext());
                }
                else{
                    Intent intent = new Intent(getContext(), UserReview.class);
                    startActivity(intent);
                }
            });
        return view;
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
            movieDetailUrl = movie.getMovieDetailUrl();
            String url = baseUrl + movieDetailUrl;
            MovieHandler.getMovieDetails(url,movieInformation);
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
//                    String videoId = movieDetail.getTrailers(movieInformation);
                        for (MovieDetail movie : movieInformation
                        ) {
                            String videoId ="";
                            String video = movie.getUrlVideos();
                            // If video do not start with an https, prompt the user that video is not available
                            if (video.startsWith("\"https://")){
                                // IDE system out ""string"" -> need to put condition in two double double quotation
                                System.out.println("test1" + true);
                                 videoId = movieDetail.getTrailers(movieInformation);
                                // Option to start video by start button
                                movieTrailer.cueVideo(videoId, 0);
                            }
                            else {
                                // Announce the user
                                Toast.makeText(getContext(), "Trailer for this movie is not available", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                });
            //Get release date in movieInformation list
            for (MovieDetail movie:movieInformation){
                duration.setText("Duration:" + " " + movie.getDuration());
                rate.setText("Rate:" + " " + movie.getRate());
            }
            vietnameseTitle.setText(movie.getVietnameseTitle());
            englishTitle.setText(movie.getEnglishTitle());
            Picasso.get().load(movie.getUrlImage()).into(movieImage);
        });
    }

}