package com.example.android_cinema_management.UserManagement;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_cinema_management.Adapter.CinemaAdapter;
import com.example.android_cinema_management.CinemaManagement.CinemaFragment;
import com.example.android_cinema_management.HomeManagement.HomeFragment1;
import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.database.CinemaDatabase;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class BuyTicketFragment1 extends Fragment {
    //Declare movie,date,time,cinema array options
    ArrayList<String> moviesArray = new ArrayList<>();
    ArrayList<String> cinemaArray = new ArrayList<>();
    ArrayList<String> timeArray = new ArrayList<>();
    ArrayList<String> dummyTimeDataArray = new ArrayList<>();
    ArrayList<String> dateArray = new ArrayList<>();
    ArrayList<Cinema> cinemaArrayList = new ArrayList<>();
    //Declare button and textview
    Button next;
    TextView screen;
    int screenId = 0;
    //Declare boolean to check if the dropdown box is pressed
    boolean chooseMovie = false;
    boolean chooseCinema = false;
    boolean chooseDate = false;
    boolean chooseTime = false;


    //Declare text input layout
    TextInputLayout movie,cinema,date,time;
    String movieChosen,dateChosen,timeChosen,cinemaChosen;

    //Declare array adapter
    ArrayAdapter<String> movieAdapterItems;
    ArrayAdapter<String> cinemaAdapterItems;
    ArrayAdapter<String> dateAdapterItems;
    ArrayAdapter<String> timeAdapterItems;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // Declare auto complete text view
    AutoCompleteTextView movieAutoCompleteTextView,cinemaAutoCompleteTextView,dateAutoCompleteTextView,timeAutoCompleteTextView;
    public BuyTicketFragment1() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_buy_by_movie, container, false);
        //Initialize value for movieArray,cinemaArray,dateArray,timeArray
        moviesArray = HomeFragment1.movieTitleList;
        CinemaDatabase.showData(db, cinemaArrayList, () -> {
            // Instantiate adapter

            for (Cinema cinema:cinemaArrayList){
                cinemaArray.add(cinema.getName());
            }

        });

        //Binding xml value
        next = view.findViewById(R.id.buy_by_movie_next_bt);
        screen = view.findViewById(R.id.buy_ticket_screen_tv);
        //Disable the button
        next.setEnabled(false);
        //Binding xml value and set the dropdown for movie
        movie = view.findViewById(R.id.buy_by_movie_movie_text_layout);
        movieAdapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, moviesArray);
        movieAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_movie_auto_complete_text);
        movieAutoCompleteTextView.setAdapter(movieAdapterItems);
        movieAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                movieChosen = parent.getItemAtPosition(position).toString();
                // Set screen base on the movie chosen
                screenId = position + 1;

                chooseMovie = true;
                // movie layout is  chosen
                if (chooseMovie){
                    //Enable cinema text layout
                    cinema.setEnabled(true);
                }else{
                    //Keep disable cinema text layout
                    cinema.setEnabled(false);
                }
            }
        });




        //Binding xml value and set the dropdown for cinema
        cinema = view.findViewById(R.id.buy_by_movie_cinema_text_layout);
        //Initially Disable the cinema text layout
        cinema.setEnabled(false);
        cinemaAdapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, cinemaArray);
        cinemaAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_cinema_auto_complete_text);
        cinemaAutoCompleteTextView.setAdapter(cinemaAdapterItems);
        cinemaAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cinemaChosen = parent.getItemAtPosition(position).toString();
                screen.setText("Screen: "+screenId);
                chooseCinema = true;
                // cinema layout is  chosen
                if (chooseCinema){
                    //Enable the date text layout
                    date.setEnabled(true);
                }else{
                    //Keep disable date text layout
                    date.setEnabled(false);
                }
            }
        });

        // Set the format date
        @SuppressLint("SimpleDateFormat")  SimpleDateFormat df = new SimpleDateFormat("EEEE,yyyy-MM-dd");
        //Get the current date and then format it
        Date today = new Date();
        String todayFormat = df.format(today);
        // Add current date to the array
        dateArray.add(todayFormat);
        Calendar cal = Calendar.getInstance();
        // Add the followings 6 days to the array from today
        for (int i = 0; i < 6;i++) {
            cal.add(Calendar.DATE, 1);
            System.out.println(cal);
            Date nextDates = cal.getTime();
            String nextDatesInFormat = df.format(nextDates);
            dateArray.add(nextDatesInFormat);
        }

        // Get the current time to compare later
        Date currentTime = Calendar.getInstance().getTime();
        Date currentDate = Calendar.getInstance().getTime();
        // Set the format of time
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df2 = new SimpleDateFormat("kk:mm");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
        String currentTimeFormat = df2.format(currentTime);
        String currentDateFormat = df3.format(currentTime);
        // Add dummy data to time array
        dummyTimeDataArray.add("08:00");
        dummyTimeDataArray.add("10:35");
        dummyTimeDataArray.add("12:20");
        dummyTimeDataArray.add("15:35");
        dummyTimeDataArray.add("17:50");
        dummyTimeDataArray.add("20:50");
        dummyTimeDataArray.add("23:00");

        //Binding xml value and set the dropdown for date
        date = view.findViewById(R.id.buy_by_movie_date_text_layout);
        //Initially disable the date text layout
        date.setEnabled(false);
        dateAdapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, dateArray);
        dateAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_date_auto_complete_text);
        dateAutoCompleteTextView.setAdapter(dateAdapterItems);
        dateAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dateChosen = parent.getItemAtPosition(position).toString();
                // spilt to get the second part which is the date to compare which current date
                String [] spiltDate = dateChosen.split(",");
                String dateCompare = spiltDate[1];
                timeArray.clear();
                // If the date come after
                if (dateCompare.compareTo(currentDateFormat) > 0){
                    for (int i = 0; i < dummyTimeDataArray.size(); i++) {
                        // Get all value from dummy array
                            timeArray.add(dummyTimeDataArray.get(i));
                        }
                }
                // If the date come before
                else{
                    for (int i = 0; i < dummyTimeDataArray.size(); i++) {
                        // Check if the time is valid (come before)
                        if (dummyTimeDataArray.get(i).compareTo(currentTimeFormat) >= 0) {
                            // add valid time to the time array
                            timeArray.add(dummyTimeDataArray.get(i));
                        }
                    }
                }
                chooseDate = true;
                // date layout is  chosen
                if (chooseDate){
                    //Enable the time text layout
                    time.setEnabled(true);
                }else{
                    //Keep disable time text layout
                    time.setEnabled(false);
                }
            }
        });



        //Binding xml value and set the dropdown for time
        time = view.findViewById(R.id.buy_by_movie_time_text_layout);
        //Initially disable the time text layout
        time.setEnabled(false);
        timeAdapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, timeArray);
        timeAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_time_auto_complete_text);
        timeAutoCompleteTextView.setAdapter(timeAdapterItems);
        timeAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                timeChosen = parent.getItemAtPosition(position).toString();
                // time layout is  chosen
                chooseTime = true;
                if (chooseTime){
                    //Enable the next button
                    next.setEnabled(true);
                }else{
                    //Keep disable next button
                    next.setEnabled(false);
                }
            }
        });

        // Function to change fragment and send data to next step in buying ticket
        next.setOnClickListener(View -> {
                Bundle bundle = new Bundle();
                bundle.putString("movie", movieChosen);
                bundle.putString("cinema", cinemaChosen);
                bundle.putString("date", dateChosen);
                bundle.putString("time", timeChosen);
                bundle.putString("screen", screen.getText().toString());
                BuyTicketFragment2 fragment2 = new BuyTicketFragment2();
                fragment2.setArguments(bundle);
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction transaction =
                        fm.beginTransaction();
                transaction.replace(R.id.buy_ticket_frame_layout, fragment2).addToBackStack("fragment1").commit();

        });

        return view;
    }
}