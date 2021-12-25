package com.example.android_cinema_management.AccountManagement;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android_cinema_management.Adapter.HomeAdapter;
import com.example.android_cinema_management.Adapter.LogInAdapter;
import com.example.android_cinema_management.MovieManagement.MovieInfoTabLayout;
import com.example.android_cinema_management.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Accounts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Accounts extends Fragment {
    //Declare adapter and button
    Button register, signIn;
    LogInAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Accounts() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Accounts.
     */
    // TODO: Rename and change types and number of parameters
    public static Accounts newInstance(String param1, String param2) {
        Accounts fragment = new Accounts();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);
        register = view.findViewById(R.id.ac_register);
        signIn = view.findViewById(R.id.ac_signIn);
        // Initialize fragment manager
        FragmentManager fm = getParentFragmentManager();
        // Initialize adapter
        adapter = new LogInAdapter(fm,getLifecycle());
        // Function click change to another fragment
        register.setOnClickListener(View ->{
            Intent intent = new Intent(getContext(),SignUp.class);
            //Send intent to sign Up activity

            // Start intent
            try {
                startActivity(intent);
            }
            // Exception if activity is not found
            catch (ActivityNotFoundException e){
                Toast.makeText(getContext(),"Oops!! Something wrong, Please try again!" ,Toast.LENGTH_LONG).show();
            }
        });
        return view;

    }
}