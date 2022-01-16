package com.example.android_cinema_management.UserManagement.AdminManagment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.Model.Feedback;
import com.example.android_cinema_management.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReplyFeedbackActivity extends AppCompatActivity {

    TextView fullName, date, time, topic, email, feedbackContent;
    EditText replyContent;
    Button replyBtn;

    //Declare FirebaseFirestore FirebaseAuth FirebaseUser String
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Feedback feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_feedback);

        //Get content cinema's intent
        feedback = (Feedback) getIntent().getSerializableExtra("feedback");

        //binding EditText and Button to file xml activity_reply_feedback
        fullName = findViewById(R.id.admin_reply_feedback_fullName_tv);
        date = findViewById(R.id.admin_reply_feedback_date_sent_tv);
        time = findViewById(R.id.admin_reply_feedback_time_sent_tv);
        topic = findViewById(R.id.admin_reply_feedback_topic_tv);
        feedbackContent = findViewById(R.id.admin_reply_feedback_feedbackContent_tv);
        email = findViewById(R.id.admin_reply_feedback_email_sent_tv);
        replyContent = findViewById(R.id.admin_reply_feedback_reply_content_sent_et);
        replyBtn = findViewById(R.id.admin_reply_feedback_reply_bt);


        fullName.setText(feedback.getUser().get("fullName").toString());
        email.setText(feedback.getUser().get("email").toString());
        date.setText(feedback.getDate());
        time.setText(feedback.getTime());
        topic.setText(feedback.getTopic());
        feedbackContent.setText(feedback.getFeedbackContent());

        replyBtn.setOnClickListener(view -> {
            String replyFeedbackId = UUID.randomUUID().toString();
            Map<String, Object> replyFeedbackMap = new HashMap<>();
            replyFeedbackMap.put("replyFeedbackId",replyFeedbackId);
            replyFeedbackMap.put("userName", fullName.getText().toString());
            replyFeedbackMap.put("userEmail", email.getText().toString());
            replyFeedbackMap.put("date", date.getText().toString());
            replyFeedbackMap.put("time", time.getText().toString());
            replyFeedbackMap.put("topic", topic.getText().toString());
            replyFeedbackMap.put("feedbackContent", feedbackContent.getText().toString());
            replyFeedbackMap.put("replyFeedbackContent", replyContent.getText().toString());
            replyFeedbackMap.put("adminEmail", "universalCustomerService@gmail.com");
            replyFeedbackMap.put("image", "https://i.imgur.com/UE0nhcd.png");

            DocumentReference docRef = db.collection("replyToFeedback").document(replyFeedbackId);
            docRef.set(replyFeedbackMap).addOnCompleteListener(task -> {
               if (task.isSuccessful()){
                   Toast.makeText(this, "REPLIED TO FEEDBACK",Toast.LENGTH_SHORT).show();
               }
            });
        });
    }
}