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


    public static void showData(FirebaseFirestore db, ArrayList<Cinema> cinemaList, Runnable callback) {
        db.collection("Cinema").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot snapshot : task.getResult()) {
                            Cinema cinema = new Cinema(
                                    snapshot.getString("id"),
                                    snapshot.getString("name"),
                                    snapshot.getString("address"),
                                    Double.parseDouble(Objects.requireNonNull(snapshot.getString("latitude"))),
                                    Double.parseDouble(Objects.requireNonNull(snapshot.getString("longitude"))),
                                    Double.parseDouble(Objects.requireNonNull(snapshot.getString("rate"))),
                                    snapshot.getString("contactNumber"),
                                    snapshot.getString("imageURL"),
                                    snapshot.getString("locationName"),
                                    Integer.parseInt(Objects.requireNonNull(snapshot.getString("review"))),
                                    snapshot.getString("city"));
                            cinemaList.add(cinema);
                        }

                        callback.run();
                    }
                });
    }
}
