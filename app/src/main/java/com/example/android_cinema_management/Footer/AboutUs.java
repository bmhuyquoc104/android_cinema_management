package com.example.android_cinema_management.Footer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_cinema_management.R;

public class AboutUs extends AppCompatActivity {

//    Writing the content as strings
    private String content = "It happens millions of times each week – a customer receives a ticket from Universal" +
            " – but each interaction is unique.\n\n" +
            "It’s just a moment in time – just one hand reaching over the counter to present a ticket to " +
            "another outstretched hand.\n\n" +
            "But it’s a connection.\n\n" +
            "We make sure everything we do honours that connection – from our commitment to the" +
            " highest quality movies in the world, to the way we engage with our customers and" +
            " communities to do business responsibly.\n\n" +
            "From our beginnings as a single theater nearly one month ago, in every place that we’ve" +
            " been, and every place that we touch, we've tried to make it a little better than we" +
            " found it.";
//    Declare the components
    private TextView AboutUsContent;
    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Getting the view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        AboutUsContent = findViewById(R.id.aboutUsContent);

//        Setting the content
        AboutUsContent.setText(content);

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