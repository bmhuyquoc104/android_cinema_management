package com.example.android_cinema_management.HomeManagement;

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
import android.widget.Button;

import com.example.android_cinema_management.Adapter.MoviesAdapter;
import com.example.android_cinema_management.Handler.MovieHandler;
import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.R;

import java.util.ArrayList;

public class HomeFragment2 extends Fragment {
    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private MoviesAdapter moviesAdapter;
    //Declare Movie list
    public static ArrayList<Movie> upcomingMovieList;
    //Declare HandlerThread Thread
    HandlerThread ht = new HandlerThread("MyHandlerThread");

    public HomeFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Binding with xml layout
        View view = inflater.inflate(R.layout.fragment_home2, container, false);
        upcomingMovieList = new ArrayList<>();
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
            String url = "https://www.galaxycine.vn/phim-sap-chieu";
            MovieHandler.getUpcomingMovieData(url,upcomingMovieList);
            msg.obj = upcomingMovieList;
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
            moviesAdapter = new MoviesAdapter(upcomingMovieList,getContext());
            // use grid layout manager to display 2 items in one row
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(gridLayoutManager);
            // Specify an adapter
            recyclerView.setAdapter(moviesAdapter);
        });
    }
}