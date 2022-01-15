package com.example.android_cinema_management.UserManagement;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.HomeManagement.HomeFragment1;
import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class AddTransactionFragment extends Fragment {

    //Declare EditText and Button
    EditText transactionQuantity, transactionType;
    Button postTransactionButton;
    TextView authorName,date,time, transactionPoint;
    //Declare FirebaseFirestore FirebaseAuth FirebaseUser String
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser mUser = mAuth.getCurrentUser();

    public AddTransactionFragment() {
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_transaction, container, false);

        transactionPoint = view.findViewById(R.id.user_add_transaction_point_tv);
        transactionQuantity = view.findViewById(R.id.user_add_transaction_quantity_sent_et);
        postTransactionButton = view.findViewById(R.id.add_transaction_add_bt);
        authorName = view.findViewById(R.id.add_transaction_authorName_tv);
        date = view.findViewById(R.id.user_add_transaction_date_sent_tv);
        time = view.findViewById(R.id.user_add_transaction_time_sent_tv);
        transactionType = view.findViewById(R.id.user_add_transaction_ticket_type_sent_et);

        //Get current user Id
        DocumentReference docRef = db.collection("Users").document(mUser.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot docSnap = task.getResult();
                    if (docSnap != null){
                        //Set author name
                        authorName.setText(docSnap.getString("fullName"));
//                        System.out.println("IDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD " + userId);
//                        System.out.println("FullNameeeeeeeeeeeeeeeeeeeeeeeeee " + userName);
                    }
                }
            }
        });

        //Set date and time for date and time textview
        Date dateTime = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df2 = new SimpleDateFormat("kk:mm");
        String currentTime = df2.format(dateTime);
        String currentDate = df.format(dateTime);
        date.setText("Date: " +currentDate);
        time.setText("Time: "+currentTime);

        postTransactionButton.setOnClickListener(View -> {
            //Generate random Id
            String Id = UUID.randomUUID().toString();

            Map<String, Object> transactionMap = new HashMap<>();
            transactionMap.put("transactionId", Id);
            transactionMap.put("ticketType", transactionType.getText().toString());
            transactionMap.put("point", Integer.parseInt(transactionPoint.getText().toString()));
            transactionMap.put("quantity", Integer.parseInt(transactionQuantity.getText().toString()));
            transactionMap.put("date", currentDate);
            transactionMap.put("time", currentTime);

            //Getting user's full name and user's email of current login user into userMap
            DocumentReference documentReference = db.collection("Users").document(mUser.getUid());
            documentReference.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    User user = documentSnapshot.toObject(User.class);

                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("fullName", Objects.requireNonNull(user).getFullName());
                    userMap.put("email", user.getEmail());
                    userMap.put("id", mUser.getUid());

                    //Then put userMap into reviewMap
                    transactionMap.put("user", userMap);

                    //Saving reviewMap into Firestore in reviews collection
                    DocumentReference documentReferenceForReview = db.collection("transaction")
                            .document(Id);
                    documentReferenceForReview.set(transactionMap).addOnCompleteListener(taskInner -> {
                        if (taskInner.isSuccessful()) {
                            Toast.makeText(getContext(), "TRANSACTION ADDED!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });


        });


        return view;
    }
}