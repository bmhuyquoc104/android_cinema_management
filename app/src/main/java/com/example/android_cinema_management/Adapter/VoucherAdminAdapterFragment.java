package com.example.android_cinema_management.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android_cinema_management.UserManagement.AdminManagment.AddVoucherFragment;
import com.example.android_cinema_management.UserManagement.AdminManagment.ListOfVoucherFragment;

public class VoucherAdminAdapterFragment extends FragmentStateAdapter {
    public VoucherAdminAdapterFragment(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new ListOfVoucherFragment();
        }
        return new AddVoucherFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
