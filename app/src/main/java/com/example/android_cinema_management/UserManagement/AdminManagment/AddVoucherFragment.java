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

public class AddVoucherFragment extends Fragment {


    EditText voucherName, point, price, imageURL;
    Button addButton;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public AddVoucherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_voucher, container, false);

        voucherName = view.findViewById(R.id.admin_new_voucher_name_et);
        point = view.findViewById(R.id.admin_new_voucher_point_sent_et);
        price = view.findViewById(R.id.admin_new_voucher_price_sent_et);
        imageURL = view.findViewById(R.id.admin_new_voucher_image_sent_et);
        addButton = view.findViewById(R.id.admin_new_voucher_add_bt);

        addButton.setOnClickListener(view1 -> {
            String voucherId = UUID.randomUUID().toString();
            Map<String, Object> voucherMap = new HashMap<>();
            voucherMap.put("id", voucherId);
            voucherMap.put("name", voucherName.getText().toString());
            voucherMap.put("price", price.getText().toString());
            voucherMap.put("pointRequire", point.getText().toString());
            voucherMap.put("image", imageURL.getText().toString());

            DocumentReference documentReferenceForVoucher = db.collection("Voucher")
                    .document(voucherId);
            documentReferenceForVoucher.set(voucherMap).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(getActivity(), "VOUCHER ADDED", Toast.LENGTH_SHORT).show();
                }
            });
        });

        return view;
    }
}