package com.example.android_cinema_management.database;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.Model.Voucher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


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
                                    snapshot.getString("id"),
                                    snapshot.getString("image"),
                                    snapshot.getString("name"),
                                    snapshot.getString("pointRequired"),
                                    snapshot.getString("price")
                                    // Parse the database type string to int
                            );
                            // add each instance to the list
                            voucherList.add(voucher);

                        }
                        // After getting the data successfully, run the run back
                        callback.run();
                    }
                });
    }

    public static void postUserVoucher ( ProgressDialog pd,FirebaseFirestore db, Context context,
                                        FirebaseAuth firebaseAuth,FirebaseUser mUser,String name){

        pd.setTitle("Getting Voucher!");
        pd.show();
        //Generate random Id
        String Id = UUID.randomUUID().toString();
        // Get the current time to compare later
        Date currentTime = Calendar.getInstance().getTime();
        Date currentDate = Calendar.getInstance().getTime();
        // Set the format of time
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df2 = new SimpleDateFormat("kk:mm");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
        String currentTimeFormat = df2.format(currentTime);
        String currentDateFormat = df3.format(currentTime);
        Map<String, Object> voucherMap = new HashMap<>();
        voucherMap.put("id", Id);
        voucherMap.put("name", name);
        voucherMap.put("time", currentTimeFormat);
        voucherMap.put("date", currentDateFormat);


        //Getting user's full name and user's email of current login user into userMap
        DocumentReference documentReference = db.collection("Users").document(mUser.getUid());
        documentReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                User user = documentSnapshot.toObject(User.class);

                Map<String, String> userMap = new HashMap<>();
                userMap.put("fullName", Objects.requireNonNull(user).getFullName());
                userMap.put("email", user.getEmail());
                userMap.put("phone", user.getPhone());
                userMap.put("id", mUser.getUid());

                //Then put userMap into reviewMap
                voucherMap.put("user", userMap);

                //Saving reviewMap into Firestore in reviews collection
                DocumentReference documentReferenceForReview = db.collection("UserVoucher")
                        .document(Id);
                documentReferenceForReview.set(voucherMap).addOnCompleteListener(taskInner -> {
                    if (taskInner.isSuccessful()) {
                        pd.dismiss();
                    }
                });
            }
        });
    }
}
