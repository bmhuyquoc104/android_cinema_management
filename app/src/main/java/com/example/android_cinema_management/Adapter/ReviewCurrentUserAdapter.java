package com.example.android_cinema_management.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Review;
import com.example.android_cinema_management.R;

import java.util.ArrayList;

public class ReviewCurrentUserAdapter extends RecyclerView.Adapter<ReviewCurrentUserAdapter.MyViewHolder> {
    //Initialize context
    private Context context;

    //Initialize ArrayList for review
    private ArrayList<Review> reviewArrayList;

    public ReviewCurrentUserAdapter(Context context, ArrayList<Review> reviewArrayList) {
        this.context = context;
        this.reviewArrayList = reviewArrayList;
    }

    @NonNull
    @Override
    public ReviewCurrentUserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_row_of_curernt_user_review, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ReviewCurrentUserAdapter.MyViewHolder holder, int position) {
        holder.movieName.setText("Movie: " + reviewArrayList.get(position).getMovieName());
        holder.movieRating.setText("Rate: "+reviewArrayList.get(position).getRateMovie());
        holder.reviewContent.setText("Review Content: " + reviewArrayList.get(position).getReviewContent());
        holder.reviewDate.setText("DatePosted: "+reviewArrayList.get(position).getDate() + "  " +reviewArrayList.get(position).getTime());
        holder .likeBtn.setText("Total like: " + reviewArrayList.get(position).getLike());
        holder.dislikeBtn.setText("Total dislike: "+reviewArrayList.get(position).getDislike());
    }

    @Override
    public int getItemCount() {
        return reviewArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView likeBtn, dislikeBtn;
        TextView movieName, movieRating, reviewContent, reviewDate, reviewTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            likeBtn = itemView.findViewById(R.id.list_of_current_user_like_tv);
            dislikeBtn = itemView.findViewById(R.id.list_of_current_user_review_dislike_tv2);
            movieName = itemView.findViewById(R.id.list_of_current_user_review_movie_tv);
            movieRating = itemView.findViewById(R.id.list_of_current_user_review_rate_tv);
            reviewContent = itemView.findViewById(R.id.list_of_current_user_review_content_tv2);
            reviewDate = itemView.findViewById(R.id.list_of_current_user_review_date_tv);

        }
    }
}
