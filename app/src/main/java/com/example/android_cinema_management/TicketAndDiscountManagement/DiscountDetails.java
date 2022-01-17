package com.example.android_cinema_management.TicketAndDiscountManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_cinema_management.Model.Discount;
import com.example.android_cinema_management.R;
import com.squareup.picasso.Picasso;

public class DiscountDetails extends AppCompatActivity {

    private TextView DiscountDetail;
    private ImageView DiscountImage;
    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_details);

//        Get the extra information to display
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        Discount discount = (Discount) bundle.get("object_discount");

        DiscountDetail = findViewById(R.id.discountDetail);

        DiscountImage = findViewById(R.id.discountImg);

//        The back button
        Back = findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


//        Setting the information into the views
        String name = discount.getName();
        String date = discount.getMonth();
        String content = discount.getContent();

//        Set the content
        DiscountDetail.setText(content);

//        Set the image
        Picasso.get().load(discount.getImage()).into(DiscountImage);
    }
    @Override
    public void onBackPressed(){

    };
}