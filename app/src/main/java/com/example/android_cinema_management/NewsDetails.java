package com.example.android_cinema_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_cinema_management.Model.News;
import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {
    private TextView NewsName, NewsDate, NewsDetail;
    private ImageView NewsImage;
    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

//        Get the extra information to display
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        News news = (News) bundle.get("object_news");

        NewsName = findViewById(R.id.newsName);
        NewsDate = findViewById(R.id.newsDate);
        NewsDetail = findViewById(R.id.newsDetail);

        NewsImage = findViewById(R.id.newsImg);

//        The back button
        Back = findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


//        Setting the information into the views
        String name = news.getName();
        String date = news.getMonth();
        String content = news.getContent();

        NewsName.setText(getBaseContext().getString(R.string.discountName, name));
        NewsDate.setText(getBaseContext().getString(R.string.discountDate, date));
        NewsDetail.setText(content);

        Picasso.get().load(news.getImage()).into(NewsImage);
    }

    @Override
    public void onBackPressed(){

    };
}