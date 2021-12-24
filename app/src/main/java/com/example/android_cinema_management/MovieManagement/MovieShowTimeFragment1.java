package com.example.android_cinema_management.MovieManagement;

import static android.os.Looper.getMainLooper;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.Handler.MovieHandler;
import com.example.android_cinema_management.MainActivity2;
import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.Model.MovieDetail;
import com.example.android_cinema_management.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieShowTimeFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieShowTimeFragment1 extends Fragment {
    // Declare textview
    TextView vietnameseTitle,englishTitle,duration,rate;
    // Declare imageView
    ImageView movieImage, goback;
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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieShowTimeFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieShowTimeFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieShowTimeFragment1 newInstance(String param1, String param2) {
        MovieShowTimeFragment1 fragment = new MovieShowTimeFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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
        goback = view.findViewById(R.id.back);
        //Initialize movieURL
        movieDetailUrl = "";
        // Initialize list and class
        movieInformation = new ArrayList<>();
        movieDetail = new MovieDetail();
        movie = MovieInfoTabLayout.currentMovie;

        // Function to go back to previous activity
        goback.setOnClickListener ( View ->{
            Intent intent = new Intent(getContext(), MainActivity2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            try {
               startActivity(intent);
            }
            catch (ActivityNotFoundException e){
                Toast.makeText(getContext(),"Oops!! Something wrong, Please try again!" ,Toast.LENGTH_LONG).show();
            }
        });
        fetchMovieDetails();
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