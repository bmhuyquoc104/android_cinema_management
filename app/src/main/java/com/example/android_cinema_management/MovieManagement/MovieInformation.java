package com.example.android_cinema_management.MovieManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.android_cinema_management.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieInformation extends AppCompatActivity {
    //Declare login and register button
    Button loginAndRegister;
    // Declare textview
    TextView vietnameseTitle,englishTitle,releaseDate;
    // Declare imageView
    ImageView movieImage;
    //Declare videoView
    VideoView movieTrailer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);
        // Binding with xml layout
        loginAndRegister = findViewById(R.id.loginAndRegister);
        vietnameseTitle = findViewById(R.id.mi_vietnameseTitle);
        englishTitle = findViewById(R.id.mi_englishTitle);
        releaseDate = findViewById(R.id.mi_releaseDate);
        movieImage = findViewById(R.id.mi_movieImage);
        movieTrailer = findViewById(R.id.mi_movieTrailer);
        // Create spannalbe String
        SpannableStringBuilder builder = new SpannableStringBuilder();

        // Text and color for string 1
        SpannableString str1= new SpannableString("Login / ");
        str1.setSpan(new ForegroundColorSpan(Color.rgb(161,161,161)), 0, str1.length(), 0);
        builder.append(str1);

        // Text and color for string 2
        SpannableString str2= new SpannableString("Sign Up");
        str2.setSpan(new ForegroundColorSpan(Color.rgb(222,22,25)), 0, str2.length(), 0);
        builder.append(str2);

        // Set text for button
        loginAndRegister.setText( builder, Button.BufferType.SPANNABLE);

        // Get intent from other activities
        Intent intent = getIntent();

        if(intent != null) {
            if (intent.hasExtra("vietnameseTitle")) {
                String vnTitle = intent.getStringExtra("vietnameseTitle");
                // Set text for vietnamese title textview
                vietnameseTitle.setText(vnTitle);
            }
            if (intent.hasExtra("englishTitle")) {
                String enTitle = intent.getStringExtra("englishTitle");
                // Set text for english title textview

                englishTitle.setText(enTitle);
            }

            if (intent.hasExtra("imageUrl")) {
                String imageUrl = intent.getStringExtra("imageUrl");
                // Set image for image view
                Picasso.get().load(imageUrl).into(movieImage);
            }

        }
    }
}