package com.example.android_cinema_management.UserManagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;

import com.example.android_cinema_management.Adapter.ListOfFeedbackReplyAdapter;
import com.example.android_cinema_management.Adapter.ReviewAdapter;
import com.example.android_cinema_management.Model.Combo;
import com.example.android_cinema_management.Model.ReplyFeedback;
import com.example.android_cinema_management.Model.Review;
import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class UserNotification extends AppCompatActivity {

    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private ListOfFeedbackReplyAdapter listOfFeedbackReplyAdapter;
    //Declare Movie list
    public static ArrayList<ReplyFeedback> replyFeedbackArrayList;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    String fullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_notification);

        replyFeedbackArrayList = new ArrayList<>();

        getReplyFeedback(db, replyFeedbackArrayList,()->{
            System.out.println("REPLY FEEDBACK LIST: " + replyFeedbackArrayList);
            recyclerView = findViewById(R.id.user_list_read_feedback_recycler_view);
            recyclerView.setHasFixedSize(true);
            listOfFeedbackReplyAdapter = new ListOfFeedbackReplyAdapter(UserNotification.this, replyFeedbackArrayList);
            layoutManager = new LinearLayoutManager(UserNotification.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(listOfFeedbackReplyAdapter);
        },user);





        //
    }

    public static void getReplyFeedback(FirebaseFirestore db, ArrayList<ReplyFeedback> replyFeedbackArrayList, Runnable callback, FirebaseUser thisUser) {
        replyFeedbackArrayList.clear();
        String email = thisUser.getEmail();
        db.collection("replyToFeedback").whereEqualTo("userEmail",email).get()
                .addOnCompleteListener(task -> {
                    // Check if task is successfully or not
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot snapshot : task.getResult()) {
                            // Create an instance and load data to voucher
                            ReplyFeedback replyFeedback = new ReplyFeedback(
                                    snapshot.getString("replyFeedbackId"),
                                    snapshot.getString("date"),
                                    snapshot.getString("time"),
                                    snapshot.getString("adminEmail"),
                                    snapshot.getString("feedbackContent"),
                                    snapshot.getString("replyFeedbackContent"),
                                    snapshot.getString("userName"),
                                    snapshot.getString("topic"),
                                    snapshot.getString("userEmail"),
                                    snapshot.getString("image")
                                    // Parse the database type string to int
                            );
                            // add each instance to the list
                            replyFeedbackArrayList.add(replyFeedback);
                            System.out.println("huy ne" + replyFeedbackArrayList);

                        }
                        // After getting the data successfully, run the run back
                        callback.run();
                    }
                });
    }
}