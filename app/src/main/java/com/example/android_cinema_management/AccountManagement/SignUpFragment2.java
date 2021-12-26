package com.example.android_cinema_management.AccountManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android_cinema_management.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment2 extends Fragment {
    EditText dateOfBirth,address,phone,gender;
    Button nextPage;
    String fullName,password,email,confirmPassword;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment2 newInstance(String param1, String param2) {
        SignUpFragment2 fragment = new SignUpFragment2();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up2, container, false);

        address = view.findViewById(R.id.frag2SignUp_address);
        dateOfBirth = view.findViewById(R.id.frag2SignUp_dateOfBirth);
        gender = view.findViewById(R.id.frag2SignUp_gender);
        phone = view.findViewById(R.id.frag2SignUp_phone);
        nextPage = view.findViewById(R.id.buttonContinue);

        Bundle bundle =this.getArguments();
        System.out.println(bundle);
        assert bundle != null;
         fullName = bundle.getString("fullName");
         email = bundle.getString("email");
         password = bundle.getString("password");
         confirmPassword = bundle.getString("confirmPassword");
        System.out.println("fullName: " +fullName + "email: "+email + "password: "+password + "confirmpassword: "
                + confirmPassword);


        nextPage.setOnClickListener(View ->{
            Bundle bundle2 = new Bundle();
            bundle2.putString("address", address.getText().toString());
            bundle2.putString("dateOfBirth", dateOfBirth.getText().toString());
            bundle2.putString("gender", gender.getText().toString());
            bundle2.putString("fullName", fullName);
            bundle2.putString("email", email);
            bundle2.putString("password", password);
            bundle2.putString("confirmPassword", confirmPassword);
            bundle2.putString("phone", phone.getText().toString());
            SignUpFragment3 fragment3 = new SignUpFragment3();
            fragment3.setArguments(bundle2);
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction transaction =
                    fm.beginTransaction();
            transaction.replace(R.id.signUp_frameLayout, fragment3).addToBackStack("fragment2").commit();
        });

        return view;
    }
}