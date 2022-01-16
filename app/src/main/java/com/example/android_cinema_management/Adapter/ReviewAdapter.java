package com.example.android_cinema_management.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Review;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.VoucherActivity;
import com.example.android_cinema_management.database.VoucherDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    //Initialize context
    private Context context;

    //Initialize ArrayList for review
    private ArrayList<Review> reviewArrayList;
    private boolean isPressed = false;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String currentDislike,currentLike;
    public ReviewAdapter(Context context, ArrayList<Review> reviewArrayList) {
        this.context = context;
        this.reviewArrayList = reviewArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_row_of_review, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.movieName.setText("Movie: " + reviewArrayList.get(position).getMovieName());
        holder.movieRating.setText("Rate: "+reviewArrayList.get(position).getRateMovie());
        holder.reviewContent.setText("Review Content: " + reviewArrayList.get(position).getReviewContent());
        holder.reviewDate.setText("Date: "+reviewArrayList.get(position).getDate());
        holder.reviewTime.setText("Time: "+reviewArrayList.get(position).getTime());
        holder.likeBtn.setText(reviewArrayList.get(position).getLike());
        holder.dislikeBtn.setText(reviewArrayList.get(position).getDislike());


        holder.likeBtn.setOnClickListener(View ->{
            CollectionReference reviewRef = db.collection("reviews");
            reviewRef.whereEqualTo("reviewId",reviewArrayList.get(position).getReviewId())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (QueryDocumentSnapshot snapShot: task.getResult()) {
                                    currentLike = snapShot.getString("like");
                                    assert currentLike != null;
                                    if (!isPressed) {
                                        holder.likeBtn.setText(Integer.toString(Integer.parseInt(currentLike) + 1));
                                        db.collection("reviews").document(reviewArrayList.get(position).getReviewId())
                                                .update("like", Integer.toString(Integer.parseInt(currentLike) + 1));
                                        holder.likeBtn.getCompoundDrawables()[0].setTint(Color.RED);
                                        isPressed = true;
                                    }
                                    else{
                                        holder.likeBtn.setText(Integer.toString(Integer.parseInt(currentLike) - 1));
                                        db.collection("reviews").document(reviewArrayList.get(position).getReviewId())
                                                .update("like", Integer.toString(Integer.parseInt(currentLike) - 1));
                                        holder.likeBtn.getCompoundDrawables()[0].setTint(Color.WHITE);
                                        isPressed = false;
                                    }
                                }

                            }else{
                                System.out.println("Error getting documents: " + task.getException());
                            }
                        }
                    });

        });


        /*
        * Function to update dislike value to the firebase every time the user press the textview
        * */
        holder.dislikeBtn.setOnClickListener(View ->{
            CollectionReference reviewRef = db.collection("reviews");
            reviewRef.whereEqualTo("reviewId",reviewArrayList.get(position).getReviewId())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (QueryDocumentSnapshot snapShot: task.getResult()){
                                    currentDislike = snapShot.getString("dislike");
                                    assert currentDislike != null;

                                    if (!isPressed) {
                                        holder.likeBtn.setText(Integer.toString(Integer.parseInt(currentDislike) + 1));
                                        db.collection("reviews").document(reviewArrayList.get(position).getReviewId())
                                                .update("dislike", Integer.toString(Integer.parseInt(currentDislike) + 1));
                                        holder.dislikeBtn.getCompoundDrawables()[0].setTint(Color.RED);
                                        isPressed = true;
                                    }
                                    else{
                                        holder.dislikeBtn.setText(Integer.toString(Integer.parseInt(currentDislike)-1));
                                        db.collection("reviews").document(reviewArrayList.get(position).getReviewId())
                                                .update("dislike", Integer.toString(Integer.parseInt(currentDislike)-1));
                                        holder.dislikeBtn.getCompoundDrawables()[0].setTint(Color.RED);
                                        isPressed = false;
                                    }

                                }

                            }else{
                                System.out.println("Error getting documents: " + task.getException());
                            }
                        }
                    });

        });

    }

    @Override
    public int getItemCount() {
        return reviewArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView likeBtn, dislikeBtn;
        TextView movieName, movieRating, reviewContent, reviewDate, reviewTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            likeBtn = itemView.findViewById(R.id.list_of_review_like_tv3);
            dislikeBtn = itemView.findViewById(R.id.list_of_review_dislike_tv2);

            movieName = itemView.findViewById(R.id.list_of_review_movie_tv);
            movieRating = itemView.findViewById(R.id.list_of_review_rate_tv);
            reviewContent = itemView.findViewById(R.id.list_of_review_content_tv);
            reviewDate = itemView.findViewById(R.id.list_of_review_date_tv);
            reviewTime = itemView.findViewById(R.id.list_of_review_time_tv);

            likeBtn.setOnClickListener(view -> {

            });

//            dislikeBtn.setOnClickListener(view -> {
//                countDislike++;
//                Review review = reviewArrayList.get(getAbsoluteAdapterPosition());
//                db.collection("reviews").document(review.getReviewId())
//                        .update("dislike", countDislike);
//            });
        }
    }
}
