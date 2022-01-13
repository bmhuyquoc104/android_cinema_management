package com.example.android_cinema_management.CinemaManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.android_cinema_management.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class AddCinemaFragment extends Fragment {

    EditText cinemaName, cinemaAddress, contactNumber, locationName, latitude, longitude, review, rate;
    Button addCinemaBtn;
    //Declare FirebaseFirestore FirebaseAuth FirebaseUser String
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String userId,userName;

    //Declare text input layout
    TextInputLayout city;

    //Declare movie array options
    ArrayList<String> cityArray = new ArrayList<>();

    String[] cities = {"Ha Noi", "Sai Gon", "Da Lat", "Can Tho",
            "Vung Tau", "Da Nang"};
    String cityChosen;
    //Declare array adapter
    ArrayAdapter<String> adapterItems;
    // Declare auto complete text view
    AutoCompleteTextView autoCompleteTextView;
    public AddCinemaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_cinema, container, false);
        cinemaName = view.findViewById(R.id.admin_add_cinema_name_et);
        cinemaAddress = view.findViewById(R.id.admin_add_cinema_address_sent_et);
        contactNumber = view.findViewById(R.id.admin_add_cinema_contactNumber_sent_et);
        locationName = view.findViewById(R.id.admin_add_cinema_location_name_tv);
        latitude = view.findViewById(R.id.admin_add_cinema_latitude_sent_et);
        longitude = view.findViewById(R.id.admin_add_cinema_longitude_sent_et);
        review = view.findViewById(R.id.admin_add_cinema_review_sent_et);
        rate = view.findViewById(R.id.admin_add_cinema_rate_sent_et);

        addCinemaBtn = view.findViewById(R.id.admin_add_cinema_add_bt);

        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, cities);
        autoCompleteTextView = view.findViewById(R.id.user_add_review_movie_auto_complete_text);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityChosen = parent.getItemAtPosition(position).toString();
            }
        });

        return view;
    }
}