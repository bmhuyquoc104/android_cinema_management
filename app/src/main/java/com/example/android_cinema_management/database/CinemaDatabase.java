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


    public static void showData(FirebaseFirestore db, ArrayList<Cinema> cinemaList){
        db.collection("Cinema").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        cinemaList.clear();
                        for (DocumentSnapshot snapshot: task.getResult()){
                            Cinema cinema = new Cinema(
                                    snapshot.getString("id"),
                                    snapshot.getString("name"),
                                    snapshot.getString("address"),
                                    snapshot.getDouble("latitude"),
                                    snapshot.getDouble("longitude"),
                                    snapshot.getDouble("rate"),
                                    snapshot.getString("contactNumber"),
                                    snapshot.getString("imageURL"),
                                    snapshot.getString("locationName"),
                                    Integer.parseInt(Objects.requireNonNull(snapshot.getString("review"))),
                                    snapshot.getString("city"));
                            cinemaList.add(cinema);
                            System.out.println("huy ne " + cinemaList);
                        }
                    }
                });
    }
}
