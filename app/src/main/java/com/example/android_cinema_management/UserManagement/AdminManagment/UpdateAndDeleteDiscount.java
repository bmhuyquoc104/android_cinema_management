package com.example.android_cinema_management.UserManagement.AdminManagment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android_cinema_management.Model.Combo;
import com.example.android_cinema_management.Model.Discount;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.AdminActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateAndDeleteDiscount extends AppCompatActivity {

    EditText name, month, content, image;
    Button update, delete;
    ImageView close;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Discount discount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete_discount);

        discount = (Discount) getIntent().getSerializableExtra("discount");

        name = findViewById(R.id.admin_discount_name_et);
        month = findViewById(R.id.admin_discount_month_sent_et);
        content = findViewById(R.id.admin_discount_content_sent_et);
        image = findViewById(R.id.admin_discount_image_sent_et);
        update = findViewById(R.id.admin_update_discount_update_bt);
        delete = findViewById(R.id.admin_delete_discount_delete_bt);

        close = findViewById(R.id.admin_update_discount_close_iv);

        close.setOnClickListener(View ->{
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
        });
        name.setText(discount.getName());
        month.setText(discount.getMonth());
        content.setText(discount.getContent());
        image.setText(discount.getImage());

        update.setOnClickListener(view -> {
            Map<String, Object> discountMap = new HashMap<>();
            discountMap.put("discountId", discount.getDiscountId());
            discountMap.put("name", name.getText().toString());
            discountMap.put("month", month.getText().toString());
            discountMap.put("content", content.getText().toString());
            discountMap.put("image", image.getText().toString());

            DocumentReference documentReference = db.collection("Discounts").document(discount.getDiscountId());
            documentReference.set(discountMap).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(this, "DISCOUNT UPDATED", Toast.LENGTH_SHORT).show();
                }
            });
        });

        delete.setOnClickListener(view -> {
            db.collection("Discounts").document(discount.getDiscountId())
                    .delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(UpdateAndDeleteDiscount.this, "DISCOUNT DELETED", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(UpdateAndDeleteDiscount.this, "FAILED TO DELETE DISCOUNT", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

    }

    //Not allow user to use the back button on phone
    @Override
    public void onBackPressed(){

    };
}