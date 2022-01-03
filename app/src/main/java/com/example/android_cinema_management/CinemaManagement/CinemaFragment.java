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
import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.Model.Screen;
import com.example.android_cinema_management.Model.Seat;
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
    //Declare adapter
    private CinemaAdapter cinemaAdapter;
    //Declare public static List to used later by other classes
    public static ArrayList<Cinema> cinemaArrayList;

    private Cinema cinema;
    // Declare handle thread
    HandlerThread ht = new HandlerThread("MyHandlerThread");
    // Declare string and array of string for cities
    String[] cities = {"Ha Noi", "Sai Gon", "Da Lat", "Can Tho",
            "Vung Tau", "Da Nang", "Nha Trang", "Ca Mau", "Hai Phong",
            "Quang Ninh", "Dong Nai"};
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
        db = FirebaseFirestore.getInstance();
        //Use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());

        autoCompleteTextView = view.findViewById(R.id.fragCinema_cities);
        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, cities);
        autoCompleteTextView.setAdapter(adapterItems);

//        // Handle scrapping data
//        CinemaDatabase.showData(db, cinemaArrayList, () -> {
//            // Instantiate adapter
//            cinemaAdapter = new CinemaAdapter(getContext(), cinemaArrayList);
//            // Set layout for recycler view
//            recyclerView.setLayoutManager(layoutManager);
//            // Set adapter for recycler view
//            recyclerView.setAdapter(cinemaAdapter);
//
//            autoCompleteTextView.setOnItemClickListener((parent, view1, position, id) -> {
//                city = parent.getItemAtPosition(position).toString();
//
//                // Initialize new array list to store result after filtering
//                ArrayList<Cinema> filterByCityList = new ArrayList<>();
//
//                // Function filter by city
//                filterByCity(filterByCityList, cinemaArrayList, city);
//
//                // Instantiate adapter
//                cinemaAdapter = new CinemaAdapter(getContext(), filterByCityList);
//
//                // Set layout for recycler view
//                recyclerView.setLayoutManager(layoutManager);
//
//                // Set adapter for recycler view
//                recyclerView.setAdapter(cinemaAdapter);
//            });
//        });

        ArrayList<String>showTime1 = new ArrayList<>();
        showTime1.add("10:30");
        showTime1.add("12:30");
        showTime1.add("14:30");

        //  Dummy data
        Seat seat = new Seat(1,false,"10");
        Seat seat2 = new Seat(2,false,"10");
        Seat seat3 = new Seat(3,false,"10");
        Seat seat4 = new Seat(4,true,"10");
        Seat seat5 = new Seat(5,true,"10");
        Seat seat6 = new Seat(6,false,"10");
        Seat seat7 = new Seat(7,false,"10");
        Seat seat8 = new Seat(8,true,"10");
        Seat seat9 = new Seat(9,false,"10");
        ArrayList<Seat> seatForScreen = new ArrayList<>();
        ArrayList<Seat> seatForScreen2 = new ArrayList<>();
        ArrayList<Seat> seatForScreen3 = new ArrayList<>();
        seatForScreen.add(seat);
        seatForScreen.add(seat2);
        seatForScreen.add(seat3);
        seatForScreen2.add(seat4);
        seatForScreen2.add(seat5);
        seatForScreen2.add(seat6);
        seatForScreen3.add(seat7);
        seatForScreen3.add(seat8);
        seatForScreen3.add(seat9);
        ArrayList<Screen> screens = new ArrayList<>();

        Screen screen = new Screen(1,5,showTime1,"available",seatForScreen);
        Screen screen2 = new Screen(2,5,showTime1,"available",seatForScreen2);
        Screen screen3 = new Screen(3,5,showTime1,"available",seatForScreen3);
        screens.add(screen);
        screens.add(screen2);
        screens.add(screen3);

        Cinema cinema = new Cinema("cinema1","nguyen van a",10.835538,10.835538,106.659878,"0898321","https://www.citypassguide.com/media/destination/galaxy-cinema-galaxy-cinema-ho-chi-minh-city.jpg","thuong xa tax","123",1200,"Sai Gon",screens);
//        Cinema cinema2 = new Cinema("123","cinema2","nguyen van b",10.773140,105.746857,5.2,"0898323121","https://images.pexels.com/photos/375885/pexels-photo-375885.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940","thuong xa stark2",2300,"Ha Noi");
//        Cinema cinema3 = new Cinema("123","cinema3","nguyen van c",10.790318,106.640184,6.3,"0898322131","https://images.pexels.com/photos/436413/pexels-photo-436413.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500","thuong xa stark3",700,"Can Tho");
//
         cinemaArrayList.add(cinema);
//        cinemaArrayList.add(cinema2);
//        cinemaArrayList.add(cinema3);
        System.out.println("cinema list ne" + cinemaArrayList);
            // Instantiate adapter
            cinemaAdapter = new CinemaAdapter(getContext(), cinemaArrayList);
            // Set layout for recycler view
            recyclerView.setLayoutManager(layoutManager);
            // Set adapter for recycler view
            recyclerView.setAdapter(cinemaAdapter);
        return view;
    }
//        new Handler (Looper.getMainLooper()).postDelayed( () -> {
//            System.out.println("huy ne " + cinemaArrayList);
//            cinemaAdapter = new CinemaAdapter(getContext(),cinemaArrayList);
//            // Specify an adapter
//            recyclerView.setAdapter(cinemaAdapter);
//        },3000);
//        return view;


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