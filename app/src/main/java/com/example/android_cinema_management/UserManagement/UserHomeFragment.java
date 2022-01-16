package com.example.android_cinema_management.UserManagement;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.android_cinema_management.Accounts;
import com.example.android_cinema_management.Adapter.ListOfFeedbackReplyAdapter;
import com.example.android_cinema_management.Adapter.VoucherAdapter;
import com.example.android_cinema_management.MainActivity;
import com.example.android_cinema_management.Model.ReplyFeedback;
import com.example.android_cinema_management.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;

import java.util.ArrayList;


public class UserHomeFragment extends Fragment {
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private ListOfFeedbackReplyAdapter listOfFeedbackReplyAdapter;
    //Declare Movie list
    private ArrayList<ReplyFeedback> replyFeedbackArrayList = new ArrayList<>();
    //Declare imageview
    ImageView profile, avatar, combo, transaction, feedback, review, points, notification;
    //Declare textview
    TextView welcome, type;
    //Declare logout
    Button logOut,buyTicket;
    // Declare string email
    public static String accountType;
    public static String gender;
    public static String totalPoint;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser mUser = firebaseAuth.getCurrentUser();
    String userId = mUser.getUid();
    public UserHomeFragment() {
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
        notification = view.findViewById(R.id.user_home_notification);
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
                        db.collection("Users").document(mUser.getUid()).update("status", "active");
                        SpannableString str2 = new SpannableString(docSnap.getString("fullName"));
                        gender = docSnap.getString("gender");
                        totalPoint = docSnap.getString("point");
                        System.out.println(totalPoint);
                        str2.setSpan(new ForegroundColorSpan(Color.rgb(222,22,25)), 0, str2.length(), 0);
                        builder.append(str2);
                        accountType = docSnap.getString("role");
                        type.setText("Account type: " + accountType);
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

        //Listen to onClick of transaction button
        transaction.setOnClickListener(View -> {
            Intent intent = new Intent(getActivity(), TransactionActivity.class);
            startActivity(intent);
        });

        //Function log out
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("Users").document(userId)
                        .update("status","inactive");
                //Declare log out message
                Spanned logoutMessage = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
                        .setHtml(
                                "<h3> You Have Successfully Logged Out. </p>" +
                                        "<p>It's our pleasure to serve you. Have a nice day! </p>" +
                                        "<h3> Thank you for using our service! </h3>"));
                FirebaseAuth.getInstance().signOut();
                Accounts fragment = new Accounts();

                VoucherAdapter.openSuccessfulDialog(R.raw.bye_success,logoutMessage,getContext());
                FragmentTransaction transaction =
                        fm.beginTransaction();
                transaction.replace(R.id.ma_container, fragment).commit();
                // Display dialog
                MainActivity.isLogin = false;

            }
        });

        /*
         *Function to switch to combo home page
         * */
        combo.setOnClickListener(View->{
            Intent intent = new Intent(getContext(),ComboActivity.class);
            startActivity(intent);
        });

        /*
        *Function to switch to voucher home page
        * */
        points.setOnClickListener(View ->{
            Intent intent = new Intent(getContext(),VoucherActivity.class);
            startActivity(intent);
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

        notification.setOnClickListener(View -> {
//            Intent intent = new Intent(getContext(), UserNotification.class);
//            startActivity(intent);
            openNotificationDialog(R.layout.activity_user_notification,db,replyFeedbackArrayList,mUser);
        });
        return view;
    }

    @SuppressLint("SetTextI18n")
    private void openNotificationDialog(int id, FirebaseFirestore db, ArrayList<ReplyFeedback>replyFeedbackArrayList,FirebaseUser user) {
        // Initialize new dialog
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Set content for dialog
        dialog.setContentView(id);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        // set the dialog to top
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        // Disable cancel by clicking randomly on the screen
//        dialog.setCancelable(false);

        dialog.show();
        RecyclerView recyclerView = dialog.findViewById(R.id.user_list_read_feedback_recycler_view);
        recyclerView.setHasFixedSize(true);



        UserNotification.getReplyFeedback(db, replyFeedbackArrayList,()->{
            System.out.println("REPLY FEEDBACK LIST: " + replyFeedbackArrayList);
            listOfFeedbackReplyAdapter = new ListOfFeedbackReplyAdapter(getContext(), replyFeedbackArrayList);
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(listOfFeedbackReplyAdapter);
        },user);



    }
}