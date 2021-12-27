package com.example.android_cinema_management.AccountManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android_cinema_management.R;


public class SignUpFragment3 extends Fragment {
    String email,password, fullName;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment3() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SignUpFragment3 newInstance(String param1, String param2) {
        SignUpFragment3 fragment = new SignUpFragment3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up3, container, false);
        Bundle bundle =this.getArguments();
        System.out.println(bundle);
        assert bundle != null;
        String fullName = bundle.getString("fullName");
        String email = bundle.getString("email");
        String password = bundle.getString("password");
        String confirmPassword = bundle.getString("confirmPassword");
        String dateOfBirth = bundle.getString("dateOfBirth");
        String phone = bundle.getString("phone");
        String address = bundle.getString("address");
        String gender = bundle.getString("gender");
        System.out.println("fullName: " +fullName + "email: "+email + "password: "+password + confirmPassword + dateOfBirth + phone+address+gender);
        return view;
    }
}