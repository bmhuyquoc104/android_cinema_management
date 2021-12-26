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
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {
    // Declare edit text
    EditText fullName,email;
    String inputFullName,inputEmail;
    TextInputEditText password,confirmPassword;
    Button nextPage;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        // Binding value to XML layout file
        fullName = view.findViewById(R.id.frag1SignUp_fullName);
        email = view.findViewById(R.id.frag1SignUp_email);
        System.out.println("dqwdasdasdas" +inputEmail);
        System.out.println("dqwdasdasdas" +inputFullName);
        password = view.findViewById(R.id.frag1SignUp_password);
        confirmPassword = view.findViewById(R.id.fgSignUp_confirmPassword);
        nextPage = view.findViewById(R.id.fgSignUp_continueButton);
//        nextPage.setEnabled(false);
        // Get value from user

//        if (fullName.length() > 0 && email.length() >0) {
//            nextPage.setEnabled(true);
//        }

        nextPage.setOnClickListener(View ->{
            inputFullName =  fullName.getText().toString();
            inputEmail =  email.getText().toString();
            Bundle bundle=new Bundle();
            bundle.putString("fullName",inputFullName);
            System.out.println(inputFullName);
//            bundle.putString("password",inputPassWord);
            bundle.putString("email",inputEmail);
            System.out.println(inputEmail);
            SignUpFragment3 fragment2 =new SignUpFragment3();
            fragment2.setArguments(bundle);
            FragmentManager fm = getParentFragmentManager();

            FragmentTransaction transaction =
                    fm.beginTransaction();
            transaction.replace(R.id.signUp_frameLayout,fragment2).addToBackStack("fragment1").commit();

        });


        return view;
    }
}