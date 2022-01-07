package com.example.android_cinema_management.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Review;
import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.R;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    //Initialize context
    private Context context;

    //Initialize ArrayList for review
    private ArrayList<Review> reviewArrayList;

    public ReviewAdapter(Context context, ArrayList<Review> reviewArrayList) {
        this.context = context;
        this.reviewArrayList = reviewArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.userName.setText(reviewArrayList.get(position).getUser().get("fullName").toString());
        holder.movieName.setText(reviewArrayList.get(position).getMovieName());
        holder.movieRating.setText(reviewArrayList.get(position).getRateMovie());
        holder.reviewContent.setText(reviewArrayList.get(position).getReviewContent());
    }

    @Override
    public int getItemCount() {
        return reviewArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageButton likeBtn, dislikeBtn;
        TextView userName, movieName, movieRating, reviewContent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            likeBtn = itemView.findViewById(R.id.likeButton);
            dislikeBtn = itemView.findViewById(R.id.dislikeButton);
            userName = itemView.findViewById(R.id.userFullName);
            movieName = itemView.findViewById(R.id.displayMovieName);
            movieRating = itemView.findViewById(R.id.displayMovieRating);
            reviewContent = itemView.findViewById(R.id.review);
        }
    }
}
