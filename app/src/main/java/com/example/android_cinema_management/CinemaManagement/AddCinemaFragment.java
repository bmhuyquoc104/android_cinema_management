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
import android.widget.Toast;

import com.example.android_cinema_management.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class AddCinemaFragment extends Fragment {

    EditText cinemaName, cinemaAddress, contactNumber, locationName, latitude, longitude, review, rate, imageURL;
    Button addCinemaBtn;
    //Declare FirebaseFirestore FirebaseAuth FirebaseUser String
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //Declare text input layout
    TextInputLayout city;

    //Declare movie array options
    ArrayList<String> cityArray = new ArrayList<>();

    String cityChosen;
    //Declare array adapter
    ArrayAdapter<String> cityAdapterItems;
    // Declare auto complete text view
    AutoCompleteTextView cityAutoCompleteTextView;
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
        city = view.findViewById(R.id.admin_add_cinema_city_text_layout);
        review = view.findViewById(R.id.admin_add_cinema_review_sent_et);
        rate = view.findViewById(R.id.admin_add_cinema_rate_sent_et);
        imageURL = view.findViewById(R.id.admin_add_cinema_image_sent_et);

        addCinemaBtn = view.findViewById(R.id.admin_add_cinema_add_bt);
        cityArray = new ArrayList<>();
        cityArray.add("Ha Noi");
        cityArray.add("Sai Gon");
        cityArray.add("Da Lat");
        cityArray.add("Can Tho");
        cityArray.add("Vung Tau");
        cityArray.add("Da Nang");

        cityAdapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, cityArray);
        cityAutoCompleteTextView = view.findViewById(R.id.admin_add_cinema_city_auto_complete_text);
        cityAutoCompleteTextView.setAdapter(cityAdapterItems);
        cityAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityChosen = parent.getItemAtPosition(position).toString();
            }
        });

        addCinemaBtn.setOnClickListener(View -> {
            String cinemaId = UUID.randomUUID().toString();
            Map<String, Object> cinemaMap = new HashMap<>();
            cinemaMap.put("id", cinemaId);
            cinemaMap.put("name", cinemaName.getText().toString());
            cinemaMap.put("address", cinemaAddress.getText().toString());
            cinemaMap.put("contactNumber", contactNumber.getText().toString());
            cinemaMap.put("locationName", locationName.getText().toString());
            cinemaMap.put("latitude", latitude.getText().toString());
            cinemaMap.put("longitude", longitude.getText().toString());
            cinemaMap.put("city", cityChosen);
            cinemaMap.put("review", review.getText().toString());
            cinemaMap.put("rate", rate.getText().toString());
            cinemaMap.put("imageURL", imageURL.getText().toString());

            //Saving reviewMap into Firestore in reviews collection
            DocumentReference documentReferenceForCinema = db.collection("Cinema")
                    .document(cinemaId);
            documentReferenceForCinema.set(cinemaMap).addOnCompleteListener(taskInner -> {
                if (taskInner.isSuccessful()) {
                    Toast.makeText(getContext(), "CINEMA ADDED!", Toast.LENGTH_SHORT).show();
                }
            });
        });

        return view;
    }
}