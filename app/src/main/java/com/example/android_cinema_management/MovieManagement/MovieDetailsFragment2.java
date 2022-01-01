package com.example.android_cinema_management.MovieManagement;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.Model.MovieDetail;
import com.example.android_cinema_management.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDetailsFragment2 extends Fragment {
    TextView directors,actors,producers,country,category,content,releaseDate;
    ImageView movieImage;
    ArrayList<MovieDetail> currentMovieList;


    public MovieDetailsFragment2() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details2, container, false);
        directors = view.findViewById(R.id.mf2_directors);
        actors = view.findViewById(R.id.mf2_actors);
        producers = view.findViewById(R.id.mf2_producers);
        category = view.findViewById(R.id.mf2_category);
        country = view.findViewById(R.id.mf2_country);
        content = view.findViewById(R.id.mf2_content);
        movieImage = view.findViewById(R.id.mf2_movieImage);
        releaseDate = view.findViewById(R.id.mf2_releasedDate);
        currentMovieList = MovieShowTimeFragment1.movieInformation;

        Picasso.get().load(MovieInfoTabLayout.currentMovie.getUrlImage()).into(movieImage);
        for (int i = 0; i <currentMovieList.size();i++){
            directors.setText("Directors: " + currentMovieList.get(i).getDirector());
            actors.setText("Actors: " + currentMovieList.get(i).getActors());
            producers.setText("Producers: " + currentMovieList.get(i).getProducers());
            category.setText("Category: " + currentMovieList.get(i).getCategory());
            country.setText("Country: " + currentMovieList.get(i).getCountry());
            content.setText(currentMovieList.get(i).getContent());
            releaseDate.setText("Released date: "+ currentMovieList.get(i).getReleaseDate());
        }
        return view;
    }
}