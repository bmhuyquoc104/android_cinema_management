package com.example.android_cinema_management.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android_cinema_management.UserManagement.AddReviewFragment;
import com.example.android_cinema_management.UserManagement.CurrentUserReviewFragment;
import com.example.android_cinema_management.UserManagement.ListOfReviewFragment;

public class ReviewFragmentAdapter extends FragmentStateAdapter {
    public ReviewFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new CurrentUserReviewFragment();
            case 2:
                return new ListOfReviewFragment();
        }
        return new AddReviewFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
