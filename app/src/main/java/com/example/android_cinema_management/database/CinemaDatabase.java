package com.example.android_cinema_management.database;

import androidx.annotation.NonNull;

import com.example.android_cinema_management.Model.Cinema;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class CinemaDatabase {

    // Method to fetch all cinema data from the firebase firestore to cinemalist
    public static void showData(FirebaseFirestore db, ArrayList<Cinema> cinemaList, Runnable callback) {
        // Check the collection name and get it
        db.collection("Cinema").get()
                .addOnCompleteListener(task -> {
                    // Check if task is successfully or not
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot snapshot : task.getResult()) {
                            // Create an instance and load data to cinema
                            Cinema cinema = new Cinema(
                                    snapshot.getString("id"),
                                    snapshot.getString("name"),
                                    snapshot.getString("address"),
                                    // Parse the database type string to double
                                    Double.parseDouble(Objects.requireNonNull(snapshot.getString("latitude"))),
                                    // Parse the database type string to double
                                    Double.parseDouble(Objects.requireNonNull(snapshot.getString("longitude"))),
                                    // Parse the database type string to double
                                    Double.parseDouble(Objects.requireNonNull(snapshot.getString("rate"))),
                                    snapshot.getString("contactNumber"),
                                    snapshot.getString("imageURL"),
                                    snapshot.getString("locationName"),
                                    // Parse the database type string to int
                                    Integer.parseInt(Objects.requireNonNull(snapshot.getString("review"))),
                                    snapshot.getString("city"));
                            // add each instance to the list
                            cinemaList.add(cinema);
                        }
                        // After getting the data successfully, run the run back
                        callback.run();
                    }
                });
    }
    
}
