package com.example.android_cinema_management.UserManagement;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
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
    EditText ticketQuantity, comboQuantity;
    TextView price;
    TextInputLayout ticket, combo;

    //Declare combo,ticket array options
    ArrayList<String> combosArray = new ArrayList<>();
    ArrayList<String> ticketArray = new ArrayList<>();

    //Declare button
    Button next2;

    //Declare boolean
    private boolean chooseTicket = false;
    private boolean chooseTicketQuantity = false;

    //Declare string
    String ticketChosen, comboChosen;

    //Declare array adapter
    ArrayAdapter<String> ticketAdapterItems;
    ArrayAdapter<String> comboAdapterItems;

    //Declare vietnamese currency format
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    // Declare auto complete text view
    AutoCompleteTextView ticketAutoCompleteTextView, comboAutoCompleteTextView;

    // Declare string array to split the date into dateOfWeek
    String[] spiltDate;
    String dateOfWeek;
    String time;

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
        combosArray.add("Regular Combo");
        combosArray.add("Snacky Combo");
        combosArray.add("Companion Combo");
        combosArray.add("Hydrate Combo");
        combosArray.add("Mega Combo");
        combosArray.add("Choose Combo");

        //Binding xml value
        next2 = view.findViewById(R.id.buy_by_movie2_next_bt);
        next2.setEnabled(false);
        price = view.findViewById(R.id.buy_ticket_totalPrice_tv);
        ticketQuantity = view.findViewById(R.id.buy_ticket_ticket_quantity_et);
        comboQuantity = view.findViewById(R.id.buy_ticket_combo_quantity_et);
        //getting content of fullName, email, password, confirmPassword, dateOfBirth, phone, address, gender
        Bundle bundle = this.getArguments();
        System.out.println(bundle);
        assert bundle != null;
        String movie = bundle.getString("movie");
        String cinema = bundle.getString("cinema");
        String date = bundle.getString("date");
        String screen = bundle.getString("screen");
        // Split the date to get the day of week and store in in string dateOfWeek
        spiltDate = date.split(",");
        dateOfWeek = spiltDate[0];


        time = bundle.getString("time");

        comboChosen = "";
        ticketChosen = "";
        comboQuantity.setText("");
        ticketQuantity.setText("");
        comboQuantity.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        ticketQuantity.setTransformationMethod(new NumericKeyBoardTransformationMethod());

        //Binding xml value and set the dropdown for ticket
        ticket = view.findViewById(R.id.buy_by_movie_ticket_text_layout);
        ticketAdapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, ticketArray);
        ticketAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_ticket_auto_complete_text);
        ticketAutoCompleteTextView.setAdapter(ticketAdapterItems);
        ticketAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ticketChosen = parent.getItemAtPosition(position).toString();
                Double finalPrice = calculatePrice(ticketQuantity.getText().toString(), ticketChosen) +
                        calculateComboPrice(comboQuantity.getText().toString(), comboChosen);
                String priceFormat = formatter.format(finalPrice);
                price.setText("Total Price: " + priceFormat + " VNĐ");

                // ticket layout is  chosen
                chooseTicket = true;
                if (chooseTicket && chooseTicketQuantity) {
                    //Enable the next button
                    next2.setEnabled(true);
                } else {
                    //Keep disable next button
                    next2.setEnabled(false);
                }
            }


        });


        // Initially set the price to 0
        price.setText("Total Price: " + 0 + " VND");


        ticketQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Double finalPrice = calculatePrice(ticketQuantity.getText().toString(), ticketChosen) +
                        calculateComboPrice(comboQuantity.getText().toString(), comboChosen);
                String priceFormat = formatter.format(finalPrice);
                price.setText("Total Price: " + priceFormat + " VNĐ");
                // ticket quantity  is  chosen
                chooseTicketQuantity = true;
                if (chooseTicket && chooseTicketQuantity) {
                    //Enable the next button
                    next2.setEnabled(true);
                } else {
                    //Keep disable next button
                    next2.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Binding xml value and set the dropdown for ticket
        combo = view.findViewById(R.id.buy_by_movie_combo_text_layout);
        comboAdapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, combosArray);
        comboAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_combo_auto_complete_text);
        comboAutoCompleteTextView.setAdapter(comboAdapterItems);
        comboAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                comboChosen = parent.getItemAtPosition(position).toString();
                Double finalPrice = calculatePrice(ticketQuantity.getText().toString(), ticketChosen) + calculateComboPrice(comboQuantity.getText().toString(), comboChosen);
                String priceFormat = formatter.format(finalPrice);
                price.setText("Total Price: " + priceFormat + " VNĐ");

            }
        });


        comboQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Double finalPrice = calculatePrice(ticketQuantity.getText().toString(), ticketChosen) + calculateComboPrice(comboQuantity.getText().toString(), comboChosen);
                String priceFormat = formatter.format(finalPrice);
                price.setText("Total Price: " + priceFormat + " VNĐ");

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        // Function to change fragment and send data to next step in buying ticket
        next2.setOnClickListener(View -> {
            String totalPrice = price.getText().toString();
            String ticketTotalQuantity = ticketQuantity.getText().toString();
            String comboTotalQuantity = comboQuantity.getText().toString();
            Bundle bundle2 = new Bundle();
            bundle2.putString("ticket", ticketChosen);
            bundle2.putString("ticketQuantity", ticketTotalQuantity);
            bundle2.putString("price", totalPrice);
            bundle2.putString("combo", comboChosen);
            bundle2.putString("comboQuantity", comboTotalQuantity);
            bundle2.putString("movie", movie);
            bundle2.putString("cinema", cinema);
            bundle2.putString("date", date);
            bundle2.putString("screen", screen);
            bundle2.putString("time", time);
            BuyTicketFragment3 fragment3 = new BuyTicketFragment3();
            fragment3.setArguments(bundle2);
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction transaction =
                    fm.beginTransaction();
            transaction.replace(R.id.buy_ticket_frame_layout, fragment3).addToBackStack("fragment3").commit();

        });

        return view;
    }

    public double calculatePrice(String ticketQuantity, String ticketType) {
        double totalPrice = 0;
        double priceTicket = 0;

        if (ticketType.equals("Adult Ticket")) {
            if (dateOfWeek.equals("Tuesday")) {
                priceTicket = 50000;
            }

            if (dateOfWeek.equals("Monday") || dateOfWeek.equals("Wednesday") || dateOfWeek.equals("Thursday")) {
                if (time.compareTo("17:00") > 0) {
                    priceTicket = 70000;
                } else {
                    priceTicket = 65000;
                }
            }
            if (dateOfWeek.equals("Friday") || dateOfWeek.equals("Saturday") || dateOfWeek.equals("Sunday")) {
                if (time.compareTo("17:00") > 0) {
                    priceTicket = 80000;
                } else {
                    priceTicket = 75000;
                }
            }
            if (!ticketQuantity.isEmpty()) {
                totalPrice += Double.parseDouble(ticketQuantity) * priceTicket;
            }
        } else if (ticketType.equals("Uni - Gold Ticket")) {
            if (dateOfWeek.equals("Tuesday")) {
                priceTicket = 50000;
            }

            if (dateOfWeek.equals("Monday") || dateOfWeek.equals("Wednesday") || dateOfWeek.equals("Thursday")) {
                if (time.compareTo("17:00") > 0) {
                    priceTicket = 65000;
                } else {
                    priceTicket = 60000;
                }
            }
            if (dateOfWeek.equals("Friday") || dateOfWeek.equals("Saturday") || dateOfWeek.equals("Sunday")) {
                if (time.compareTo("17:00") > 0) {
                    priceTicket = 70000;
                } else {
                    priceTicket = 65000;
                }
            }

            if (!ticketQuantity.isEmpty()) {
                totalPrice += Double.parseDouble(ticketQuantity) * priceTicket;
            }
        } else if (ticketType.equals("Kids & Senior Ticket")) {
            if (dateOfWeek.equals("Tuesday")) {
                priceTicket = 45000;
            }

            if (dateOfWeek.equals("Monday") || dateOfWeek.equals("Wednesday") || dateOfWeek.equals("Thursday")) {
                priceTicket = 50000;
            }
            if (dateOfWeek.equals("Friday") || dateOfWeek.equals("Saturday") || dateOfWeek.equals("Sunday")) {
                priceTicket = 60000;
            }
            if (!ticketQuantity.isEmpty()) {
                totalPrice += Double.parseDouble(ticketQuantity) * priceTicket;
            }
        }
        return totalPrice;
    }

    private class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return source;
        }
    }

    public double calculateComboPrice(String quantity, String combo) {
        double comboPrice = 0;
        double totalComboPrice = 0;
        if (combo.equals("Regular Combo")) {
            comboPrice = 69000;
            if (!quantity.isEmpty()) {
                totalComboPrice += comboPrice * Double.parseDouble(quantity);
            }
        } else if (combo.equals("Snacky Combo")) {
            comboPrice = 89000;
            if (!quantity.isEmpty()) {
                totalComboPrice += comboPrice * Double.parseDouble(quantity);
            }
        } else if (combo.equals("Companion Combo")) {
            comboPrice = 99000;
            if (!quantity.isEmpty()) {
                totalComboPrice += comboPrice * Double.parseDouble(quantity);
            }
        } else if (combo.equals("Hydrate Combo")) {
            comboPrice = 119000;
            if (!quantity.isEmpty()) {
                totalComboPrice += comboPrice * Double.parseDouble(quantity);
            }
        } else if (combo.equals("Mega Combo")) {
            comboPrice = 149000;
            if (!quantity.isEmpty()) {
                totalComboPrice += comboPrice * Double.parseDouble(quantity);
            }
        } else {
            comboPrice = 0;
            if (!quantity.isEmpty()) {
                totalComboPrice += comboPrice * Double.parseDouble(quantity);
            }
        }
        return totalComboPrice;
    }

}