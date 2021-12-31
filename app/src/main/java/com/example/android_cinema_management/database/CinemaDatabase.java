package com.example.android_cinema_management.database;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.android_cinema_management.Model.Cinema;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Objects;

public class CinemaDatabase {

    /**
     * Method to filter cinema from Firebase Firestore
     */
    public static void filterData(FirebaseFirestore db,
                                  Context context,
                                  ProgressDialog pd,
                                  String city,
                                  String rate,
                                  String review, int state,
                                  Runnable callback) {
        // set title of progress bar
        pd.setTitle("Filtering cinemas ... ");

        // show progress when user press update button
        pd.show();

        CollectionReference collectionRef = db.collection("Cinema");
        Query query = null;

       /*
       - 1 1 1 default
       - 0 0 0 -> run show data

       - 1 0 0
       - 0 1 0
       - 0 0 1

       - 1 1 0
       - 1 0 1
       - 0 1 1
       */
        switch (state) {
            case 0:
                callback.run();
                break;
            case 1:
                query = collectionRef.whereEqualTo("city", city);
                break;
            case 2:
                query = collectionRef.whereEqualTo("rate", rate);
                break;
            case 3:
                query = collectionRef.whereEqualTo("review", review);
                break;
            case 4:
                query = collectionRef
                        .whereEqualTo("city", city).whereEqualTo("rate", rate);
                break;
            case 5:
                query = collectionRef
                        .whereEqualTo("city", city).whereEqualTo("review", review);
                break;
            case 6:
                query = collectionRef
                        .whereEqualTo("rate", rate).whereEqualTo("review", review);
                break;
            default:
                query = collectionRef
                        .whereEqualTo("city", city)
                        .whereEqualTo("rate", rate)
                        .whereEqualTo("review", review);
        }

        // start querying
        if (query != null) {
            query.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Filtering completed!", Toast.LENGTH_SHORT).show();
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

                        System.out.println(cinema.toString());
                    }
                }
            });
        }
    }

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
