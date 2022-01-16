package com.example.android_cinema_management.database;

import com.example.android_cinema_management.Model.Feedback;
import com.example.android_cinema_management.Model.Transaction;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class FeedbackDatabase {
    public static void getFeedbacksByEmail(FirebaseFirestore db, ArrayList<Feedback> feedbackArrayList, Runnable callback, FirebaseUser user) {
        String email = user.getEmail();
        System.out.println("my email" + email);
        ArrayList<Feedback> feedbackArrayList2 = new ArrayList<>();
        feedbackArrayList.clear();
        db.collection("feedback").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
                    count++;
                    Feedback dataContainer = doc.toObject(Feedback.class);
                    feedbackArrayList2.add(dataContainer);
                    System.out.println(feedbackArrayList2.size());

//                    System.out.println(transactionArrayList);

                }
                for ( Feedback feedback:
                        feedbackArrayList2) {
                    System.out.println("database" + feedback.getUser().get("email"));
                    System.out.println("my email" + email);
                    if(Objects.equals(feedback.getUser().get("email"), email)){
                        System.out.println(Objects.equals(feedback.getUser().get("email"), email));
                        feedbackArrayList.add(feedback);
                        System.out.println(feedbackArrayList2);
                    }
                }
                if (count == Objects.requireNonNull(task.getResult()).size()) {
                    callback.run();
                }
            }

        });
    }
}
