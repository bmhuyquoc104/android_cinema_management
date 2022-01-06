package com.example.android_cinema_management.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.R;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<User> userArrayList;

    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        ImageButton likeBtn, dislikeBtn;
        TextView userName, movieName, movieRating, reviewContent;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            likeBtn = itemView.findViewById(R.id.likeButton);
            dislikeBtn = itemView.findViewById(R.id.dislikeButton);
            userName = itemView.findViewById(R.id.userFullName);
            movieName = itemView.findViewById(R.id.displayMovieName);
            movieRating = itemView.findViewById(R.id.displayMovieRating);
            reviewContent = itemView.findViewById(R.id.review);
        }
    }

    public ReviewAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.review_item, parent, false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

//    @Override
//    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
//        User user = userArrayList.get(position);
////        holder.likeBtn
//    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
