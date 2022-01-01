package com.example.android_cinema_management.SearchItemManagement;

import android.app.Dialog;
import android.app.ProgressDialog;
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
import com.example.android_cinema_management.database.CinemaDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class SearchFilterByCinemaFragment extends Fragment {
    ArrayList<String>temp = new ArrayList<>();
    //Declare editText, button,textview
    EditText searchCinema;
    Button buttonFilter;
    TextView result;
    //Declare list for search filter and original list
    ArrayList<Cinema> searchList, cinemaList, filterList;
    ImageView close;
    //Declare auto complete text view
    AutoCompleteTextView city;
    AutoCompleteTextView review;
    AutoCompleteTextView rate;
    //Declare string range array, int last, and string comparison for filter value in the string
    String[] reviewRange, rateRange;
    int reviewLast;
    double rateLast;
    String reviewComparison, rateComparison;
    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private CinemaAdapter cinemaAdapter;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressDialog pd;

    //Declare string city and string array of cities
    String[] cities = {"All Cities", "Ha Noi", "Sai Gon", "Da Lat", "Can Tho",
            "Vung Tau", "Da Nang", "Nha Trang", "Ca Mau", "Hai Phong",
            "Quang Ninh", "Dong Nai"};
    String cityChosen;
    //Declare string reviews and string array of reviews
    String[] reviews = {"All Reviews", "Less than 5000", "Greater or equal to 5000"};
    String reviewChosen;
    //Declare string rates and string array of rates
    String[] rates = {"All Rates", "Less than 5.0", "Greater or equal to 5.0"};
    String rateChosen;



    public SearchFilterByCinemaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\
        View view = inflater.inflate(R.layout.fragment_search_filter_by_cinema_fragement1, container, false);
        //Binding to xml value
        searchCinema = view.findViewById(R.id.frag1SearchCinemaSearchBar);
        buttonFilter = view.findViewById(R.id.searchByCinemaFilters);
        result = view.findViewById(R.id.search_by_movie_result);
        close = view.findViewById(R.id.closeButton);
        recyclerView = view.findViewById(R.id.searchByCinemaRecyclerView);
        recyclerView.setHasFixedSize(true);
        //Instantiate array list
        searchList = new ArrayList<>();
        filterList = new ArrayList<>();
        cinemaList = new ArrayList<>();
//        cinemaList = CinemaFragment.cinemaArrayList;
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
                searchCinemaByAllAttributes(cinemaList, searchList, inputText);
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
        buttonFilter.setOnClickListener(View -> {
            openFilterDialog();
        });

        pd = new ProgressDialog(getActivity());
        return view;
    }

    /**
     * Function to search cinema List by attributes and display in the UI
     */
    private void searchCinemaByAllAttributes(ArrayList<Cinema> cinemaList, ArrayList<Cinema> searchList, String keyword) {
        for (Cinema cinema : cinemaList) {
            if (cinema.getCity().trim().toLowerCase().contains(keyword.trim().toLowerCase()) ||
                    cinema.getAddress().trim().toLowerCase().contains(keyword.trim().toLowerCase()) ||
                    cinema.getContactNumber().trim().toLowerCase().contains(keyword.trim().toLowerCase()) ||
                    cinema.getLocationName().trim().toLowerCase().contains(keyword.trim().toLowerCase()) ||
                    cinema.getName().trim().toLowerCase().contains(keyword.trim().toLowerCase())) {
                searchList.add(cinema);
            }
        }
    }


    /**
     * Function to open dialog when click the filter button
     */
    private void openFilterDialog() {
        // Initialize new dialog
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Set content for dialog
        dialog.setContentView(R.layout.open_cinema_filter_dialog);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        // set the dialog to top
        windowAttributes.gravity = Gravity.TOP;
        window.setAttributes(windowAttributes);
        // Disable cancel by clicking randomly on the screen
        dialog.setCancelable(false);
        dialog.show();

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
        reviewChosen = review.getText().toString();
        cityChosen = city.getText().toString();
        rateChosen = rate.getText().toString();

        //Show drop down list when click auto complete text city
        city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityChosen = city.getText().toString();
                System.out.println("huy ne" + cityChosen);
                filterAttributeTracking();

            }
        });
        //Show drop down list when click auto complete text review
        review.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                reviewChosen = review.getText().toString();
                // Check the range in the review chosen by user
                if (position > 1) {
                    reviewRange = reviewChosen.split(" ");
                    // Assign max and comparison in the range
                    reviewLast = Integer.parseInt(reviewRange[4]);
                    reviewComparison = reviewRange[0];
                    System.out.println("huy ne" + reviewLast);
                    System.out.println("huy ne" + reviewComparison);
                    filterAttributeTracking();
                }
            }
        });

        //Show drop down list when click auto complete text rate
        rate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rateChosen = rate.getText().toString();
                System.out.println("huy ne" + rateChosen);
                // Assign max and comparison in the range
                if (position > 1) {
                    rateRange = rateChosen.split(" ");
                    rateLast = Double.parseDouble(rateRange[4]);
                    rateComparison = rateRange[0];
                    System.out.println("huy ne" + rateLast);
                    System.out.println("huy ne" + rateComparison);
                    filterAttributeTracking();
                }

            }
        });

        // Function to reset the dialog
        reset.setOnClickListener(View -> {
            // Clear auto complete text
            city.getText().clear();
            review.getText().clear();
            rate.getText().clear();
        });

        // Function to filter by user selection
        filters.setOnClickListener(View -> {
//            filterCinemaAllAttributes(cinemaList);
            filterList = new ArrayList<>();

            //dismiss the dialog
            dialog.dismiss();
//            filterCinemaAllAttributes();
            CinemaDatabase.filterData(db, getActivity(), pd, "Sai Gon", "9.5",
                    "100", 6);

//            // Instantiate adapter
//            cinemaAdapter = new CinemaAdapter(getContext(), filterList);
//
//            // Set layout for recycler view
//            recyclerView.setLayoutManager(layoutManager);
//
//            // Set adapter for recycler view
//            recyclerView.setAdapter(cinemaAdapter);
        });
        //Function to close the dialog
        close.setOnClickListener(View -> {
            dialog.dismiss();
        });
    }
    private void filterAttributeTracking() {
        temp = new ArrayList<>();
        temp.add(cityChosen);
        temp.add(rateChosen);
        temp.add(reviewChosen);
        System.out.println("DDDDAAAYYYYYYYY" + temp.toString());
    }

