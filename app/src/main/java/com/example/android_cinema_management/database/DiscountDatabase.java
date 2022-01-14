package com.example.android_cinema_management.database;

import com.example.android_cinema_management.Model.Discount;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class DiscountDatabase {

    // Method to fetch all cinema data from the firebase FireStore to cinemaList
    public static void showData(FirebaseFirestore db, ArrayList<Discount> discountList, Runnable callback) {
        // Check the collection name and get it
        db.collection("Discounts").get()
            .addOnCompleteListener(task -> {
                // Check if task is successfully or not
                if (task.isSuccessful()) {
                    for (DocumentSnapshot snapshot : task.getResult()) {
                        // Create an instance and load data to cinema
                        Discount discount = new Discount(
                                snapshot.getString("Name"),
                                snapshot.getString("Month"),
                                snapshot.getString("Image"),
                                snapshot.getString("Content"));
                        // add each instance to the list
                        discountList.add(discount);
                    }
                    // After getting the data successfully, run the run back
                    callback.run();
                }
            });
    }
}
