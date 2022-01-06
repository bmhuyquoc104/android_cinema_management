package com.example.android_cinema_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.Model.Review;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ReadReview extends AppCompatActivity {

    TextView userName, movieName, movieRating, reviewContent;
    ImageButton likeBtn, dislikeBtn;
    int countLike = 0, countDislike = 0;

    ArrayList<Review> resultContainer;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_item);

        userName = findViewById(R.id.userFullName);
        movieName = findViewById(R.id.displayMovieName);
        movieRating = findViewById(R.id.displayMovieRating);

        likeBtn =  findViewById(R.id.likeButton);
        dislikeBtn = findViewById(R.id.dislikeButton);

        db = FirebaseFirestore.getInstance();
        resultContainer = new ArrayList<>();


        likeBtn.setOnClickListener(View -> {
            countLike++;
//            db.collection("reviews").document("like").set(countLike);
            getReviews(db, resultContainer, () -> {
                for (Review v: resultContainer) {
                    System.out.println(v.toString());
                }
            });
            Toast.makeText(this, "EYYYYY", Toast.LENGTH_SHORT).show();
        });

        dislikeBtn.setOnClickListener(View -> {
            countDislike++;
//            db.collection("reviews").document("dislike").set(countDislike);
        });


    }

    private void getReviews(FirebaseFirestore db, ArrayList<Review> resultContainer, Runnable callback) {
        db.collection("reviews").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
                    Review dataContainer = doc.toObject(Review.class);
                    resultContainer.add(dataContainer);
                }
                callback.run();
            }
        });
    }
}