package com.example.android_cinema_management.UserManagement;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.MainActivity;
import com.example.android_cinema_management.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class UserHomeFragmentActivity extends Fragment {
    //Declare imageview
    ImageView profile, avatar, combo, transaction, feedback, review, points;
    //Declare textview
    TextView welcome, type;
    //Declare logout
    Button logOut,buyTicket;
    // Declare string email
    String name;


    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    String userId = user.getUid();
    public UserHomeFragmentActivity() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Binding to XML layout
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);
        profile = view.findViewById(R.id.user_home_profile_iv);
        combo = view.findViewById(R.id.user_home_gift_iv);
        transaction = view.findViewById(R.id.user_home_transaction_history_iv);
        feedback = view.findViewById(R.id.user_home_feedback_iv);
        review = view.findViewById(R.id.user_home_review_iv);
        points = view.findViewById(R.id.user_home_points_iv);
        logOut = view.findViewById(R.id.user_home_logout_button);
        welcome = view.findViewById(R.id.user_home_name);
        type = view.findViewById(R.id.user_home_account_type);
        buyTicket = view.findViewById(R.id.user_home_buy_ticket_bt);
        // Receive the bundle from other fragments
        Bundle bundle = this.getArguments();
        System.out.println(bundle);
//        assert bundle != null;
//        email = bundle.getString("email");

        // Initialize fragment manager
        FragmentManager fm = getParentFragmentManager();


        //Create spannalbe String
        SpannableStringBuilder builder = new SpannableStringBuilder();

        // Text and color for string 1
        SpannableString str1= new SpannableString("Welcome: ");
        str1.setSpan(new ForegroundColorSpan(Color.rgb(161,161,161)), 0, str1.length(), 0);
        builder.append(str1);

        DocumentReference docRef = db.collection("Users").document(userId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot docSnap = task.getResult();
                    if (docSnap != null){
//                        name = docSnap.getString("fullName");
//                        System.out.println("IDDDDDDDDDDDDDDDDDDDDDDDDD: " + userId);
//                        System.out.println("fullNameeeeeeeeeeeeeeeeeee: " + name);
                        SpannableString str2 = new SpannableString(docSnap.getString("fullName"));
                        str2.setSpan(new ForegroundColorSpan(Color.rgb(222,22,25)), 0, str2.length(), 0);
                        builder.append(str2);
                        welcome.setText( builder, Button.BufferType.SPANNABLE);
                    }
                }
            }
        });

        // Text and color for string 2
//        SpannableString str2= new SpannableString(name);
//        str2.setSpan(new ForegroundColorSpan(Color.rgb(222,22,25)), 0, str2.length(), 0);
//        builder.append(str2);



        // Set text for textView


        /**
         * Function to switch to profile page
         *
         * */
        profile.setOnClickListener(View ->{
            Intent intent = new Intent(getContext(),UserProfile.class);
            // Start intent
            try {
               startActivity(intent);
            }
            // Exception if activity is not found
            catch (ActivityNotFoundException e){
                Toast.makeText(getContext(),"Oops!! Something wrong, Please try again!" ,Toast.LENGTH_LONG).show();
            }        });

        //Listen onClick of Review Button
        review.setOnClickListener(View ->{
            Intent intent = new Intent(getActivity(), UserReview.class);
            startActivity(intent);
        });

        //Listen onClick of FeedBack button
        feedback.setOnClickListener(View ->{
            Intent intent = new Intent(getActivity(), UserFeedBackActivity.class);
            startActivity(intent);
        });

        //Function log out
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("Users").document(userId)
                        .update("status","inactive");
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        /*
        *Function to add buy ticket
        * */
        buyTicket.setOnClickListener(View ->{
            Intent intent = new Intent(getContext(), BuyTicketActivity.class);
            // Start intent
            try {
                startActivity(intent);
            }
            // Exception if activity is not found
            catch (ActivityNotFoundException e){
                Toast.makeText(getContext(),"Oops!! Something wrong, Please try again!" ,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}