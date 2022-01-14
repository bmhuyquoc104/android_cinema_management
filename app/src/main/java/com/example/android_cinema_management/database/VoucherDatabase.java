package com.example.android_cinema_management.database;

import com.example.android_cinema_management.Model.Voucher;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class VoucherDatabase {
    // Method to fetch all voucher data from the firebase FireStore to voucher
    public static void fetchVoucherDatabase(FirebaseFirestore db, ArrayList<Voucher> voucherList, Runnable callback) {
        // Check the collection name and get it
        db.collection("Voucher").get()
                .addOnCompleteListener(task -> {
                    // Check if task is successfully or not
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot snapshot : task.getResult()) {
                            // Create an instance and load data to voucher
                            Voucher voucher = new Voucher(
                                    snapshot.getString("image"),
                                    snapshot.getString("name"),
                                    snapshot.getString("price"),
                                    snapshot.getString("pointRequired"),
                                    // Parse the database type string to int
                                    snapshot.getString("id")
                            );
                            // add each instance to the list
                            voucherList.add(voucher);

                        }
                        // After getting the data successfully, run the run back
                        callback.run();
                    }
                });
    }
}
