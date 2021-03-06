package com.example.android_cinema_management.HomeManagement;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_cinema_management.Adapter.MoviesAdapter;
import com.example.android_cinema_management.Footer.AboutUs;
import com.example.android_cinema_management.Footer.Contact;
import com.example.android_cinema_management.Footer.CopyRight;
import com.example.android_cinema_management.Footer.Job;
import com.example.android_cinema_management.Handler.MovieHandler;
import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.R;

import java.util.ArrayList;

public class HomeFragment1 extends Fragment {
    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private MoviesAdapter moviesAdapter;
    //Declare Movie list
    public static ArrayList<Movie> movieList;
    public static ArrayList<String> movieTitleList;
    //Declare HandlerThread Thread
    HandlerThread ht = new HandlerThread("MyHandlerThread");



    public HomeFragment1() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Binding with xml layout
        View view = inflater.inflate(R.layout.fragment_home1, container, false);
        movieList = new ArrayList<>();
        movieTitleList = new ArrayList<>();
        // Set fixed size for recycler view
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);



        fetchAndRenderMovie();
        return view;
    }

    private void fetchAndRenderMovie(){
        //Start the handler thread
        ht.start();
        //Initialize new handler
        Handler asHandler = new Handler(ht.getLooper()){
            //Handle function after receiving message
            @Override
            public void handleMessage(@NonNull Message msg) {
                renderMovieList();
            }
        };

        //Initialize new message
        Message msg = new Message();

        // Handle scrapping data
        Runnable runnable = () ->{
            String url = "https://www.galaxycine.vn/phim-dang-chieu";
            MovieHandler.getMovieData(url,movieList);
            msg.obj = movieList;
            asHandler.sendMessage(msg);
        };
        asHandler.post(runnable);
    }

    /** Function to render the movie list in UI
     *
     * */
    public void renderMovieList(){
        // Initialize new ui handler
        Handler uiThreadHandler = new Handler(Looper.getMainLooper());

        // Read post from fetchAndRenderMovie
        uiThreadHandler.post(() ->{
            moviesAdapter = new MoviesAdapter(movieList,getContext());
            for (Movie movie:movieList
                 ) {
                movieTitleList.add(movie.getEnglishTitle());
                System.out.println("hello huy ne " + movieTitleList);
            }
            // use grid layout manager to display 2 items in one row
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(gridLayoutManager);
            // Specify an adapter
            recyclerView.setAdapter(moviesAdapter);
        });
    }
}