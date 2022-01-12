package com.example.android_cinema_management.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android_cinema_management.UserManagement.AddReviewFragment;
import com.example.android_cinema_management.UserManagement.AddTransactionFragment;
import com.example.android_cinema_management.UserManagement.ListOfReviewFragment;
import com.example.android_cinema_management.UserManagement.ListOfTransactionFragment;

public class TransactionFragmentAdapter extends FragmentStateAdapter {
    public TransactionFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new ListOfTransactionFragment();
        }
        return new AddTransactionFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
