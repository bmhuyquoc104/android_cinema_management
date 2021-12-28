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
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.database.CinemaDatabase;

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
    String [] cities = {"Ha Noi", "Sai Gon", "Da Lat", "Can Tho",
            "Vung Tau", "Da Nang", "Nha Trang","Ca Mau","Hai Phong",
            "Quang Ninh","Dong Nai"};
    String city;
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
        //Use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());

        autoCompleteTextView = view.findViewById(R.id.fragCinema_cities);
        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, cities);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city = parent.getItemAtPosition(position).toString();
            }
        });
        //Dummy data
        Cinema cinema = new Cinema("123","cinema1","nguyen van a","12","15","0898321","https://www.citypassguide.com/media/destination/galaxy-cinema-galaxy-cinema-ho-chi-minh-city.jpg","thuong xa stark");
        Cinema cinema2 = new Cinema("123","cinema2","nguyen van b","12","15","0898323121","https://www.google.com/url?sa=i&url=https%3A%2F%2Fvir.com.vn%2Fgalaxy-cinema-mobilised-capital-from-bond-issuance-85024.html&psig=AOvVaw3d4ZZI9LzZs7ucX-fdKNG0&ust=1640759186075000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCICXorbuhfUCFQAAAAAdAAAAABAJ","thuong xa stark2");
        Cinema cinema3 = new Cinema("123","cinema3","nguyen van c","12","15","0898322131","https://www.google.com/url?sa=i&url=https%3A%2F%2Fthientu.vn%2Fblog%2Ftong-dai-galaxy-cinema-hotline-cskh-cua-he-thong-galaxy-cinema&psig=AOvVaw3d4ZZI9LzZs7ucX-fdKNG0&ust=1640759186075000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCICXorbuhfUCFQAAAAAdAAAAABAP","thuong xa stark3");

        cinemaArrayList.add(cinema);
        cinemaArrayList.add(cinema2);
        cinemaArrayList.add(cinema3);
        System.out.println("cinema list ne" + cinemaArrayList);
        cinemaAdapter = new CinemaAdapter(getContext(),cinemaArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cinemaAdapter);

        return view;
    }

//    private void fetchAndRenderCinema(){
//        //Start the handler thread
//        ht.start();
//        //Initialize new handler
//        Handler asHandler = new Handler(ht.getLooper()){
//            //Handle function after receiving message
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                renderCinemaList();
//            }
//        };
//
//        //Initialize new message
//        Message msg = new Message();
//
//        // Handle scrapping data
//        Runnable runnable = () ->{
//            CinemaDatabase.getAllCinemas();
//            msg.obj = cinemaArrayList;
//            asHandler.sendMessage(msg);
//        };
//        asHandler.post(runnable);
//    }

    /** Function to render the cinema list in UI
     *
     * */
//    public void renderMovieList(){
//        // Initialize new ui handler
//        Handler uiThreadHandler = new Handler(Looper.getMainLooper());
//
//        // Read post from fetchAndRenderMovie
//        uiThreadHandler.post(() ->{
//            cinemaAdapter = new CinemaAdapter(getContext(),cinemaArrayList);
//            // Specify an adapter
//            recyclerView.setAdapter(cinemaAdapter);
//        });
//    }

}