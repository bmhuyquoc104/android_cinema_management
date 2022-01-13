package com.example.android_cinema_management.CinemaManagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.android_cinema_management.Adapter.CinemaAdapter;
import com.example.android_cinema_management.Adapter.MoviesAdapter;
import com.example.android_cinema_management.Handler.MovieHandler;
import com.example.android_cinema_management.HomeFragment;
import com.example.android_cinema_management.HomeManagement.HomeFragment1;
import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.Model.Screen;
import com.example.android_cinema_management.Model.Seat;
import com.example.android_cinema_management.Model.ShowTime;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.database.CinemaDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class CinemaFragment extends Fragment {
    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare array adapter
    ArrayAdapter<String> adapterItems;
    //Declare public static string array cinema title
    public static ArrayList<String> cinemaNameList;
    //Declare adapter
    private CinemaAdapter cinemaAdapter;
    //Declare public static List to used later by other classes
    public static ArrayList<Cinema> cinemaArrayList;

    private Cinema cinema;
    // Declare handle thread
    HandlerThread ht = new HandlerThread("MyHandlerThread");
    // Declare string and array of string for cities
    String[] cities = {"Ha Noi", "Sai Gon", "Da Lat", "Can Tho",
            "Vung Tau", "Da Nang"};
    String city;
    //Declare firebase
    FirebaseFirestore db;

    // Declare auto text complete
    AutoCompleteTextView autoCompleteTextView;

    public CinemaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cinema, container, false);
        // Set fixed size for recycler view
        recyclerView = view.findViewById(R.id.cinemaRecyclerView);
        recyclerView.setHasFixedSize(true);
        // Instantiate class
        cinema = new Cinema();
        cinemaArrayList = new ArrayList<>();
        cinemaNameList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        //Use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());

        autoCompleteTextView = view.findViewById(R.id.fragCinema_cities);
        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, cities);
        autoCompleteTextView.setAdapter(adapterItems);

        // Handle scrapping data
        CinemaDatabase.showData(db, cinemaArrayList, () -> {
            // Instantiate adapter
            cinemaAdapter = new CinemaAdapter(getContext(), cinemaArrayList);
            // Set layout for recycler view
            recyclerView.setLayoutManager(layoutManager);
            // Set adapter for recycler view
            recyclerView.setAdapter(cinemaAdapter);

            for (Cinema cinema:cinemaArrayList){
                cinemaNameList.add(cinema.getName());
            }

            System.out.println("hello ne" +  cinemaNameList);
            autoCompleteTextView.setOnItemClickListener((parent, view1, position, id) -> {
                city = parent.getItemAtPosition(position).toString();

                // Initialize new array list to store result after filtering
                ArrayList<Cinema> filterByCityList = new ArrayList<>();

                // Function filter by city
                filterByCity(filterByCityList, cinemaArrayList, city);

                // Instantiate adapter
                cinemaAdapter = new CinemaAdapter(getContext(), filterByCityList);

                // Set layout for recycler view
                recyclerView.setLayoutManager(layoutManager);

                // Set adapter for recycler view
                recyclerView.setAdapter(cinemaAdapter);
            });
        });



        return view;
    }



    /**
     * Function to render the cinema list in UI
     */
    public void renderCinemaList() {
        // Read post from fetchAndRenderMovie
        System.out.println("huy ne " + cinemaArrayList);
        cinemaAdapter = new CinemaAdapter(getContext(), cinemaArrayList);

        // Specify an adapter
        recyclerView.setAdapter(cinemaAdapter);

    }

    // Filter cinema list by city
    public void filterByCity(ArrayList<Cinema> filterCinemaList, ArrayList<Cinema> cinemaList, String inputCity) {
        for (Cinema cinema : cinemaList) {
            if (cinema.getCity() != null && cinema.getCity().equals(inputCity)) {
                filterCinemaList.add(cinema);
            }
        }
    }

}