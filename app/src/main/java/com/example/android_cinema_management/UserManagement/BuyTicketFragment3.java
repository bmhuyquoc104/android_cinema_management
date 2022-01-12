package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android_cinema_management.R;

public class BuyTicketFragment3 extends Fragment {

    //Declare button
    Button next3;

    public BuyTicketFragment3() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buy_ticket_by_movie3, container, false);
        //Binding xml value
        next3 = view.findViewById(R.id.buy_by_movie3_next_bt);
        // Function to change fragment and send data to next step in buying ticket
        next3.setOnClickListener(View -> {
            Bundle bundle = new Bundle();
            BuyTicketFragment4 fragment4 = new BuyTicketFragment4();
            fragment4.setArguments(bundle);
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction transaction =
                    fm.beginTransaction();
            transaction.replace(R.id.buy_ticket_frame_layout, fragment4).addToBackStack("fragment4").commit();

        });
        return view;
    }
}