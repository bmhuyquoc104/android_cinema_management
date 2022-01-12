package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_cinema_management.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class BuyTicketFragment4 extends Fragment {
    // Declare textview
    TextView phone,email,fullName;
    //Declare FirebaseFirestore FirebaseAuth FirebaseUser String
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    public BuyTicketFragment4() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buy_ticket4, container, false);

        //Binding to XML value
        phone = view.findViewById(R.id.buy_ticket_phone_tv);
        email = view.findViewById(R.id.buy_ticket_email_tv);
        fullName = view.findViewById(R.id.buy_ticket_name_tv);
        //Initialize mAth db
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        //Get current user login
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        DocumentReference docRef = db.collection("Users").document(mUser.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot docSnap = task.getResult();
                    if (docSnap != null){
//                        userId = docSnap.getString("Id");
//                        userName = docSnap.getString("fullName");
                        fullName.setText(docSnap.getString("fullName"));
                        phone.setText(docSnap.getString("phone"));
                        email.setText(docSnap.getString("email"));
                    }
                }
            }
        });


        return view;
    }
}