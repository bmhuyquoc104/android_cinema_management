package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android_cinema_management.CinemaManagement.CinemaFragment;
import com.example.android_cinema_management.HomeManagement.HomeFragment1;
import com.example.android_cinema_management.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class BuyTicketFragment2 extends Fragment {
    // Declare edittext,textview, text layout
    EditText quantity;
    TextView price;
    TextInputLayout ticket,combo;

    //Declare combo,ticket array options
    ArrayList<String> combosArray = new ArrayList<>();
    ArrayList<String> ticketArray = new ArrayList<>();

    //Declare button
    Button next2;

    //Declare string
    String ticketChosen,comboChosen;

    //Declare array adapter
    ArrayAdapter<String> ticketAdapterItems;
    ArrayAdapter<String> comboAdapterItems;

    // Declare auto complete text view
    AutoCompleteTextView ticketAutoCompleteTextView,comboAutoCompleteTextView;

    public BuyTicketFragment2() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buying_ticket_by_movie2, container, false);

        //Initialize value for combo,ticket array
        ticketArray = HomeFragment1.movieTitleList;
        combosArray = CinemaFragment.cinemaNameList;

        //Binding xml value
        next2 = view.findViewById(R.id.buy_by_movie2_next_bt);
        price = view.findViewById(R.id.buy_ticket_totalPrice_tv);
        quantity = view.findViewById(R.id.buy_ticket_quantity_et);
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

        // Function to change fragment and send data to next step in buying ticket
        next2.setOnClickListener(View -> {
            String totalPrice = price.getText().toString();
            String totalQuantity = quantity.getText().toString();
            Bundle bundle2 = new Bundle();
            bundle2.putString("ticket", ticketChosen);
            bundle2.putString("quantity", totalQuantity);
            bundle2.putString("price", totalPrice);
            bundle2.putString("combo", comboChosen);
            BuyTicketFragment3 fragment3 = new BuyTicketFragment3();
            fragment3.setArguments(bundle);
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction transaction =
                    fm.beginTransaction();
            transaction.replace(R.id.buy_ticket_frame_layout, fragment3).addToBackStack("fragment3").commit();

        });

        return view;
    }
}