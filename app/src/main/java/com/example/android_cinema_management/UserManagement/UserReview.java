package com.example.android_cinema_management.UserManagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class UserReview extends AppCompatActivity {


    EditText reviewMovieName, reviewBox, ratingMovie;
    Button postReviewButton;

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_review);

        reviewBox = findViewById(R.id.reviewBox);
        reviewMovieName = findViewById(R.id.editText_review_movieName);
        ratingMovie = findViewById(R.id.editTextRate);

        postReviewButton = findViewById(R.id.postReviewButton);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        postReviewButton.setOnClickListener(View -> {
            String randomId = UUID.randomUUID().toString();

            Map<String, Object> reviewMap = new HashMap<>();
            reviewMap.put("reviewId", randomId);
            reviewMap.put("movieName", reviewMovieName.getText().toString());
            reviewMap.put("rateMovie", ratingMovie.getText().toString());
            reviewMap.put("reviewContent", reviewBox.getText().toString());
            reviewMap.put("like", 0);
            reviewMap.put("dislike", 0);

            DocumentReference documentReference = db.collection("Users").document(userId);
            documentReference.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    User user = documentSnapshot.toObject(User.class);

                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("fullName", Objects.requireNonNull(user).getFullName());
                    userMap.put("email", user.getEmail());
                    userMap.put("id", user.getId());

                    reviewMap.put("user", userMap);

                    DocumentReference documentReferenceForReview = db.collection("reviews")
                            .document(randomId);
                    documentReferenceForReview.set(reviewMap).addOnCompleteListener(taskInner -> {
                        if (taskInner.isSuccessful()) {
                            Toast.makeText(this, "REVIEWED ADDED!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        });
    }
}