package com.example.android_cinema_management.UserManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class UserFeedBack extends AppCompatActivity {

    //Declare EditText and Button
    EditText feedbackBox, feedbackTopic;
    Button postFeedback;

    //Declare FirebasFirestore FirebaseAuth FirebaseUser String SimpleDateFormat
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String userId;
    String currentDate;
    SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed_back);

        //Initialize editText for feedbackTopic feedbackBox
        feedbackTopic = findViewById(R.id.editTextTopic);
        feedbackBox = findViewById(R.id.feedbackBox);

        //Initialize button postFeedback
        postFeedback = findViewById(R.id.postFeedbackButton);

        //Initialize mAuth and db
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        //Get current user login
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        //Get current user Id
        userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        //Listen to postFeedback button event
        postFeedback.setOnClickListener(View -> {

            //Get current date and time
            dateFormat = new SimpleDateFormat("dd.MM.yyyy G 'at' HH:mm:ss z");
            currentDate = dateFormat.format(new Date());

            //Generate an Id for a feedback
            String id = UUID.randomUUID().toString();

            //Getting all content from all above EditTexts into a Map
            Map<String, Object> feedbackMap = new HashMap<>();
            feedbackMap.put("topic", feedbackTopic.getText().toString());
            feedbackMap.put("feedbackContent", feedbackBox.getText().toString());
            feedbackMap.put("date", currentDate);
            feedbackMap.put("id", id);

            //Getting user's full name and user's email from Firestore system into a Map
           DocumentReference documentReference = db.collection("Users").document(userId);
           documentReference.get().addOnCompleteListener(task -> {
              if (task.isSuccessful()){
                  DocumentSnapshot documentSnapshot = task.getResult();
                  User user = documentSnapshot.toObject(User.class);

                  Map<String, String> userMap = new HashMap<>();
                  userMap.put("fullName", Objects.requireNonNull(user).getFullName());
                  userMap.put("email", user.getEmail());

                  //Put userMap into feedBackMap
                  feedbackMap.put("user", userMap);

                  //Save feedbackMap into Firestore inside collection name feedback
                  DocumentReference documentReferenceForFeedback = db.collection("feedback")
                          .document(id);
                  documentReferenceForFeedback.set(feedbackMap).addOnCompleteListener(taskInner -> {
                      if (taskInner.isSuccessful()) {
                          Toast.makeText(this, "FEEDBACK ADDED!", Toast.LENGTH_SHORT).show();
                      }
                  });
              }
           });
        });
    }
}