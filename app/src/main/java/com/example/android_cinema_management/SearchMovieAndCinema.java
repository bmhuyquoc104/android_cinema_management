package com.example.android_cinema_management;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cinema_management.Adapter.MovieAndCinemaSearchFilterAdapter;
import com.google.android.material.tabs.TabLayout;


public class SearchMovieAndCinema extends Fragment {
    //Declare tablayout, adapter and viewpager2
    TabLayout layout;
    ViewPager2 viewpager2;
    MovieAndCinemaSearchFilterAdapter adapter;

    public SearchMovieAndCinema() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_movie_and_cinema, container, false);
        //Binding with XML values
        layout = view.findViewById(R.id.fragSearch);
        viewpager2 = view.findViewById(R.id.fragSearchViewPager2);

        // Initialize fragment manager
        FragmentManager fm = getParentFragmentManager();
        // Initialize adapter
        adapter = new MovieAndCinemaSearchFilterAdapter(fm,getLifecycle());
        // Set adapter to viewpage2
        viewpager2.setAdapter(adapter);
        // Change the layout by tab selected
        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                layout.selectTab(layout.getTabAt(position));
            }
        });
        //Disable swiping
        viewpager2.setUserInputEnabled(false);
        return view;
    }
}