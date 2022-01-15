package com.example.android_cinema_management.UserManagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.android_cinema_management.Adapter.ListOfFeedbackReplyAdapter;
import com.example.android_cinema_management.Adapter.ReviewAdapter;
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

        getReplyFeedback(db, replyFeedbackArrayList);

        System.out.println("REPLY FEEDBACK LIST: " + replyFeedbackArrayList);
        recyclerView = findViewById(R.id.user_list_read_feedback_recycler_view);
        recyclerView.setHasFixedSize(true);
        listOfFeedbackReplyAdapter = new ListOfFeedbackReplyAdapter(UserNotification.this, replyFeedbackArrayList);
        layoutManager = new LinearLayoutManager(UserNotification.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listOfFeedbackReplyAdapter);



        //
    }

    private void getReplyFeedback(FirebaseFirestore db, ArrayList<ReplyFeedback> replyFeedbackArrayList){
        replyFeedbackArrayList.clear();
        DocumentReference docRef = db.collection("Users").document(user.getUid());
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                DocumentSnapshot docSnap = task.getResult();
                if (docSnap != null){
                    fullName = docSnap.getString("fullName");
                    System.out.println("FullNameeeeeee " + fullName);
                    DocumentReference documentReference = db.collection("replyToFeedback").document(docSnap.getString("fullName"));
                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()){
                                int count = 0;
                                DocumentSnapshot documentSnapshot = task.getResult();
                                while (documentSnapshot.exists()){
                                    count++;
                                    ReplyFeedback dataContainer = documentSnapshot.toObject(ReplyFeedback.class);
                                    replyFeedbackArrayList.add(dataContainer);
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}