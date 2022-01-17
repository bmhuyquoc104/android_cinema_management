package com.example.android_cinema_management.UserManagement.AdminManagment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_cinema_management.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddDiscountFragment extends Fragment {

    EditText name, month, content, image;
    Button addButton;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public AddDiscountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_discount, container, false);

        name = view.findViewById(R.id.admin_new_discount_name_et);
        month = view.findViewById(R.id.admin_new_discount_month_sent_et);
        content = view.findViewById(R.id.admin_new_discount_content_sent_et);
        image = view.findViewById(R.id.admin_new_discount_image_sent_et);
        addButton = view.findViewById(R.id.admin_new_discount_add_bt);

        addButton.setOnClickListener(view1 -> {
            String discountId = UUID.randomUUID().toString();
            Map<String, Object> discountMap = new HashMap<>();
            discountMap.put("discountId", discountId);
            discountMap.put("name", name.getText().toString());
            discountMap.put("month", month.getText().toString());
            discountMap.put("content", content.getText().toString());
            discountMap.put("image", image.getText().toString());

            DocumentReference documentReferenceForDiscount = db.collection("Discounts").document(discountId);
            documentReferenceForDiscount.set(discountMap).addOnCompleteListener(task -> {
               if (task.isSuccessful()){
                   Toast.makeText(getActivity(), "DISCOUNT ADDED", Toast.LENGTH_SHORT).show();
               }
            });
        });

        return view;
    }
}