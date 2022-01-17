package com.example.android_cinema_management.UserManagement.AdminManagment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_cinema_management.Model.Voucher;
import com.example.android_cinema_management.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateAndDeleteVoucher extends AppCompatActivity {

    EditText voucherName, voucherPoint, voucherPrice, imageURL;
    Button updateBtn, deleteBtn;

    //Declare FirebaseFirestore FirebaseAuth FirebaseUser String
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Voucher voucher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete_voucher);

        voucher = (Voucher) getIntent().getSerializableExtra("voucher");

        voucherName = findViewById(R.id.admin_add_voucher_name_et);
        voucherPoint = findViewById(R.id.admin_add_voucher_point_sent_et);
        voucherPrice = findViewById(R.id.admin_add_voucher_price_sent_et);
        imageURL = findViewById(R.id.admin_add_voucher_image_sent_et);
        updateBtn = findViewById(R.id.admin_update_voucher_update_bt);
        deleteBtn = findViewById(R.id.admin_delete_voucher_delete_bt);

        voucherName.setText(voucher.getId());
        voucherPoint.setText(voucher.getPointRequired());
        voucherPrice.setText(voucher.getPrice());
        imageURL.setText(voucher.getImage());

        updateBtn.setOnClickListener(view -> {
            Map<String, Object> voucherMap = new HashMap<>();
            voucherMap.put("id", voucher.getId());
            voucherMap.put("name", voucherName.getText().toString());
            voucherMap.put("pointRequire", voucherPoint.getText().toString());
            voucherMap.put("price", voucherPrice.getText().toString());
            voucherMap.put("image", imageURL.getText().toString());

            DocumentReference documentReference = db.collection("Voucher").document(voucher.getId());
            documentReference.set(voucherMap).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(this, "VOUCHER UPDATED", Toast.LENGTH_SHORT).show();
                }
            });
        });

        deleteBtn.setOnClickListener(view -> {
            db.collection("Voucher").document(voucher.getId())
                    .delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(UpdateAndDeleteVoucher.this, "VOUCHER DELETED", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(UpdateAndDeleteVoucher.this, "FAILED TO DELETE VOUCHER", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

    }
}