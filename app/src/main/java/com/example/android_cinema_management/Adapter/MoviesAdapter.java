package com.example.android_cinema_management.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.R;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private ArrayList<Movie> movieList;
    private Context context;

    public MoviesAdapter(ArrayList<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_row_of_movies,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MyViewHolder holder, int position) {
        holder.vietnameseTitle.setText(movieList.get(position).getVietnameseTitle());
        holder.englishTitle.setText(movieList.get(position).getEnglishTitle());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView vietnameseTitle, englishTitle;
        ImageView movieImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            vietnameseTitle = itemView.findViewById(R.id.vietnameseTitle);
            englishTitle = itemView.findViewById(R.id.englishTitle);
            movieImage = itemView.findViewById(R.id.movie1);
        }
    }
}
