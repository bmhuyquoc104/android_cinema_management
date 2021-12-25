package com.example.android_cinema_management.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android_cinema_management.AccountManagement.SignInFragment;
import com.example.android_cinema_management.AccountManagement.SignUpFragment;

public class LogInAdapter extends FragmentStateAdapter {
    public LogInAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){
           case 1:
               return new SignUpFragment();
       }
       return new SignInFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
