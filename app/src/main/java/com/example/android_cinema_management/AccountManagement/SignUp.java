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
    TextView title;
    ImageView close;
    // Declare viewpager 2 and adapter
    @SuppressLint("ResourceType")
    // Declare view layout and textview array
    LinearLayout dotLayout;
    TextView [] dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Binding to XML's value
        title = findViewById(R.id.signUp_title);
        close = findViewById(R.id.signUpClose);

        // Add first fragment to activity
        FragmentManager fm = getSupportFragmentManager();
        SignUpFragment fragment = new SignUpFragment();
        fm.beginTransaction().add(R.id.signUp_frameLayout, fragment).commit();
//        adapter = new RegisterAdapter(fm,getLifecycle());
//        viewpager2.setAdapter(adapter);
        // Instantiate textview array
        dots = new TextView[3];
//        dotsIndicator();
        // Create spannalbe String
        SpannableStringBuilder builder = new SpannableStringBuilder();

        // Text and color for string 1
        SpannableString str1 = new SpannableString("Register Here");
        str1.setSpan(new ForegroundColorSpan(Color.rgb(161, 161, 161)), 0, str1.length(), 0);
        builder.append(str1);

        // Text and color for string 2
        SpannableString str2 = new SpannableString("!");
        str2.setSpan(new ForegroundColorSpan(Color.rgb(222, 22, 25)), 0, str2.length(), 0);
        builder.append(str2);
        // Set text for textView
        title.setText(builder, Button.BufferType.SPANNABLE);
        // Function to get back to previous account fragment
        close.setOnClickListener(view -> {
            finish();
        });


    }
}
