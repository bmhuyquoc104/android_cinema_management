package com.example.android_cinema_management.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android_cinema_management.SearchItemManagement.SearchFilterByCinemaFragment;
import com.example.android_cinema_management.SearchItemManagement.SearchFilterMovieFragment2;

public class MovieAndCinemaSearchFilterAdapter extends FragmentStateAdapter {
    public MovieAndCinemaSearchFilterAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new SearchFilterByCinemaFragment();
        }
        return new SearchFilterMovieFragment2();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
