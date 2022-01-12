package com.example.android_cinema_management.UserManagement;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
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

import java.text.DecimalFormat;
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

    //Declare vietnamese currency format
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    // Declare auto complete text view
    AutoCompleteTextView ticketAutoCompleteTextView,comboAutoCompleteTextView;

    public BuyTicketFragment2() {
        // Required empty public constructor
    }



    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buying_ticket_by_movie2, container, false);

        //Initialize value for combo,ticket array
        ticketArray.add("Adult Ticket");
        ticketArray.add("Uni - Gold Ticket");
        ticketArray.add("Kids & Senior Ticket");
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

        //Binding xml value and set the dropdown for cinema
        ticket = view.findViewById(R.id.buy_by_movie_ticket_text_layout);
        //Initially Disable the cinema text layout
        ticketAdapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, ticketArray);
        ticketAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_ticket_auto_complete_text);
        ticketAutoCompleteTextView.setAdapter(ticketAdapterItems);
        ticketAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ticketChosen = parent.getItemAtPosition(position).toString();
                String priceFormat = formatter.format(calculatePrice(quantity.getText().toString(),comboChosen,ticketChosen));
                price.setText("Total Price: "+ priceFormat + " VNĐ");

            }
        });

        price.setText("Total Price: "+ 0 + " VND");

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String priceFormat = formatter.format(calculatePrice(quantity.getText().toString(),comboChosen,ticketChosen));
                price.setText("Total Price: "+ priceFormat + " VNĐ");

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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

    public double calculatePrice(String quantity, String combo, String ticketType){
        double totalPrice = 0;
        double priceTicket = 0;
        if (ticketType.equals("Adult Ticket")){
            priceTicket = 65000;
            if (!quantity.isEmpty()){
                totalPrice += Double.parseDouble(quantity) * priceTicket;
            }

        }

        else if (ticketType.equals("Uni - Gold Ticket")){
            priceTicket = 60000;
            if (!quantity.isEmpty()){
                totalPrice += Double.parseDouble(quantity) * priceTicket;
            }
        }
        else if (ticketType.equals("Kids & Senior Ticket")){
            priceTicket = 50000;
            if (!quantity.isEmpty()){
                totalPrice += Double.parseDouble(quantity) * priceTicket;
            }
        }
        return totalPrice;
    }
}