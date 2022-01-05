package com.example.android_cinema_management.UserManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.android_cinema_management.R;

public class UserReview extends AppCompatActivity {


    EditText reviewMovieName, reviewBox;
    Button postReviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_review);

        reviewBox = findViewById(R.id.reviewBox);
    }
}