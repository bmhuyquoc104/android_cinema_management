package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


public class BuyTicketFragment4 extends Fragment {
    // Declare textview
    TextView phone,email,fullName;
    //Declare Button
    Button purchaseBtn;

    //Declare FirebaseFirestore FirebaseAuth FirebaseUser String
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser mUser = mAuth.getCurrentUser();

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
        purchaseBtn = view.findViewById(R.id.buy_by_movie_purchase_bt);

        //Getting all fill content from 3 previous BuyTicketFragment
        Bundle bundle = this.getArguments();
        assert bundle != null;
        String movie = bundle.getString("movie");
        String cinema = bundle.getString("cinema");
        String date = bundle.getString("date");
        String time = bundle.getString("time");
        String ticket = bundle.getString("ticket");
        String quantity = bundle.getString("quantity");
        String combo = bundle.getString("combo");
        String seat = bundle.getString("seat");
        String screen = bundle.getString("screen");
        String point = bundle.getString("point");
        String transactionId = UUID.randomUUID().toString();

        DocumentReference documentReference = db.collection("Users").document(mUser.getUid());
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot docSnap = task.getResult();
                if (docSnap != null) {
//                        userId = docSnap.getString("Id");
//                        userName = docSnap.getString("fullName");
                    fullName.setText(docSnap.getString("fullName"));
                    phone.setText(docSnap.getString("phone"));
                    email.setText(docSnap.getString("email"));
                }
            }
        });

        purchaseBtn.setOnClickListener(View -> {
            Map<String, Object> transactionMap = new HashMap<>();
            transactionMap.put("transactionId", transactionId);
            transactionMap.put("movie", movie);
            transactionMap.put("cinema", cinema);
            transactionMap.put("date", date);
            transactionMap.put("time", time);
            transactionMap.put("ticket", ticket);
            transactionMap.put("quantity", quantity);
            transactionMap.put("combo", combo);
            transactionMap.put("seat", seat);
            transactionMap.put("screen", screen);
            transactionMap.put("point", point);

            //Get current user login
            DocumentReference docRef = db.collection("Users").document(mUser.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot docSnap = task.getResult();
                        User user = docSnap.toObject(User.class);
                        Map<String, String> userMap = new HashMap<>();
                        userMap.put("fullName", Objects.requireNonNull(user).getFullName());
                        userMap.put("email", user.getEmail());
                        userMap.put("phone", user.getPhone());

                        //Then put userMap into reviewMap
                        transactionMap.put("user",userMap);

                        //Saving buy ticket information into transaction's collection
                        DocumentReference documentReferenceForReview = db.collection("transaction")
                                .document(transactionId);
                        documentReferenceForReview.set(transactionMap).addOnCompleteListener(taskInner -> {
                            if (taskInner.isSuccessful()) {
                                Toast.makeText(getContext(), "TRANSACTION ADDED!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });


        });
        return view;
    }
}