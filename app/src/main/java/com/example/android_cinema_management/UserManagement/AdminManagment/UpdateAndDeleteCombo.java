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
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.AdminActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UpdateAndDeleteCombo extends AppCompatActivity {

    EditText comboName, comboDescription, comboPrice, imageURL;
    Button updateBtn, deleteBtn;
    ImageView close;
    //Declare FirebaseFirestore FirebaseAuth FirebaseUser String
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Combo combo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete_combo);

        combo = (Combo) getIntent().getSerializableExtra("combo");


        comboName = findViewById(R.id.admin_update_combo_name_et);
        comboDescription = findViewById(R.id.admin_update_combo_description_sent_et);
        comboPrice = findViewById(R.id.admin_combo_price_et);
        imageURL = findViewById(R.id.admin_update_combo_image_sent_et);
        updateBtn = findViewById(R.id.admin_update_combo_update_bt);
        deleteBtn = findViewById(R.id.admin_delete_combo_delete_bt);
        close = findViewById(R.id.admin_update_combo_close_iv);

        close.setOnClickListener(View ->{
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
        });


        System.out.println(combo.getPrice());
        comboName.setText(combo.getName());
        comboDescription.setText(combo.getDescription());
//        price.setText(combo.getPrice());
        imageURL.setText(combo.getImage());
        comboPrice.setText(combo.getPrice());
        updateBtn.setOnClickListener(view -> {
            Map<String, Object> comboMap = new HashMap<>();
            comboMap.put("id", combo.getId());
            comboMap.put("name", comboName.getText().toString());
            comboMap.put("description", comboDescription.getText().toString());
            comboMap.put("price", comboPrice.getText().toString());
            comboMap.put("image", imageURL.getText().toString());

            DocumentReference documentReference = db.collection("combo").document(combo.getId());
            documentReference.set(comboMap).addOnCompleteListener(task -> {
               if (task.isSuccessful()){
                   Toast.makeText(this, "COMBO UPDATED", Toast.LENGTH_SHORT).show();
               }
            });
        });

        deleteBtn.setOnClickListener(view -> {
            db.collection("combo").document(combo.getId())
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

    //Not allow user to use the back button on phone
    @Override
    public void onBackPressed(){

    };
}