package com.example.android_cinema_management.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android_cinema_management.AccountManagement.SignUpFragment;
import com.example.android_cinema_management.AccountManagement.SignUpFragment2;
import com.example.android_cinema_management.AccountManagement.SignUpFragment3;

public class RegisterAdapter extends FragmentStateAdapter {
    public RegisterAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SignUpFragment();
            case 1:
                return new SignUpFragment2();
            case 2:
                return new SignUpFragment3();
        }
        return new SignUpFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
