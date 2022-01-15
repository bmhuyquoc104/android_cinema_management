package com.example.android_cinema_management.UserManagement.AdminManagment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_cinema_management.Model.Combo;
import com.example.android_cinema_management.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UpdateAndDeleteCombo extends AppCompatActivity {

    EditText comboName, comboDescription, price, imageURL;
    Button updateBtn, deleteBtn;

    //Declare FirebaseFirestore FirebaseAuth FirebaseUser String
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Combo combo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete_combo);

        combo = (Combo) getIntent().getSerializableExtra("combo");

        comboName = findViewById(R.id.admin_add_combo_name_et);
        comboDescription = findViewById(R.id.admin_add_combo_description_sent_et);
        price = findViewById(R.id.admin_new_combo_price_sent_et);
        imageURL = findViewById(R.id.admin_add_combo_image_sent_et);
        updateBtn = findViewById(R.id.admin_update_combo_update_bt);
        deleteBtn = findViewById(R.id.admin_delete_combo_delete_bt);

        comboName.setText(combo.getComboName());
        comboDescription.setText(combo.getDescription());
        price.setText(combo.getPrice());
        imageURL.setText(combo.getImageURL());

        updateBtn.setOnClickListener(view -> {
            Map<String, Object> comboMap = new HashMap<>();
            comboMap.put("comboId", combo.getComboId());
            comboMap.put("comboName", comboName);
            comboMap.put("description", comboDescription);
            comboMap.put("price", price);
            comboMap.put("imageURL", imageURL);

            DocumentReference documentReference = db.collection("combo").document(combo.getComboId());
            documentReference.set(comboMap).addOnCompleteListener(task -> {
               if (task.isSuccessful()){
                   Toast.makeText(this, "COMBO UPDATED", Toast.LENGTH_SHORT).show();
               }
            });
        });

        deleteBtn.setOnClickListener(view -> {
            db.collection("combo").document(combo.getComboId())
                    .delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(UpdateAndDeleteCombo.this, "COMBO DELETED", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(UpdateAndDeleteCombo.this, "FAILED TO DELETE COMBO", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

    }


}