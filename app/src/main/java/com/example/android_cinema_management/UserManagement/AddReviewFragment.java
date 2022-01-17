package com.example.android_cinema_management.UserManagement;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.Adapter.VoucherAdapter;
import com.example.android_cinema_management.HomeManagement.HomeFragment1;
import com.example.android_cinema_management.Model.Review;
import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.database.ReviewDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


public class AddReviewFragment extends Fragment {
    //Declare EditText and Button
    EditText  reviewBox, ratingMovie;
    Button postReviewButton;
    TextView authorName,date,time;
    //Declare FirebaseFirestore FirebaseAuth FirebaseUser String
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String userId,userName;

    //Declare text input layout
    TextInputLayout movie;
    //Declare review list
    ArrayList<Review> reviewArrayList = new ArrayList<>();
    //Declare movie array options
    ArrayList<String> moviesArray = new ArrayList<>();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = firebaseAuth.getCurrentUser();


    String movieChosen;
    //Declare array adapter
    ArrayAdapter<String> adapterItems;
    // Declare auto complete text view
    AutoCompleteTextView autoCompleteTextView;

    public AddReviewFragment() {
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_review, container, false);
        //Initialize EditText for reviewBox reviewMovieName
        reviewBox = view.findViewById(R.id.user_add_review_content_sent_et);
        ratingMovie = view.findViewById(R.id.user_add_review_rate_tv);
        authorName = view.findViewById(R.id.user_add_review_authorName_tv);
        date = view.findViewById(R.id.user_add_review_date_sent_tv);
        time = view.findViewById(R.id.user_add_review_time_sent_tv);
        movie = view.findViewById(R.id.user_add_review_movie_text_layout);
        //Initialize postReviewButton
        postReviewButton = view.findViewById(R.id.user_add_review_add_bt);

        //Initialize mAth db
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        //Get current user login
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        //Get current user Id
//        userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
//        userName = Objects.requireNonNull(mAuth.getCurrentUser()).getDisplayName();

        //Declare success message
        Spanned successMessage = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
                .setHtml(
                        "<h3> You Purchase Have Successfully Confirmed. </p>" +
                                "<p>Please come to the chosen cinema with your <strong > UniCard and ID </strong> to make an payment and receive " +
                                "the ticket.</p>" +
                                "<h3> Thank you for using our service! </h3>"));

        DocumentReference docRef = db.collection("Users").document(mUser.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot docSnap = task.getResult();
                    if (docSnap != null){
//                        userId = docSnap.getString("Id");
//                        userName = docSnap.getString("fullName");
                        authorName.setText(docSnap.getString("fullName"));
//                        System.out.println("IDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD " + userId);
//                        System.out.println("FullNameeeeeeeeeeeeeeeeeeeeeeeeee " + userName);
                    }
                }
            }
        });


        //Set date and time for date and time textview
        Date dateTime = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df2 = new SimpleDateFormat("kk:mm");
        String currentTime = df2.format(dateTime);
        String currentDate = df.format(dateTime);
        date.setText("Date: " +currentDate);
        time.setText("Time: "+currentTime);


        //Set author name
//        authorName.setText("Author name: "+ userName);

        //Binding xml value and set the dropdown for movie
        moviesArray = HomeFragment1.movieTitleList;
        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, moviesArray);
        autoCompleteTextView = view.findViewById(R.id.user_add_review_movie_auto_complete_text);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                movieChosen = parent.getItemAtPosition(position).toString();
            }
        });




        //Listen onClick of postReviewButton
        postReviewButton.setOnClickListener(View -> {
            //Generate Id for a review post
            String randomId = UUID.randomUUID().toString();

            //Getting all content from the above EditText into reviewMap
            Map<String, Object> reviewMap = new HashMap<>();
            reviewMap.put("reviewId", randomId);
            reviewMap.put("movieName", movie.getEditText().getText().toString());
            reviewMap.put("rateMovie", ratingMovie.getText().toString());
            reviewMap.put("reviewContent", reviewBox.getText().toString());
            reviewMap.put("like", "0");
            reviewMap.put("time", currentTime);
            reviewMap.put("date", currentDate);
            reviewMap.put("dislike", "0");

            //Getting user's full name and user's email of current login user into userMap
            DocumentReference documentReference = db.collection("Users").document(mUser.getUid());
            documentReference.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    User user = documentSnapshot.toObject(User.class);

                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("fullName", Objects.requireNonNull(user).getFullName());
                    userMap.put("email", user.getEmail());
                    userMap.put("avatar", user.getAvatar());
                    userMap.put("id", mUser.getUid());

                    //Then put userMap into reviewMap
                    reviewMap.put("user", userMap);

                    //Saving reviewMap into Firestore in reviews collection
                    DocumentReference documentReferenceForReview = db.collection("reviews")
                            .document(randomId);
                    documentReferenceForReview.set(reviewMap).addOnCompleteListener(taskInner -> {
                        if (taskInner.isSuccessful()) {
                            VoucherAdapter.openSuccessfulDialog(R.raw.review_success,successMessage,getContext());
                            ReviewDatabase.getReviewsByCurrentUser(db,reviewArrayList,()->{},currentUser);
                            ReviewDatabase.getAllReviews(db,reviewArrayList,() ->{});
                        }
                    });
                }
            });
        });
        return view;
    }
}