package com.example.android_cinema_management.UserManagement.AdminManagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.AdminActivity;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddVoucherActivity extends AppCompatActivity {

    // Initialize EditText ImageView and Button
    EditText voucherName, point, price, imageURL, fullName;
    Button addButton;
    ImageView close;

    //Declare and initialize FirebaseFirestore
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voucher);

        //Binding variable with entity from xml file
        voucherName = findViewById(R.id.admin_new_voucher_name_et);
        point = findViewById(R.id.admin_new_voucher_point_sent_et);
        price = findViewById(R.id.admin_new_voucher_price_sent_et);
        imageURL = findViewById(R.id.admin_new_combo_image_sent_et);
        fullName = findViewById(R.id.admin_new_voucher_userName_sent_et);
        addButton = findViewById(R.id.admin_new_voucher_add_bt);

        //Listen to onClick close
        close.setOnClickListener(View ->{
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
        });

        //Listen to onClick addButton
        addButton.setOnClickListener(view -> {
            //Generate random discountId using UUID
            String voucherId = UUID.randomUUID().toString();
            Map<String, Object> voucherMap = new HashMap<>();
            voucherMap.put("voucherId", voucherId);
            voucherMap.put("voucherName", voucherName);
            voucherMap.put("voucherPrice", price);
            voucherMap.put("pointRequire", point);
            voucherMap.put("voucherImage", imageURL);
            voucherMap.put("fullName", fullName);

            //saving discountMap into collection name Discount
            DocumentReference documentReferenceForVoucher = db.collection("voucher")
                    .document(voucherId);
            documentReferenceForVoucher.set(voucherMap).addOnCompleteListener(task -> {
               if (task.isSuccessful()){
                   Toast.makeText(this, "VOUCHER ADDED", Toast.LENGTH_SHORT).show();
               }
            });
        });


    }

    @Override
    public void onBackPressed(){

    };
}