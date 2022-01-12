package com.example.android_cinema_management.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android_cinema_management.TicketAndDiscountManagement.DiscountFragment;
import com.example.android_cinema_management.TicketAndDiscountManagement.TicketFragment;

public class DiscountAndTicketAdapter extends FragmentStateAdapter {
    public DiscountAndTicketAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new DiscountFragment();
        }
        return new TicketFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
