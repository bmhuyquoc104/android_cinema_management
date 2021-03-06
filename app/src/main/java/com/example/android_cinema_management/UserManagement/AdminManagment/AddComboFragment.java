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

public class AddComboFragment extends Fragment {

    // Initialize EditText for comboName, description, price, imageURl, and Button for addbutton
    EditText comboName, description, price, imageURL;
    Button addButton;

    //Declare and initialize FirebaseFirestore
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public AddComboFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_combo, container, false);

        //Binding EditText and button variable with entity in xml file
        comboName = view.findViewById(R.id.admin_new_combo_name_et);
        description = view.findViewById(R.id.admin_new_combo_description_sent_et);
        price = view.findViewById(R.id.admin_new_combo_price_sent_et);
        imageURL = view.findViewById(R.id.admin_new_combo_image_sent_et);
        addButton = view.findViewById(R.id.admin_new_combo_add_bt);

        //Listen to addButton onClick
        addButton.setOnClickListener(View -> {
            //Create a random ID using UUID
            String comboId = UUID.randomUUID().toString();

            //add all field from above edit text into comboMap
            Map<String, Object> comboMap = new HashMap<>();
            comboMap.put("id", comboId);
            comboMap.put("name", comboName.getText().toString());
            comboMap.put("description", description.getText().toString());
            comboMap.put("price", price.getText().toString());
            comboMap.put("image", imageURL.getText().toString());

            //save comboMap into collection combo with id tha just has been created
            DocumentReference documentReferenceForCombo = db.collection("combo")
                    .document(comboId);
            documentReferenceForCombo.set(comboMap).addOnCompleteListener(task -> {
               if (task.isSuccessful()){
                   Toast.makeText(getContext(),"COMBO ADDED", Toast.LENGTH_SHORT).show();
               }
            });
        });

        return view;
    }
}
