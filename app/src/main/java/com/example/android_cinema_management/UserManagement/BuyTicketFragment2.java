package com.example.android_cinema_management.UserManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    //Declare boolean
    boolean chooseTicket = false;
    boolean chooseCombo = false;

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

        //Initialize value for ticket array
        ticketArray.add("regular");
        ticketArray.add("membership");
        ticketArray.add("children");
        ticketArray.add("adult");
        //Binding xml and set enable drop down
        ticket = view.findViewById(R.id.buy_by_movie_ticket_text_layout);
        //Initially Disable the combo text layout
        ticketAdapterItems = new ArrayAdapter<String>(getContext(),R.layout.gender_selector_list, ticketArray);
        ticketAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_ticket_auto_complete_text);
        ticketAutoCompleteTextView.setAdapter(ticketAdapterItems);
        ticketAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                ticketChosen = parent.getItemAtPosition(i).toString();
//                System.out.println("TICKETTTTTTT " + ticketChosen);
                chooseTicket = true;
                if (chooseTicket){
                    combo.setEnabled(true);
                }else {
                    combo.setEnabled(false);
                }

            }
        });

        //Initialize value for combo array
        combosArray.add("Big Set");
        combosArray.add("Couple Combo");
        combosArray.add("Eco Combo");
        combosArray.add("Fast & Furious");
        //Binding xml and set enable drop down
        combo = view.findViewById(R.id.buy_by_movie_ticket_text_layout);
        //Initially Disable the combo text layout
        comboAdapterItems = new ArrayAdapter<String>(getContext(),R.layout.gender_selector_list, combosArray);
        comboAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_combo_auto_complete_text);
        comboAutoCompleteTextView.setAdapter(comboAdapterItems);
        comboAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                comboChosen = parent.getItemAtPosition(i).toString();
//                System.out.println("COMBOOOOOOOO " + comboChosen);
                chooseCombo = true;
                if (chooseCombo){
                    next2.setEnabled(true);
                }else {
                    next2.setEnabled(false);
                }

            }
        });

        // Function to change fragment and send data to next step in buying ticket
        next2.setOnClickListener(View -> {
            Double totalPrice = Double.parseDouble(quantity.getText().toString()) * 20.25;
//            System.out.println("QUANTITYYYYY " + quantity.getText().toString());
            Bundle bundle2 = new Bundle();
            bundle2.putString("movie",movie);
            bundle2.putString("cinema", cinema);
            bundle2.putString("date", date);
            bundle2.putString("time", time);
            bundle2.putString("ticket", ticketChosen);
            bundle2.putString("quantity", quantity.getText().toString());
            bundle2.putString("price", String.valueOf(totalPrice));
            bundle2.putString("combo", comboChosen);
            BuyTicketFragment3 fragment3 = new BuyTicketFragment3();
            fragment3.setArguments(bundle2);
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction transaction =
                    fm.beginTransaction();
            transaction.replace(R.id.buy_ticket_frame_layout, fragment3).addToBackStack("fragment2").commit();

        });

        return view;
    }
}