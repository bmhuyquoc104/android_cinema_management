package com.example.android_cinema_management.database;

import com.example.android_cinema_management.Model.Review;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class ReviewDatabase {
    public static void getReviewsByCurrentUser(FirebaseFirestore db, ArrayList<Review> reviewArrayList, Runnable callback, FirebaseUser user) {
        String email = user.getEmail();
        ArrayList<Review> reviewArrayList2 = new ArrayList<>();
        reviewArrayList.clear();
        db.collection("reviews").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
                    count++;
                    Review dataContainer = doc.toObject(Review.class);
                    reviewArrayList2.add(dataContainer);

                }
                for ( Review review:
                        reviewArrayList2) {
                    if(review.getUser().get("email").equals(email)){
                        reviewArrayList.add(review);
                    }
                }
                System.out.println(reviewArrayList);
                if (count == Objects.requireNonNull(task.getResult()).size()) {
                    callback.run();
                }
            }
        });
    }
}
