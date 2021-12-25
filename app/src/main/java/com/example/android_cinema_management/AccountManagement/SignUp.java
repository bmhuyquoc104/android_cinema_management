package com.example.android_cinema_management.AccountManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.AccountManagement.Accounts;
import com.example.android_cinema_management.MainActivity;
import com.example.android_cinema_management.R;

public class SignUp extends AppCompatActivity {
    //Declare textview and imageView
    TextView title;
    ImageView close;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Binding to XML's value
        title = findViewById(R.id.signUp_title);
        close = findViewById(R.id.signUpClose);

        // Create spannalbe String
        SpannableStringBuilder builder = new SpannableStringBuilder();

        // Text and color for string 1
        SpannableString str1= new SpannableString("Register Here");
        str1.setSpan(new ForegroundColorSpan(Color.rgb(161,161,161)), 0, str1.length(), 0);
        builder.append(str1);

        // Text and color for string 2
        SpannableString str2= new SpannableString("!");
        str2.setSpan(new ForegroundColorSpan(Color.rgb(222,22,25)), 0, str2.length(), 0);
        builder.append(str2);
        // Set text for textView
        title.setText( builder, Button.BufferType.SPANNABLE);
        // Function to get back to previous account fragment
        close.setOnClickListener(view ->{
//            Intent intent = new Intent(this, MainActivity.class);
//            //Send intent to sign Up activity
//
//            // Start intent
//            try {
//                startActivity(intent);
//            }
//            // Exception if activity is not found
//            catch (ActivityNotFoundException e){
//                Toast.makeText(this,"Oops!! Something wrong, Please try again!" ,Toast.LENGTH_LONG).show();
//            }
            // Return to the last stack
            finish();
        });
    }
}