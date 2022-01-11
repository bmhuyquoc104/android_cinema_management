package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.android_cinema_management.CinemaManagement.CinemaFragment;
import com.example.android_cinema_management.HomeManagement.HomeFragment1;
import com.example.android_cinema_management.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class BuyByMovieFragment extends Fragment {
    //Declare movie,date,time,cinema array options
    ArrayList<String> moviesArray = new ArrayList<>();
    ArrayList<String> cinemaArray = new ArrayList<>();
    ArrayList<String> timeArray = new ArrayList<>();
    ArrayList<String> dateArray = new ArrayList<>();
    //Declare text input layout
    TextInputLayout movie,cinema,date,time;
    String movieChosen,dateChosen,timeChosen,cinemaChosen;
    //Declare array adapter
    ArrayAdapter<String> movieAdapterItems;
    ArrayAdapter<String> cinemaAdapterItems;
    ArrayAdapter<String> dateAdapterItems;
    ArrayAdapter<String> timeAdapterItems;
    // Declare auto complete text view
    AutoCompleteTextView movieAutoCompleteTextView,cinemaAutoCompleteTextView,dateAutoCompleteTextView,timeAutoCompleteTextView;
    public BuyByMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_buy_by_movie, container, false);
        //Initialize value for movieArray,cinemaArray,dateArray,timeArray
        moviesArray = HomeFragment1.movieTitleList;
        cinemaArray = CinemaFragment.cinemaNameList;
        //Binding xml value and set the dropdown for movie
        movie = view.findViewById(R.id.buy_by_movie_movie_text_layout);
        movieAdapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, moviesArray);
        movieAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_movie_auto_complete_text);
        movieAutoCompleteTextView.setAdapter(movieAdapterItems);
        movieAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                movieChosen = parent.getItemAtPosition(position).toString();
            }
        });

        cinema = view.findViewById(R.id.buy_by_movie_cinema_text_layout);
        cinemaAdapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, cinemaArray);
        cinemaAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_cinema_auto_complete_text);
        cinemaAutoCompleteTextView.setAdapter(cinemaAdapterItems);
        cinemaAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cinemaChosen = parent.getItemAtPosition(position).toString();
            }
        });


        return view;
    }
}