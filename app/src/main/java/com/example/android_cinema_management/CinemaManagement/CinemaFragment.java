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


//        //  Dummy data
//        Seat seat = new Seat(1,false,"10");
//        Seat seat2 = new Seat(2,false,"10");
//        Seat seat3 = new Seat(3,false,"10");
//        Seat seat4 = new Seat(4,true,"10");
//        Seat seat5 = new Seat(5,true,"10");
//        Seat seat6 = new Seat(6,false,"10");
//        Seat seat7 = new Seat(7,false,"10");
//        Seat seat8 = new Seat(8,true,"10");
//        Seat seat9 = new Seat(9,false,"10");
//        ArrayList<Seat> seatForScreen = new ArrayList<>();
//        ArrayList<Seat> seatForScreen2 = new ArrayList<>();
//        ArrayList<Seat> seatForScreen3 = new ArrayList<>();
//        seatForScreen.add(seat);
//        seatForScreen.add(seat2);
//        seatForScreen.add(seat3);
//        seatForScreen2.add(seat4);
//        seatForScreen2.add(seat5);
//        seatForScreen2.add(seat6);
//        seatForScreen3.add(seat7);
//        seatForScreen3.add(seat8);
//        seatForScreen3.add(seat9);
//
//        ArrayList<Movie>movies = HomeFragment1.movieList;
//        Movie movie1 = new Movie();
//        Movie movie2 = new Movie();
//        Movie movie3 = new Movie();
//        Movie movie6 = new Movie();
//        for (int i = 0; i< movies.size();i++){
//            movie1 = movies.get(0);
//            movie2 = movies.get(1);
//            movie3 = movies.get(2);
//            movie6 = movies.get(5);
//        }
//        ShowTime showTime1 = new ShowTime("10:30","2D",movie1,"2022-01-11");
//        ShowTime showTime7 = new ShowTime("08:30","2D",movie1,"2022-01-12");
//        ShowTime showTime2 = new ShowTime("12:30","2D",movie2,"2022-01-13");
//        ShowTime showTime3 = new ShowTime("14:30","2D",movie3,"2022-01-11");
//        ShowTime showTime4 = new ShowTime("16:30","3D",movie1,"2022-01-12");
//        ShowTime showTime5 = new ShowTime("18:30","3D",movie2,"2022-01-12");
//        ShowTime showTime6 = new ShowTime("23:12","2D",movie3,"2022-01-12");
//        ShowTime showTime8 = new ShowTime("15:30","2D",movie6,"2022-01-12");
//
//
//
//        ArrayList<ShowTime> showTimes = new ArrayList<>();
//        showTimes.add(showTime1);
//        showTimes.add(showTime2);
//        showTimes.add(showTime3);
//        showTimes.add(showTime4);
//        showTimes.add(showTime5);
//        showTimes.add(showTime6);
//        showTimes.add(showTime7);
//        showTimes.add(showTime8);
//
//        ArrayList<String> cinemaTime = new ArrayList<>();
//        for (ShowTime showtime:showTimes
//        ) {
//            cinemaTime.add(showtime.getTime());
//        }
//
//        ArrayList<String> cinemaDate = new ArrayList<>();
//        for (ShowTime showtime:showTimes
//        ) {
//            cinemaDate.add(showtime.getDate());
//        }
//
//
//
//        ArrayList<Screen> screens = new ArrayList<>();
//        Screen screen = new Screen(1,5,showTimes,"available",seatForScreen);
//        Screen screen2 = new Screen(2,5,showTimes,"available",seatForScreen2);
//        Screen screen3 = new Screen(3,5,showTimes,"available",seatForScreen3);
//
//        System.out.println("huy before" + showTimes.size());
//        for (int i = 0 ;i < showTimes.size();i++) {
//            screen.getShowTime().get(i).removeInvalidShowTime(showTimes);
//
//        }
//
//
//
//        System.out.println("huy after" + showTimes.size());
//        screens.add(screen);
//        screens.add(screen2);
//        screens.add(screen3);
//
//        Cinema cinema = new Cinema("cinema1","nguyen van a",10.835538,10.835538,106.659878,"0898321","https://www.citypassguide.com/media/destination/galaxy-cinema-galaxy-cinema-ho-chi-minh-city.jpg","thuong xa tax","123",1200,"Sai Gon",screens);
////        Cinema cinema2 = new Cinema("123","cinema2","nguyen van b",10.773140,105.746857,5.2,"0898323121","https://images.pexels.com/photos/375885/pexels-photo-375885.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940","thuong xa stark2",2300,"Ha Noi");
////        Cinema cinema3 = new Cinema("123","cinema3","nguyen van c",10.790318,106.640184,6.3,"0898322131","https://images.pexels.com/photos/436413/pexels-photo-436413.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500","thuong xa stark3",700,"Can Tho");
////
//         cinemaArrayList.add(cinema);
//        cinemaArrayList.add(cinema2);
//        cinemaArrayList.add(cinema3);
//        System.out.println("cinema list ne" + cinemaArrayList);
//            // Instantiate adapter
//            cinemaAdapter = new CinemaAdapter(getContext(), cinemaArrayList);
//            // Set layout for recycler view
//            recyclerView.setLayoutManager(layoutManager);
//            // Set adapter for recycler view
//            recyclerView.setAdapter(cinemaAdapter);
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