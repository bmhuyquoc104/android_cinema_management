package com.example.android_cinema_management.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.MovieManagement.MovieInfoTabLayout;
import com.example.android_cinema_management.MovieManagement.MovieInformation;
import com.example.android_cinema_management.MovieManagement.MovieShowTimeFragment1;
import com.example.android_cinema_management.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    // Initialize array list for movie
    private ArrayList<Movie> movieList;
    // Initialize context
    private Context context;

    // Constructor
    public MoviesAdapter(ArrayList<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Attach with one_row_of_movies lay out to render one row of item
        View view = LayoutInflater.from(context).inflate(R.layout.one_row_of_movies,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MyViewHolder holder, int position) {
        // Load image to the holder using Picasso library
        Picasso.get().load(movieList.get(position).getUrlImage()).into(holder.movieImage);
        // Load vietnameseTitle to holder
        holder.vietnameseTitle.setText(movieList.get(position).getVietnameseTitle());
        // Load englishTitle to holder
        holder.englishTitle.setText(movieList.get(position).getEnglishTitle());
        holder.itemView.setOnClickListener(view ->{
            //Send intent to movie information activity
            Intent intent = new Intent(context, MovieInfoTabLayout.class);
            //Use send method
            intent.setAction(Intent.ACTION_SEND);
//            // Set text type
            intent.setType("plain/text");
//            // Send message contain title, image
            intent.putExtra("englishTitle", movieList.get(position).getEnglishTitle());
            intent.putExtra("vietnameseTitle", movieList.get(position).getVietnameseTitle());
            intent.putExtra("imageUrl", movieList.get(position).getUrlImage());
            intent.putExtra("movieDetailUrl",movieList.get(position).getMovieDetailUrl());
            // Start intent
            try {
                context.startActivity(intent);
            }
            // Exception if activity is not found
            catch (ActivityNotFoundException e){
                Toast.makeText(context,"Oops!! Something wrong, Please try again!" ,Toast.LENGTH_LONG).show();
            }


        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // Declare textview and imageview to hold attribute
        TextView vietnameseTitle, englishTitle;
        ImageView movieImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //Binding to layout
            vietnameseTitle = itemView.findViewById(R.id.vietnameseTitle);
            englishTitle = itemView.findViewById(R.id.englishTitle);
            movieImage = itemView.findViewById(R.id.movie1);
        }
    }


}
