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

import com.example.android_cinema_management.Adapter.MoviesAdapter;
import com.example.android_cinema_management.Handler.MovieHandler;
import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment1 extends Fragment {
    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private MoviesAdapter moviesAdapter;
    //Declare Movie list
    private ArrayList<Movie> movieList;
    //Declare HandlerThread Thread
    HandlerThread ht = new HandlerThread("MyHandlerThread");
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment1 newInstance(String param1, String param2) {
        HomeFragment1 fragment = new HomeFragment1();
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
        // Binding with xml layout
        View view = inflater.inflate(R.layout.fragment_home1, container, false);
        movieList = new ArrayList<>();
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
            // use grid layout manager to display 2 items in one row
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(gridLayoutManager);
            // Specify an adapter
            recyclerView.setAdapter(moviesAdapter);
        });
    }
}