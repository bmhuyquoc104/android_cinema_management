package com.example.android_cinema_management.Footer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_cinema_management.R;

public class Job extends AppCompatActivity {
//    Setting the content as String
    private String jobContent = "UNIVERSAL is a unit within Our Group, one of RMIT’s largest conglomerates," +
            " doing business in 1 country. UNIVERSAL is one of top 5 cinema system and film" +
            " distributor in RMIT, leading film distributor and cinema system in Vietnam.\n\n" +
            "UNIVERSAL always offers employment opportunities for young, dynamic talents," +
            " who’s in love with film industry.\n\n" +
            "If you wish to join our UNIVERSAL family and become one of our Unians," +
            " please refer some following ways:\n\n\n" +
            "1. Search through our vacancy list, pick the one that most suits you and send" +
            " your CV together with your photo directly to unitalent@cj.net with email subject" +
            " \"[NAME] - Application for [JOB TITLE] – [WORKING LOCATION]\" to apply.\n\n" +
            "2. Register through online links.\n\n" +
            "3. Direct application at Cinema Sites.\n\n";

    private TextView JobContent;
    private ImageView Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Getting the views
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

//        Setting the content
        JobContent = findViewById(R.id.jobContent);
        JobContent.setText(jobContent);

//        Button to go back
        Back = findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){

    };
}