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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class UserFeedBack extends AppCompatActivity {


    EditText feedbackMovieName, feedbackBox, feedbackSatisfying;
    Button postFeedback;

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed_back);

        feedbackMovieName = findViewById(R.id.editText_feedback_movieName);
        feedbackSatisfying = findViewById(R.id.editTextSatisfying);
        feedbackBox = findViewById(R.id.feedbackBox);

        postFeedback = findViewById(R.id.postFeedbackButton);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        postFeedback.setOnClickListener(View -> {
            String id = UUID.randomUUID().toString();

            Map<String, Object> feedbackMap = new HashMap<>();
            feedbackMap.put("movieName", feedbackMovieName.getText().toString());
            feedbackMap.put("satisfying", feedbackSatisfying.getText().toString());
            feedbackMap.put("feedbackContent", feedbackBox.getText().toString());
            feedbackMap.put("id", id);

           DocumentReference documentReference = db.collection("Users").document(userId);
           documentReference.get().addOnCompleteListener(task -> {
              if (task.isSuccessful()){
                  DocumentSnapshot documentSnapshot = task.getResult();
                  User user = documentSnapshot.toObject(User.class);

                  Map<String, String> userMap = new HashMap<>();
                  userMap.put("fullName", Objects.requireNonNull(user).getFullName());
                  userMap.put("email", user.getEmail());
                  userMap.put("id", userId);

                  feedbackMap.put("user", userMap);

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