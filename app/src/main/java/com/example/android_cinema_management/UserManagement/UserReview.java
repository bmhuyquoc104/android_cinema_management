package com.example.android_cinema_management.UserManagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        userId = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Map<String, String> userMap = new HashMap<>();
                userMap.put("email", value.getString("email"));
                userMap.put("fullName", value.getString("fullName"));
                userMap.put("id", value.getString("id"));
            }
        });


    }
}