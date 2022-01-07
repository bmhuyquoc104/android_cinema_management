package com.example.android_cinema_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.Adapter.FeedbackAdapter;
import com.example.android_cinema_management.Adapter.ReviewAdapter;
import com.example.android_cinema_management.Model.Feedback;
import com.example.android_cinema_management.Model.Review;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ReadReview extends AppCompatActivity {

    //Declare RecyclerView ReviewAdapter ArrayList FirebaseFirestore
    RecyclerView recyclerView;
    FeedbackAdapter feedbackAdapter;
    ArrayList<Feedback> resultContainer;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_review);

        //Initialize recyclerView and db
        recyclerView = findViewById(R.id.feedbackRecyclerView);
        db = FirebaseFirestore.getInstance();
        //set recyclerView to a fixed size
        recyclerView.setHasFixedSize(true);
        //set layoutManager for recycleView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Initialize resultContainer and reviewAdapter
        resultContainer = new ArrayList<>();
        feedbackAdapter = new FeedbackAdapter(this, resultContainer);
        //Set recyclerView to reviewAdapter
        recyclerView.setAdapter(feedbackAdapter);


        //call function get feedback data
        getReviews(db, resultContainer, () -> {
            for (Feedback v: resultContainer) {
                System.out.println(v.toString());
            }
        });


    }

    //Function get reviews data
    private void getReviews(FirebaseFirestore db, ArrayList<Feedback> resultContainer, Runnable callback) {
        db.collection("feedback").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
                    Feedback dataContainer = doc.toObject(Feedback.class);
                    resultContainer.add(dataContainer);
                }
                callback.run();
            }
        });
    }
}