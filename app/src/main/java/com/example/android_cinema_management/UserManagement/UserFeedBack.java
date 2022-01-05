package com.example.android_cinema_management.UserManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.android_cinema_management.R;

public class UserFeedBack extends AppCompatActivity {


    EditText feedbackMovieName, feedbackBox;
    Button postFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed_back);

        feedbackMovieName = findViewById(R.id.editText_review_movieName);
        feedbackBox = findViewById(R.id.feedbackBox);
        postFeedback = findViewById(R.id.postFeedbackButton);


    }
}