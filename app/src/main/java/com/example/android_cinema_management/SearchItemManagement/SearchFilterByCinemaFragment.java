package com.example.android_cinema_management.SearchItemManagement;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_cinema_management.Adapter.CinemaAdapter;
import com.example.android_cinema_management.CinemaManagement.CinemaFragment;
import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.R;

import java.util.ArrayList;


public class SearchFilterByCinemaFragment extends Fragment {
    //Declare editText, button,textview
    EditText searchCinema;
    Button buttonFilter;
    TextView result;
    ArrayList<Cinema> searchList, cinemaList;
    ImageView close;


    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private CinemaAdapter cinemaAdapter;
    //Declare array adapter
//    ArrayAdapter<String> adapterItems;
//    ArrayAdapter<String> adapterItems2;
//    ArrayAdapter<String> adapterItems3;
    //Declare string city and string array of cities
    String [] cities = {"Ha Noi", "Sai Gon", "Da Lat", "Can Tho",
            "Vung Tau", "Da Nang", "Nha Trang","Ca Mau","Hai Phong",
            "Quang Ninh","Dong Nai"};
    String cityChosen;

    String [] reviews = {"Less than 1000", "1000 - 2000", "2001 - 3000", "3001 - 4000","Greater than 4000"};
    String reviewChosen;

    String [] rates = {"Less than 3", "4-6", "7-9", "Greater than 9"};
    String rateChosen;

    public SearchFilterByCinemaFragment() {
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
        // Inflate the layout for this fragment\
        View view = inflater.inflate(R.layout.fragment_search_filter_by_cinema_fragement1, container, false);
        //Binding to xml value
        searchCinema = view.findViewById(R.id.frag1SearchCinemaSearchBar);
        buttonFilter = view.findViewById(R.id.searchByCinemaFilters);
        result = view.findViewById(R.id.searchByCinemaResult);
        close = view.findViewById(R.id.closeButton);
        recyclerView = view.findViewById(R.id.searchByCinemaRecyclerView);
        recyclerView.setHasFixedSize(true);
        //Instantiate array list
        searchList = new ArrayList<>();
        cinemaList = CinemaFragment.cinemaArrayList;
        System.out.println("huy ne" + cinemaList);
        //Use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());

        // Instantiate adapter
        cinemaAdapter = new CinemaAdapter(getContext(), cinemaList);
        // Set layout for recycler view
        recyclerView.setLayoutManager(layoutManager);
        // Set adapter for recycler view
        recyclerView.setAdapter(cinemaAdapter);
        searchCinema.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String inputText = searchCinema.getText().toString();
                searchList = new ArrayList<>();
                searchCinemaByAllAttributes(cinemaList,searchList,inputText);
                // Instantiate adapter
                cinemaAdapter = new CinemaAdapter(getContext(), searchList);
                // Set layout for recycler view
                recyclerView.setLayoutManager(layoutManager);
                // Set adapter for recycler view
                recyclerView.setAdapter(cinemaAdapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });








        // Activate filter dialog
        buttonFilter.setOnClickListener(View ->{
            openFilterDialog();
        });
        return view;
    }

    /** Function to search cinema List by attributes and display in the UI
     *
     * */
    private void searchCinemaByAllAttributes(ArrayList<Cinema>cinemaList, ArrayList<Cinema>searchList,String keyword){
        for (Cinema cinema: cinemaList){
            if (cinema.getCity().trim().toLowerCase().contains(keyword.trim().toLowerCase()) ||
                    cinema.getAddress().trim().toLowerCase().contains(keyword.trim().toLowerCase()) ||
                    cinema.getContactNumber().trim().toLowerCase().contains(keyword.trim().toLowerCase()) ||
                    cinema.getLocationName().trim().toLowerCase().contains(keyword.trim().toLowerCase()) ||
                    cinema.getName().trim().toLowerCase().contains(keyword.trim().toLowerCase())){
                searchList.add(cinema);
            }
          }
        }

    /** function to apply filter and display cinema list in UI
     *
     * */
    private void filterCinemaAllAttributes(ArrayList<Cinema> cinemaList,
                                           ArrayList<Cinema>filterList
                                           ) {
        for (Cinema cinema : cinemaList){
        }
    }
    /**
     * Function to open dialog when click the filter button
     * */
    private void openFilterDialog(){
        final Dialog dialog = new Dialog (getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.open_filter_dialog);
        Window window = dialog.getWindow();
        if (window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.TOP;
        window.setAttributes(windowAttributes);
        dialog.setCancelable(false);
        dialog.show();
        //Declare auto complete text view
        AutoCompleteTextView city;
        AutoCompleteTextView review;
        AutoCompleteTextView rate;
        // Binding value to xml layout
        Button reset = dialog.findViewById(R.id.ofd_reset);
        Button filters = dialog.findViewById(R.id.ofd_filters);
        ImageView close = dialog.findViewById(R.id.closeButton);
        city = dialog.findViewById(R.id.filter_city_autoTextView);
        review = dialog.findViewById(R.id.filter_review_autoCompleteText);
        rate = dialog.findViewById(R.id.filter_rate_autoCompleteText);
        // Declare String array adapter
        ArrayAdapter<String> adapterCities;
        ArrayAdapter<String> adapterReviews;
        ArrayAdapter<String> adapterRates;

        // Initialize adapter value
        adapterCities = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, cities);
        adapterReviews = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, reviews);
        adapterRates = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, rates);

        // Set adapter
        city.setAdapter(adapterCities);
        review.setAdapter(adapterReviews);
        rate.setAdapter(adapterRates);

        //Show drop down list when click auto complete text city
        city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityChosen = parent.getItemAtPosition(position).toString();

            }
        });
        //Show drop down list when click auto complete text review
        review.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                reviewChosen = parent.getItemAtPosition(position).toString();

            }
        });

        //Show drop down list when click auto complete text rate
        rate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rateChosen = parent.getItemAtPosition(position).toString();
            }
        });

        // Function to reset the dialog
        reset.setOnClickListener(View ->{
            // Clear auto complete text
            city.getText().clear();
            review.getText().clear();
            rate.getText().clear();
        });

        // Function to filter by user selection
        filters.setOnClickListener(View ->{
//            filterCinemaAllAttributes(cinemaList);
            dialog.dismiss();
        });
        //Function to close the dialog
        close.setOnClickListener(View ->{
            dialog.dismiss();
        });
    }
    }




