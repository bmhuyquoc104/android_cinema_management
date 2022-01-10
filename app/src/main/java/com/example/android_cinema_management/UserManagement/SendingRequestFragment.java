package com.example.android_cinema_management.UserManagement;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


public class SendingRequestFragment extends Fragment {

    //Declare EditText, textview, imageview  and Button
    EditText feedbackBox, feedbackTopic;
    Button postFeedback;
    TextInputLayout topic;
    ImageView close;
    TextView date,email;
    //Declare FirebasFirestore FirebaseAuth FirebaseUser String SimpleDateFormat
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String userId;
    String currentDate;
    String currentEmail;
    SimpleDateFormat dateFormat;
    String[] topicArray = {"Buying Ticket", "Transaction","Discount","Account","Others"};
    String topicChosen;
    //Declare array adapter
    ArrayAdapter<String> adapterItems;
    // Declare auto complete text view
    AutoCompleteTextView autoCompleteTextView;

    public SendingRequestFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sending_request, container, false);


        //Initialize editText for feedbackTopic feedbackBox
//        feedbackTopic = findViewById(R.id.editTextTopic);
        feedbackBox = view.findViewById(R.id.user_feedback_message_sent_et);
        date = view.findViewById(R.id.user_feedback_date_sent_tv);
        email = view.findViewById(R.id.user_feedback_email_tv);
        topic = view.findViewById(R.id.user_feedback_topic_text_layout);
        close = view.findViewById(R.id.user_feedback_close_iv);
        //Initialize button postFeedback
        postFeedback = view.findViewById(R.id.user_feedback_send_bt);

        //Initialize mAuth and db
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        //Get current user login
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        //Get current user Id
        userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        currentEmail = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();

        //Binding xml value and set the dropdown for topic
        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, topicArray);
        autoCompleteTextView = view.findViewById(R.id.user_feedback_topic_auto_complete_text);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                topicChosen = parent.getItemAtPosition(position).toString();
            }
        });

        //Set current email
        email.setText(currentEmail);

        //Set current date
        Date time = Calendar.getInstance().getTime();
        //Format the date for later comparison
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = df.format(time);
        date.setText(currentDate);


        //Listen to postFeedback button event
        postFeedback.setOnClickListener(View -> {

            //Generate an Id for a feedback
            String id = UUID.randomUUID().toString();

            //Getting all content from all above EditTexts into a Map
            Map<String, Object> feedbackMap = new HashMap<>();
//            feedbackMap.put("topic", feedbackTopic.getText().toString());
            feedbackMap.put("topic", topic.getEditText().getText().toString());
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
                            Toast.makeText(getContext(), "FEEDBACK ADDED!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        });
        return view ;
    }
}