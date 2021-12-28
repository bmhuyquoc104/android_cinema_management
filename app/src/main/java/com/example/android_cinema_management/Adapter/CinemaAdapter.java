package com.example.android_cinema_management.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.R;

import java.util.ArrayList;

public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.MyViewHolder>{
    // Initialize array list for movie
    private ArrayList<Cinema> movieList;
    // Initialize context
    private Context context;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cinemaImage;
        TextView cinemaName, cinemaLocationName,cinemaAddress,cinemaContact;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cinemaImage = itemView.findViewById(R.id.cinemaImage);
            cinemaName = itemView.findViewById(R.id.cinemaName);
            cinemaLocationName = itemView.findViewById(R.id.cinemaLocationName);
            cinemaAddress = itemView.findViewById(R.id.cinemaAddress);
            cinemaContact = itemView.findViewById(R.id.cinemaPhoneContact);
        }
    }
}
