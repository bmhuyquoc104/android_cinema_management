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

import com.example.android_cinema_management.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class BuyTicketFragment3 extends Fragment {

    //Declare EditText textInputLayout
    EditText seat, point, screen;
    TextInputLayout paymentMethod;

    //Declare paymentMethod array option
    ArrayList<String> paymentMehtodhArray  = new ArrayList<>();

    //Declare button
    Button next3;

    //Declare boolean
    boolean choosePaymentMethod = false;

    //Declare String
    String paymentMethodChosen;

    ArrayAdapter<String> paymentMethodAdapterItem;

    AutoCompleteTextView paymentMethodAutoCompleteTextView;

    public BuyTicketFragment3() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buy_ticket_by_movie3, container, false);

        //Binding xml value
        seat = view.findViewById(R.id.buy_ticket_seat_et);
        point = view.findViewById(R.id.buy_ticket_point_et);
        screen = view.findViewById(R.id.buy_ticket_screen_et);
        next3 = view.findViewById(R.id.buy_by_movie3_next_bt);

        Bundle bundle = this.getArguments();
        assert bundle != null;
        String movie = bundle.getString("movie");
        String cinema = bundle.getString("cinema");
        String date = bundle.getString("date");
        String time = bundle.getString("time");
        String ticket = bundle.getString("ticket");
        String quantity = bundle.getString("quantity");
        String price = bundle.getString("price");
        String combo = bundle.getString("combo");
        System.out.println("TICKETTTTTT " + ticket);
        System.out.println("QUANTITYYYYYY " + quantity);
        System.out.println("COMBOOOOOOOO " + combo);



        //Initialize value for paymentMethod array
        paymentMehtodhArray.add("Visa");
        paymentMehtodhArray.add("MasterCard");
        paymentMehtodhArray.add("Debit");
        paymentMehtodhArray.add("American Express");
        paymentMehtodhArray.add("Zalo Pay");
        paymentMehtodhArray.add("MoMo");

        //Binding xml and set enable drop down
        paymentMethod = view.findViewById(R.id.buy_by_movie_payment_method_layout);
        //Initially disable the combo text layout
        paymentMethodAdapterItem = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, paymentMehtodhArray);
        paymentMethodAutoCompleteTextView = view.findViewById(R.id.buy_by_movie_payment_method_auto_complete_text);
        paymentMethodAutoCompleteTextView.setAdapter(paymentMethodAdapterItem);
        paymentMethodAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                paymentMethodChosen = parent.getItemAtPosition(i).toString();
                choosePaymentMethod = true;
                if (choosePaymentMethod){
                    next3.setEnabled(true);
                }else {
                    next3.setEnabled(false);
                }
            }
        });

        // Function to change fragment and send data to next step in buying ticket
        next3.setOnClickListener(View -> {
            String inputSeat = seat.getText().toString();
            String inputScreen = screen.getText().toString();
            String inputPoint = screen.getText().toString();
            Bundle bundle2 = new Bundle();
            bundle2.putString("movie",movie);
            bundle2.putString("cinema", cinema);
            bundle2.putString("date", date);
            bundle2.putString("time", time);
            bundle2.putString("ticket", ticket);
            bundle2.putString("quantity", quantity);
            bundle2.putString("price", price);
            bundle2.putString("combo", combo);
            bundle2.putString("seat", inputSeat);
            bundle2.putString("screen", inputScreen);
            bundle2.putString("point", inputPoint);
            BuyTicketFragment4 fragment4 = new BuyTicketFragment4();
            fragment4.setArguments(bundle2);
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction transaction =
                    fm.beginTransaction();
            transaction.replace(R.id.buy_ticket_frame_layout, fragment4).addToBackStack("fragment3").commit();

        });
        return view;
    }
}