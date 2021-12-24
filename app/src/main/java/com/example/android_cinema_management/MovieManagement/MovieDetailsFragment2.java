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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment2 extends Fragment {
    //Declare textview and imageview
    TextView directors,actors,producers,country,category,content,releaseDate;
    ImageView movieImage;
    ArrayList<MovieDetail> currentMovieList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment2 newInstance(String param1, String param2) {
        MovieDetailsFragment2 fragment = new MovieDetailsFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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