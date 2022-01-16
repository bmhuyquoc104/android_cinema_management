package com.example.android_cinema_management.database;

import com.example.android_cinema_management.Model.Combo;
import com.example.android_cinema_management.Model.Voucher;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class ComboDatabase {
    //Function get combos data
    public static void getCombos(FirebaseFirestore db, ArrayList<Combo> comboList, Runnable callback) {
        db.collection("combo").get()
                .addOnCompleteListener(task -> {
                    // Check if task is successfully or not
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot snapshot : task.getResult()) {
                            // Create an instance and load data to voucher
                            Combo combo = new Combo(
                                    snapshot.getString("id"),
                                    snapshot.getString("name"),
                                    snapshot.getString("description"),
                                    snapshot.getString("price"),
                                    snapshot.getString("image")
                                    // Parse the database type string to int
                            );
                            // add each instance to the list
                            comboList.add(combo);
                            System.out.println("huy ne" + comboList);

                        }
                        // After getting the data successfully, run the run back
                        callback.run();
                    }
                });
    }

}
