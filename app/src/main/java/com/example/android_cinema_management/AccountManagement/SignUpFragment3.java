package com.example.android_cinema_management.AccountManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android_cinema_management.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment3 extends Fragment {
    String email,password, fullName;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment3.
     */
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