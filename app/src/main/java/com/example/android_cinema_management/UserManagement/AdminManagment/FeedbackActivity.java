package com.example.android_cinema_management.UserManagement.AdminManagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.android_cinema_management.Adapter.FeedbackAdapter;
import com.example.android_cinema_management.Adapter.FeedbackAdminAdapter;
import com.example.android_cinema_management.Model.Feedback;
import com.example.android_cinema_management.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class FeedbackActivity extends AppCompatActivity {

    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private FeedbackAdminAdapter feedbackAdminAdapter;
    //Declare Movie list
    public static ArrayList<Feedback> feedbackArrayList;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackArrayList = new ArrayList<>();
        //call function get reviews data
        getFeedbacks(db, feedbackArrayList, () -> {
            System.out.println("FEEDBACK LIST: " + feedbackArrayList);

            // Set fixed size for recycler view
            recyclerView = findViewById(R.id.admin_list_feedback_admin_recycler_view);
            recyclerView.setHasFixedSize(true);
            feedbackAdminAdapter = new FeedbackAdminAdapter(this, feedbackArrayList);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            // Specify an adapter
            recyclerView.setAdapter(feedbackAdminAdapter);
        });
    }

    //Function get reviews data
    private void getFeedbacks(FirebaseFirestore db, ArrayList<Feedback> feedbackArrayList, Runnable callback) {
        feedbackArrayList.clear();
        db.collection("feedback").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
                    count++;
                    Feedback dataContainer = doc.toObject(Feedback.class);
                    feedbackArrayList.add(dataContainer);
                    if (count == Objects.requireNonNull(task.getResult()).size()) {
                        callback.run();
                    }
                }
            }
        });
    }
}