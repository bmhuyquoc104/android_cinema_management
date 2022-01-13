package com.example.android_cinema_management;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_cinema_management.Adapter.CinemaAdapter;
import com.example.android_cinema_management.Adapter.MoviesAdapter;
import com.example.android_cinema_management.Handler.MovieHandler;
import com.example.android_cinema_management.HomeManagement.HomeFragment1;
import com.example.android_cinema_management.HomeManagement.HomeFragment2;
import com.example.android_cinema_management.MainActivity;
import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.Model.Movie;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.database.CinemaDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Locale;

public class SearchMovieFragment extends Fragment {
    //Declare editText, button,textview
    EditText searchMovie;
    Button movieFilter;
    TextView movieResult;
    //Declare list for search filter and original list
    ArrayList<Movie> searchList, streamingMovieList,upcomingMovieList,movieList;
    ImageView close;
    //Declare auto complete text view
    AutoCompleteTextView country;
    AutoCompleteTextView category;
    AutoCompleteTextView rate;
    AutoCompleteTextView month;
    //Declare string range array, int last, and string comparison for filter value in the string
    String[] rateRange;
    double rateLast;
    String rateComparison;
    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private MoviesAdapter movieAdapter;

    public SearchMovieFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_filter_movie2, container, false);
        //Binding to xml value
        searchMovie = view.findViewById(R.id.search_movie_edittext);
        movieResult = view.findViewById(R.id.search_by_movie_result);
        recyclerView = view.findViewById(R.id.search_by_movie_recyclerview);
        recyclerView.setHasFixedSize(true);
        //Instantiate array list
        searchList = new ArrayList<>();
        streamingMovieList = HomeFragment1.movieList;
        upcomingMovieList = HomeFragment2.upcomingMovieList;
        movieList = new ArrayList<>();

        // If upcoming movie list not equal to null -> add upcoming movie list to movie list
        if ( upcomingMovieList != null) {
            movieList.addAll(streamingMovieList);
            movieList.addAll(upcomingMovieList);
        }
        // If the user no click upcoming in the tab home tab layout -> only add streaming movie to the list
        else {
            movieList.addAll(streamingMovieList);
        }

        System.out.println("huy ne" + movieList);
        //Use a grid layout manager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false);

        // Instantiate adapter
        movieAdapter = new MoviesAdapter(movieList, getContext());
        // Set layout for recycler view
        recyclerView.setLayoutManager(gridLayoutManager);
        // Set adapter for recycler view
        recyclerView.setAdapter(movieAdapter);

        /**
         * Method for search movie in the movie list and return the correct one by user
         * */
        searchMovie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            /**
             * Method to detect the value by key changed from user
             * */
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Get the input from user
                String inputText = searchMovie.getText().toString();
                // Initialize the new search list when ever the user search -> avoid stack lists
                searchList = new ArrayList<>();
                searchMovieByAllAttributes(movieList, searchList, inputText);
                // Instantiate adapter
                movieAdapter = new MoviesAdapter(searchList, getContext());
                // Set layout for recycler view
                recyclerView.setLayoutManager(gridLayoutManager);
                // Set adapter for recycler view
                recyclerView.setAdapter(movieAdapter);
                //Create spannalbe String
                SpannableStringBuilder builder = new SpannableStringBuilder();

                // Text and color for string 1
                SpannableString str1= new SpannableString("Total Movies : ");
                str1.setSpan(new ForegroundColorSpan(Color.rgb(161,161,161)), 0, str1.length(), 0);
                builder.append(str1);

                // Text and color for string 2
                SpannableString str2= new SpannableString(Integer.toString(searchList.size()));
                str2.setSpan(new ForegroundColorSpan(Color.rgb(222,22,25)), 0, str2.length(), 0);
                builder.append(str2);

                // Set text for edittext
                movieResult.setText( builder, Button.BufferType.SPANNABLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
         //Create spannalbe String
        SpannableStringBuilder builder = new SpannableStringBuilder();

        // Text and color for string 1
        SpannableString str1= new SpannableString("Total Movies : ");
        str1.setSpan(new ForegroundColorSpan(Color.rgb(161,161,161)), 0, str1.length(), 0);
        builder.append(str1);

        // Text and color for string 2
        SpannableString str2= new SpannableString(Integer.toString(movieList.size()));
        str2.setSpan(new ForegroundColorSpan(Color.rgb(222,22,25)), 0, str2.length(), 0);
        builder.append(str2);

        // Set text for editext
        movieResult.setText( builder, Button.BufferType.SPANNABLE);


        return view;
    }

    /**
     * Function to search cinema List by attributes and display in the UI
     */
    private void searchMovieByAllAttributes(ArrayList<Movie> movieList, ArrayList<Movie> searchList, String keyword) {
        for (Movie movie : movieList) {
            // Check if the instance contains the keyword or not
            if (movie.getEnglishTitle().trim().toLowerCase().contains(keyword.trim().toLowerCase()) ||
                    movie.getVietnameseTitle().trim().toLowerCase().contains(keyword.trim().toLowerCase())
                   ) {
                // add to the search list
                searchList.add(movie);
            }
        }
    }

}


