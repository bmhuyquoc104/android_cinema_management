package com.example.android_cinema_management.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cinema_management.CinemaManagement.AddCinemaFragment;
import com.example.android_cinema_management.CinemaManagement.ListOfCinemaFragment;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.AddReviewFragment;
import com.example.android_cinema_management.UserManagement.ListOfReviewFragment;

public class CinemaFragmentAdapter extends FragmentStateAdapter {


    public CinemaFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new ListOfCinemaFragment();
        }
        return new AddCinemaFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}