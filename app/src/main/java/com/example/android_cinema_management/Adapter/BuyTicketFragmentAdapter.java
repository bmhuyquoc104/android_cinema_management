package com.example.android_cinema_management.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.android_cinema_management.UserManagement.BuyByCinemaFragment;
import com.example.android_cinema_management.UserManagement.BuyByDayFragment;
import com.example.android_cinema_management.UserManagement.BuyByMovieFragment;

public class BuyTicketFragmentAdapter extends FragmentStateAdapter {
    public BuyTicketFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BuyByMovieFragment();
            case 1:
                return new BuyByDayFragment();
            case 2:
                return new BuyByCinemaFragment();
        }
        return new BuyByMovieFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
