package com.example.android_cinema_management.UserManagement.AdminManagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.android_cinema_management.R;

public class DiscountActivity extends AppCompatActivity {


    Button postDiscount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        postDiscount = findViewById(R.id.addDiscount);

        postDiscount.setOnClickListener(View -> {
            Intent intent = new Intent(DiscountActivity.this, AddDiscount.class);
            startActivity(intent);
        });
    }
}