package com.example.android_cinema_management.UserManagement;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import com.example.android_cinema_management.SwipeToDeleteCallBack;
import com.example.android_cinema_management.UserManagement.AdminManagment.UpdateAndDeleteCombo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;

import java.util.ArrayList;


public class UserHomeFragment extends Fragment {
    //Declare recycler view
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private ListOfFeedbackReplyAdapter listOfFeedbackReplyAdapter;
    //Declare reply feedback list
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

    // Declare and initialize firebase services
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser mUser = firebaseAuth.getCurrentUser();
    String userId = mUser.getUid();

    // Initialize the layout
    ConstraintLayout constraintLayout;
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
        constraintLayout = view.findViewById(R.id.one_row_of_notification_clo);
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


        /*
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

        // Show drop down notification dialog
        notification.setOnClickListener(View -> {
            openNotificationDialog(R.layout.activity_user_notification,db,replyFeedbackArrayList,mUser);
        });
        return view;
    }


    /*
    * Function to open notification dialog
    * */
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
        // enable cancel by clicking randomly on the screen
        dialog.setCancelable(true);
        // Show the dialog
        dialog.show();

        //Binding the value to dialog layout
        RecyclerView recyclerView = dialog.findViewById(R.id.user_list_read_feedback_recycler_view);
        ConstraintLayout constraintLayout = dialog.findViewById(R.id.one_row_of_notification_clo);
        recyclerView.setHasFixedSize(true);


        /*
        * Function to get all reply feedback from the database and display on the recycler view
        * */
        UserNotification.getReplyFeedback(db, replyFeedbackArrayList,()->{
            listOfFeedbackReplyAdapter = new ListOfFeedbackReplyAdapter(getContext(), replyFeedbackArrayList);
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            Spanned errorMessage = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
                    .setHtml(
                            "<h3> You don't have any notifications. </p>" +
                                    "<p>Please try later.</p>" +
                                    "<h3> Thank you for using our service! </h3>"));

            if (replyFeedbackArrayList.isEmpty()){
                VoucherAdapter.openSuccessfulDialog(R.raw.no_notification,errorMessage,getContext());
                dialog.dismiss();
            }
            recyclerView.setAdapter(listOfFeedbackReplyAdapter);

            // Function that allow the user to choose delete option when swipe the item
            SwipeToDeleteCallBack swipeToDeleteCallback = new SwipeToDeleteCallBack(getContext()) {
                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                    // Binding the position to adapter
                     int position = viewHolder.getBindingAdapterPosition();
                    System.out.println(position);
                    String positionId = listOfFeedbackReplyAdapter.getReplyFeedbackArrayList().get(position).getReplyId();
                     // Initialize the object for replyFeedback
                     ReplyFeedback item = listOfFeedbackReplyAdapter.getReplyFeedbackArrayList().get(position);
                     /*
                     * Function to remove the item from the recycler view
                     * */
                    listOfFeedbackReplyAdapter.removeItem(position);
                    // Initialize snack bar to show undo message
                    Snackbar snackbar = Snackbar
                            .make(constraintLayout, "This notification was deleted.", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        // Restore the delete item if the user accidently delete the wrong one
                        @Override
                        public void onClick(View view) {
                            listOfFeedbackReplyAdapter.restoreItem(item, position);
                            recyclerView.scrollToPosition(position);
                        }
                    });
                    // Customize the snackbar
                    snackbar.setDuration(1000);
                    snackbar.setTextColor(getResources().getColor(R.color.colorPrimary4));
                    snackbar.setBackgroundTint(getResources().getColor(R.color.colorPrimary3));
                    snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary2));
                    snackbar.show();
                    /*
                    * Function to completely delete the notification from the database after the user not choosing the undo option
                    * */
                    snackbar.addCallback(new Snackbar.Callback(){

                        @Override
                        public void onDismissed(Snackbar snackbar, int event) {
                            if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT) {
                                listOfFeedbackReplyAdapter.removeFromFireStore(db,positionId);
                            }
                        }
                    });
                }
            };

            // Initialize itemTouchHelper
            ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
            itemTouchhelper.attachToRecyclerView(recyclerView);

        },user);
    }
}