//    /**
//     * method to apply filter and display cinema list in UI
//     */
//    private ArrayList<Cinema> filterCinemaAllAttributes(
//    ) {
//        //Declare 3 boolean to check the attribute to filter
//        boolean checkReview = false;
//        boolean checkRate = false;
//        boolean checkCity = false;
//        // Loop through array list and filter the correct cinema
//        for (Cinema cinema : cinemaList) {
//            // Check if the user choose the city to filter or not
//            if (!city.getText().toString().equals("All Cities")) {
//                // If the cinema is not found -> change the check city is false -> otherwise true
//                if (cinema.getCity().equals(city.getText().toString())) {
//                    System.out.println("cho city" + city.getText().toString());
//                    System.out.println("cho city cinema" + cinema.getCity());
//                    checkCity = true;
//                }
//
//            }
//            // Check if the user choose the review to filter or not
//            if (review.getText().toString().equals("All Reviews")) {
//                // check the comparison
//                if (reviewComparison.equals("Less")) {
//                    // If the review is not found -> change check review to false
//                    if ((cinema.getReview() < reviewLast)) {
//                        checkReview = true;
//                    }
//                    // check the comparison
//                } else if (reviewComparison.equals("Greater")) {
//                    // If the review is not found -> change check review to false
//                    if ((cinema.getReview() > reviewLast)) {
//                        checkReview = true;
//                    }
//                } else {
//                    // If the review is not found -> change check review to false
//                    if ((cinema.getReview() <= reviewLast && cinema.getReview()
//                            >= Integer.parseInt(reviewComparison))) {
//                        checkReview = true;
//                    }
//                }
//            }
//            if (rate.getText().toString().equals("All Rates")) {
//                // check the comparison
//                if (rateComparison.equals("Less")) {
//                    if (!(cinema.getRate() < rateLast)) {
//                        checkRate = true;
//                    }
//                    // If the rate is not found -> change check review to false
//                } else if (rateComparison.equals("Greater")) {
//                    if ((cinema.getRate() > rateLast)) {
//                        checkRate = true;
//                    }
//
//                } else {
//                    // If the rate is not found -> change check review to false
//                    if ((cinema.getRate() <= rateLast &&
//                            cinema.getRate() >= Double.parseDouble(rateComparison))) {
//                        checkRate = true;
//                    }
//                }
//            }
//            System.out.println(checkCity + "checkcity");
//            System.out.println(checkRate + "checkrate");
//            System.out.println(checkReview + "checkreview");
//            // Get cinema that satisfy all conditions
//            if (checkRate || checkCity || checkReview) {
//                filterList.add(cinema);
//                System.out.println("hehlkenmhklernkhlerngkergnekl" + cinema);
//            }
//        }
//        return filterList;
//    }
}




