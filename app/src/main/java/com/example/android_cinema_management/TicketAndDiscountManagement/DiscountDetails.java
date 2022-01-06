package com.example.android_cinema_management.TicketAndDiscountManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_cinema_management.Model.Discount;
import com.example.android_cinema_management.R;

public class DiscountDetails extends AppCompatActivity {

    private TextView DiscountName, DiscountDate, DiscountDetail;
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

        DiscountName = findViewById(R.id.discountName);
        DiscountDate = findViewById(R.id.discountDate);
        DiscountDetail = findViewById(R.id.discountDetail);

//        The back button
        Back = findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


//        Setting the information into the views
        String name = discount.getName();
        String date = discount.getContent();
        DiscountName.setText(getBaseContext().getString(R.string.discountName, name));
        DiscountDate.setText(getBaseContext().getString(R.string.discountDate, date));
    }
}