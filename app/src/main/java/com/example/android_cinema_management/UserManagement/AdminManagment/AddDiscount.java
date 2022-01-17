package com.example.android_cinema_management.UserManagement.AdminManagment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android_cinema_management.Adapter.DiscountAdapter;
import com.example.android_cinema_management.Adapter.ReviewFragmentAdapter;
import com.example.android_cinema_management.Model.Discount;
import com.example.android_cinema_management.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class AddDiscount extends AppCompatActivity {

    //Declare TextInputEditText TextInputLayout AutoCompleteTextView Button String and FirebaseFirestore
    private TextInputEditText name, content, image;
    private TextInputLayout monthMenu;
    private AutoCompleteTextView monthACT;
    private Button post, back;
    private String monthChosen, nameInput, contentInput, imageInput;
    private FirebaseFirestore db;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discount);
      
//        Getting the views
        name = findViewById(R.id.ed_name);
        content = findViewById(R.id.ed_content);
        image = findViewById(R.id.ed_image);
        monthMenu = findViewById(R.id.ti_month);
        monthACT = findViewById(R.id.month_items);
        post = findViewById(R.id.bt_post);
        back = findViewById(R.id.bt_back);

        //Initailize db of FirebaseFirestore
        db = FirebaseFirestore.getInstance();

        //Get the extra information to display
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        //Assign the extra info
        Discount discount = (Discount) bundle.get("object_discount");
        String id = (String) bundle.get("id_discount");

        if (bundle != null) {
            post.setText("Update");
            name.setText(discount.getName());
            content.setText(discount.getContent());
            monthChosen = discount.getMonth();
            image.setText(discount.getImage());
        } else {
            post.setText("Post");
        }

        Toast.makeText(AddDiscount.this,
                id,
                Toast.LENGTH_SHORT).show();

//        For choosing the month
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "June", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(this, R.layout.month_list, months);
        monthACT.setAdapter(monthAdapter);
        monthACT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                monthChosen = parent.getItemAtPosition(position).toString();
            }
        });

//        Getting the strings and then save them

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameInput = name.getText().toString();
                contentInput = content.getText().toString();
                imageInput = image.getText().toString();


                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null) {
                    String id = UUID.randomUUID().toString();
                    saveToFireStore(id, nameInput, monthChosen, contentInput, imageInput);
                }
            }
        });

        //Listen to back onClick event
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

//    private void updateToFireStore(String id, String n, String m, String c, String i) {
//        db.collection("Discounts").document(id).update("Name", n, "Month", m, "Content", c, "Image", i)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(AddDiscount.this,
//                                "Discount updated.",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(AddDiscount.this,
//                        "Failed to update discount.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    Saving the content to the database
    private void saveToFireStore(String id, String n, String m, String c, String i) {
        if (!n.isEmpty() && !m.isEmpty() && !c.isEmpty() && !i.isEmpty()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("Name", n);
            map.put("Month", m);
            map.put("Content", c);
            map.put("Image", i);

            System.out.println("DISCOUNT MAPPPPPPPPPPPP: " + map);

            DocumentReference documentReferenceForDiscount = db.collection("Discounts").document(id);
            documentReferenceForDiscount.set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(AddDiscount.this,
                            "Discount added.",
                            Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddDiscount.this,
                            "Failed to add discount.",
                            Toast.LENGTH_SHORT).show();
                }
            });
//            db.collection("Discounts").document(id)(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            Toast.makeText(AddDiscount.this,
//                                    "Discount added.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(AddDiscount.this,
//                                    "Failed to add discount.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    });
        } else {
            Toast.makeText(AddDiscount.this,
                    "No empty field allowed.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}