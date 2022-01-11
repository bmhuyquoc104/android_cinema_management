package com.example.android_cinema_management.UserManagement;

import android.annotation.SuppressLint;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class BuyByMovieFragment extends Fragment {
    //Declare movie,date,time,cinema array options
    ArrayList<String> moviesArray = new ArrayList<>();
    ArrayList<String> cinemaArray = new ArrayList<>();
    ArrayList<String> timeArray = new ArrayList<>();
    ArrayList<String> dummyTimeDataArray = new ArrayList<>();
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

        @SuppressLint("SimpleDateFormat")  SimpleDateFormat df = new SimpleDateFormat("EEEE,yyyy-MM-dd");

        Date today = new Date();
        String todayFormat = df.format(today);
        dateArray.add(todayFormat);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < 6;i++) {
            cal.add(Calendar.DATE, 1);
            System.out.println(cal);
            Date nextDates = cal.getTime();
            String nextDatesInFormat = df.format(nextDates);
            dateArray.add(nextDatesInFormat);
        }
        System.out.println(dateArray);
        date = view.findViewById(R.id.buy_by_movie_date_text_layout);
        dateAdapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, dateArray);
        dateAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_date_auto_complete_text);
        dateAutoCompleteTextView.setAdapter(dateAdapterItems);
        dateAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dateChosen = parent.getItemAtPosition(position).toString();
            }
        });

        Date currentTime = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df2 = new SimpleDateFormat("kk:mm");
        String currentTimeFormat = df2.format(currentTime);

        dummyTimeDataArray.add("08:00");
        dummyTimeDataArray.add("10:30");
        dummyTimeDataArray.add("13:10");
        dummyTimeDataArray.add("15:30");
        dummyTimeDataArray.add("17:50");
        dummyTimeDataArray.add("20:50");
        dummyTimeDataArray.add("23:00");

        for (int i = 0; i < dummyTimeDataArray.size(); i++) {
            if (dummyTimeDataArray.get(i).compareTo(currentTimeFormat) >= 0) {
                timeArray.add(dummyTimeDataArray.get(i));
            }
        }
        time = view.findViewById(R.id.buy_by_movie_time_text_layout);
        timeAdapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, timeArray);
        timeAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_time_auto_complete_text);
        timeAutoCompleteTextView.setAdapter(timeAdapterItems);
        timeAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                timeChosen = parent.getItemAtPosition(position).toString();
            }
        });


        return view;
    }
}