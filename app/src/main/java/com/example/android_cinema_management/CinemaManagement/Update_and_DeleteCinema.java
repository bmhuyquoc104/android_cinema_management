package com.example.android_cinema_management.CinemaManagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Update_and_DeleteCinema extends AppCompatActivity {

    EditText cinemaName, cinemaAddress, contactNumber, locationName, latitude, longitude, review, rate, imageURL;
    Button updateCinemaBtn, deleteCinemaBtn;
    //Declare FirebaseFirestore FirebaseAuth FirebaseUser String
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //Declare text input layout
    TextInputLayout city;

    Cinema cinema;

    //Declare movie array options
    ArrayList<String> cityArray = new ArrayList<>();

    String cityChosen;
    //Declare array adapter
    ArrayAdapter<String> cityAdapterItems;
    // Declare auto complete text view
    AutoCompleteTextView cityAutoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete_cinema);
        //get content cinema's intent
        cinema = (Cinema) getIntent().getSerializableExtra("cinema");

        //Binding EditText and Button to file xml activity_update_and_delete_cinema
        cinemaName = findViewById(R.id.admin_add_cinema_name_et);
        cinemaAddress = findViewById(R.id.admin_add_cinema_address_sent_et);
        contactNumber = findViewById(R.id.admin_add_cinema_contactNumber_sent_et);
        locationName = findViewById(R.id.admin_add_cinema_location_name_tv);
        latitude = findViewById(R.id.admin_add_cinema_latitude_sent_et);
        longitude = findViewById(R.id.admin_add_cinema_longitude_sent_et);
        city = findViewById(R.id.admin_add_cinema_city_text_layout);
        review = findViewById(R.id.admin_add_cinema_review_sent_et);
        rate = findViewById(R.id.admin_add_cinema_rate_sent_et);
        imageURL = findViewById(R.id.admin_add_cinema_image_sent_et);
        updateCinemaBtn = findViewById(R.id.admin_update_cinema_update_bt);
        deleteCinemaBtn = findViewById(R.id.admin_delete_cinema_delete_bt);

        //Initialize cityArray and add content into cityArray
        cityArray = new ArrayList<>();
        cityArray.add("Ha Noi");
        cityArray.add("Sai Gon");
        cityArray.add("Da Lat");
        cityArray.add("Can Tho");
        cityArray.add("Vung Tau");
        cityArray.add("Da Nang");

        //Setup and binding AdapterItems and AutoCompleteTextView
        cityAdapterItems = new ArrayAdapter<String>(this, R.layout.gender_selector_list, cityArray);
        cityAutoCompleteTextView = findViewById(R.id.admin_add_cinema_city_auto_complete_text);
        cityAutoCompleteTextView.setAdapter(cityAdapterItems);
        cityAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityChosen = parent.getItemAtPosition(position).toString();
            }
        });

        //Set content for each field accordingly
        cinemaName.setText(cinema.getName());
        cinemaAddress.setText(cinema.getAddress());
        contactNumber.setText(cinema.getContactNumber());
        locationName.setText(cinema.getLocationName());
        latitude.setText(String.valueOf(cinema.getLatitude()));
        longitude.setText(String.valueOf(cinema.getLongitude()));
        rate.setText(String.valueOf(cinema.getRate()));
        review.setText(String.valueOf(cinema.getReview()));
        rate.setText(String.valueOf(cinema.getRate()));
        imageURL.setText(cinema.getImageUrl());

        //Listen to updateCinemaBtn onClick
        updateCinemaBtn.setOnClickListener(view -> {
            //put all field content into cinemaMap
            Map<String, Object> cinemaMap = new HashMap<>();
            cinemaMap.put("id", cinema.getCinemaId());
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

            //updating new cinema detial into collection Cinema with according to CinemaId document
            DocumentReference documentReferenceForCinema = db.collection("Cinema")
                    .document(cinema.getCinemaId());
            documentReferenceForCinema.set(cinemaMap).addOnCompleteListener(taskInner -> {
                if (taskInner.isSuccessful()) {
                    Toast.makeText(this, "CINEMA UPDATED!", Toast.LENGTH_SHORT).show();
                }
            });
        });

        //Listen to deleteCinemaBtn button onClick
        deleteCinemaBtn.setOnClickListener(view -> {
            db.collection("Cinema").document(cinema.getCinemaId())
                    .delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Update_and_DeleteCinema.this, "CINEMA DELETED!", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(Update_and_DeleteCinema.this, "FAIL TO DELETED CINEMA", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
}