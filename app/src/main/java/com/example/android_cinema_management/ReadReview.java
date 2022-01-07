package com.example.android_cinema_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.Adapter.ReviewAdapter;
import com.example.android_cinema_management.Model.Review;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ReadReview extends AppCompatActivity {

    RecyclerView recyclerView;
    ReviewAdapter reviewAdapter;
    ArrayList<Review> resultContainer;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_review);


        recyclerView = findViewById(R.id.reviewRecyclerView);
        db = FirebaseFirestore.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        resultContainer = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(this, resultContainer);
        recyclerView.setAdapter(reviewAdapter);



        getReviews(db, resultContainer, () -> {
            for (Review v: resultContainer) {
                System.out.println(v.toString());
            }
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