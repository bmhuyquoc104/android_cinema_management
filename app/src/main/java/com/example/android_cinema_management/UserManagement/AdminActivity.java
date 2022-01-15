package com.example.android_cinema_management.UserManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_cinema_management.MainActivity;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.AdminManagment.CinemaActivity;
import com.example.android_cinema_management.UserManagement.AdminManagment.ComboActivity;
import com.example.android_cinema_management.UserManagement.AdminManagment.FeedbackActivity;


public class AdminActivity extends AppCompatActivity {
    // Declare imageview,string and textview
    ImageView logout, discount, combo, cinema,feedback;
    TextView welcome;
    String fullName;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        //Binding to XML value
        logout = findViewById(R.id.admin_logout_iv);
        discount = findViewById(R.id.admin_home_discount_iv);
        combo = findViewById(R.id.admin_home_combo_iv);
        cinema = findViewById(R.id.admin_home_cinema_iv);
        feedback = findViewById(R.id.admin_home_feedback_iv);
        welcome = findViewById(R.id.admin_home_name);

        //Receive intents from previous activity
        Intent intent = getIntent();
        if (intent != null) {
            // Identify each intent by its key and then assign the value to the text view to display
            if (intent.hasExtra("name")) {
                fullName = intent.getStringExtra("name");
            }
            // If somehow the intent is not being sent or received
            else {
                String text = "Hello";
                welcome.setText(text);
            }
        }

        //Create spannalbe String
        SpannableStringBuilder builder = new SpannableStringBuilder();

        // Text and color for string 1
        SpannableString str1= new SpannableString("Welcome: ");
        str1.setSpan(new ForegroundColorSpan(Color.rgb(161,161,161)), 0, str1.length(), 0);
        builder.append(str1);

        // Text and color for string 2
        SpannableString str2= new SpannableString("\n" + fullName);
        str2.setSpan(new ForegroundColorSpan(Color.rgb(222,22,25)), 0, str2.length(), 0);
        builder.append(str2);

        // Set text for textView
        welcome.setText( builder, Button.BufferType.SPANNABLE);

        //Listen to onClick Cinema button
        cinema.setOnClickListener(view -> {
            Intent intentCinema = new Intent(this, CinemaActivity.class);
            startActivity(intentCinema);
        });

        //Listen to onClick Feedback button
        feedback.setOnClickListener(view -> {
            Intent intentFeedback = new Intent(this, FeedbackActivity.class);
            startActivity(intentFeedback);
        });

        combo.setOnClickListener(view -> {
            Intent intentCombo = new Intent(this, ComboActivity.class);
            startActivity(intentCombo);
        });

        /*
        *Function to close activity
        * */
        logout.setOnClickListener(view ->{
            Intent intent2 = new Intent(this, MainActivity.class);
            // Delete all stacks before to avoid stack memory redundant and collapse between stacks
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent2);
            MainActivity.isLogin = false;
        });
    }
    //Not allow user to use the back button on phone
    @Override
    public void onBackPressed(){

    };
}