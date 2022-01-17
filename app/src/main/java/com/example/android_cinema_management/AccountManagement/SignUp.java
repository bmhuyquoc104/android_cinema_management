package com.example.android_cinema_management.AccountManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android_cinema_management.R;

public class SignUp extends AppCompatActivity {
    //Declare textview ,button ,and imageView
    ImageView close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Binding to XML's value
        close = findViewById(R.id.signUpClose);

        // Add first fragment to activity
        FragmentManager fm = getSupportFragmentManager();
        SignUpFragment fragment = new SignUpFragment();
        fm.beginTransaction().add(R.id.signUp_frameLayout, fragment).commit();


        // Function to get back to previous account fragment
        close.setOnClickListener(view -> {
            finish();
        });


    }

    @Override
    public void onBackPressed(){

    };
}
