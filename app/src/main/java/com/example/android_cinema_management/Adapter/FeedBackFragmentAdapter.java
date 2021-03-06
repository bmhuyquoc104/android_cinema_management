package com.example.android_cinema_management.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android_cinema_management.UserManagement.ListOfFeedbackFragment;
import com.example.android_cinema_management.UserManagement.SendingFeedBackFragment;

public class FeedBackFragmentAdapter extends FragmentStateAdapter {
    public FeedBackFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new ListOfFeedbackFragment();
        }
        return new SendingFeedBackFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
