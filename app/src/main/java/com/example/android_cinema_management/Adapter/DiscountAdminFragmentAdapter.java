package com.example.android_cinema_management.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android_cinema_management.UserManagement.AdminManagment.AddComboFragment;
import com.example.android_cinema_management.UserManagement.AdminManagment.AddDiscountFragment;
import com.example.android_cinema_management.UserManagement.AdminManagment.ListOfAllDiscount;
import com.example.android_cinema_management.UserManagement.AdminManagment.ListOfComboAdminFragment;

public class DiscountAdminFragmentAdapter extends FragmentStateAdapter {
    public DiscountAdminFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new ListOfAllDiscount();
        }
        return new AddDiscountFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
