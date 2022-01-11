package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cinema_management.R;


public class BuyingTicketByMovieFragment2 extends Fragment {

    public BuyingTicketByMovieFragment2() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buying_ticket_by_movie2, container, false);

        //getting content of fullName, email, password, confirmPassword, dateOfBirth, phone, address, gender
        Bundle bundle =this.getArguments();
        System.out.println(bundle);
        assert bundle != null;
        String movie = bundle.getString("movie");
        String cinema = bundle.getString("cinema");
        String date = bundle.getString("date");
        String time = bundle.getString("time");
        System.out.println("movie ne "+ movie);
        System.out.println("cinema ne "+ cinema);
        System.out.println("date ne "+ date);
        System.out.println("time ne "+ time);
        return view;
    }
}