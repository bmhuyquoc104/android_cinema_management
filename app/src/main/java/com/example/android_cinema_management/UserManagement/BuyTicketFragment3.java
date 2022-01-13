package com.example.android_cinema_management.UserManagement;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.R;

import java.util.ArrayList;
import java.util.List;

public class BuyTicketFragment3 extends Fragment {

    //Declare button
    Button next3;

    //Declare textview
    TextView available,unAvailable,selecting,reserved;

    //Declare boolean
    private boolean chooseSeat = false;
    ViewGroup chooseSeatLayout;


    // Declare the string of all seats
    String seats = "_AAAAAAAAAARRRR_/"
            + "_________________/"
            + "UU__AAAARRRRR__RR/"
            + "AA__RRRAAAAAA__AA/"
            + "AA__AAAAAAAAA__AA/"
            + "AA__AARUUUURR__AA/"
            + "RR__UUUA_RRRR__AA/"
            + "AA__AAAA_RRAA__RR/"
            + "AA__AARR_UUUU__RR/"
            + "AA__UUUU_UURR__RR/"
            + "_________________/"
            + "_________________/";

    // Declare list of textview for render seat programmatically
    List<TextView> listOfSeatView = new ArrayList<>();
    // Set the width for each seat
    int seatWidth = 100;
    // Set the margin between each seat
    int seatMargin = 10;

    // Set status of the seat
    int SEAT_IS_AVAILABLE = 1;
    int SEAT_IS_UNAVAILABLE = 2;
    int SEAT_IS_RESERVED = 3;

    // Declare the string
    String seatChosen = "";

    public BuyTicketFragment3() {
        // Required empty public constructor
    }


    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buy_ticket_by_movie3, container, false);
        //Binding xml value
        available = view.findViewById(R.id.buy_ticket_available_tv);
        unAvailable = view.findViewById(R.id.buy_ticket_unavailable_tv);
        selecting = view.findViewById(R.id.buy_ticket_selecting_tv);
        reserved = view.findViewById(R.id.buy_ticket_reserved_tv);
        next3 = view.findViewById(R.id.buy_by_movie3_next_bt);
        chooseSeatLayout = view.findViewById(R.id.layoutSeat);

        //getting content of fullName, email, password, confirmPassword, dateOfBirth, phone, address, gender
        Bundle bundle = this.getArguments();
        System.out.println(bundle);
        assert bundle != null;
        String movie = bundle.getString("movie");
        String cinema = bundle.getString("cinema");
        String date = bundle.getString("date");
        String screen = bundle.getString("screen");
        String time = bundle.getString("time");
        String ticketChosen = bundle.getString("ticket");
        String ticketTotalQuantity = bundle.getString("ticketQuantity");
        String totalPrice = bundle.getString("price");
        String comboChosen = bundle.getString("combo");
        String comboTotalQuantity = bundle.getString("comboQuantity");



        // Function to change fragment and send data to next step in buying ticket

        next3.setEnabled(false);
        next3.setOnClickListener(View -> {
            Bundle bundle2 = new Bundle();
            bundle2.putString("seat", seatChosen);
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
            BuyTicketFragment4 fragment4 = new BuyTicketFragment4();
            fragment4.setArguments(bundle2);
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction transaction =
                    fm.beginTransaction();
            transaction.replace(R.id.buy_ticket_frame_layout, fragment4).addToBackStack("fragment4").commit();

        });



        seats = "/" + seats;
        LinearLayout layoutSeat = new LinearLayout(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setPadding(8 * seatMargin, 8 * seatMargin, 8 * seatMargin, 8 * seatMargin);
        chooseSeatLayout.addView(layoutSeat);

        LinearLayout layout = null;

        // Initialize count
        int count = 0;

        for (int index = 0; index < seats.length(); index++) {
            if (seats.charAt(index) == '/') {
                layout = new LinearLayout(getContext());
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layoutSeat.addView(layout);
                // Render appropriate seat if seat is unavailable
            } else if (seats.charAt(index) == 'U') {
                count++;
                TextView textView = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatWidth, seatWidth);
                layoutParams.setMargins(seatMargin, seatMargin, seatMargin, seatMargin);
                textView.setLayoutParams(layoutParams);
                textView.setPadding(0, 0, 0, 2 * seatMargin);
                textView.setId(count);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.seat_unavailable);
                textView.setTextColor(Color.BLACK);
                textView.setTag(SEAT_IS_UNAVAILABLE);
                textView.setText(count + "");
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11);
                layout.addView(textView);
                listOfSeatView.add(textView);
                textView.setOnClickListener(View -> {
                    chooseSeat(textView);
                });
                // Render appropriate seat if seat is available
            } else if (seats.charAt(index) == 'A') {
                count++;
                TextView textView = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatWidth, seatWidth);
                layoutParams.setMargins(seatMargin, seatMargin, seatMargin, seatMargin);
                textView.setLayoutParams(layoutParams);
                textView.setPadding(0, 0, 0, 2 * seatMargin);
                textView.setId(count);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.seat_available);
                textView.setText(count + "");
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11);
                textView.setTextColor(Color.WHITE);
                textView.setTag(SEAT_IS_AVAILABLE);
                layout.addView(textView);
                listOfSeatView.add(textView);
                textView.setOnClickListener(View -> {
                    chooseSeat(textView);
                });
                // Render appropriate seat if seat is reserved
            } else if (seats.charAt(index) == 'R') {
                count++;
                TextView textView = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatWidth, seatWidth);
                layoutParams.setMargins(seatMargin, seatMargin, seatMargin, seatMargin);
                textView.setLayoutParams(layoutParams);
                textView.setPadding(0, 0, 0, 2 * seatMargin);
                textView.setId(count);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.seat_reserved);
                textView.setText(count + "");
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11);
                textView.setTextColor(Color.BLACK);
                textView.setTag(SEAT_IS_RESERVED);
                layout.addView(textView);
                listOfSeatView.add(textView);
                textView.setOnClickListener(View -> {
                    chooseSeat(textView);
                });
            } else if (seats.charAt(index) == '_') {
                TextView textView = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatWidth, seatWidth);
                layoutParams.setMargins(seatMargin, seatMargin, seatMargin, seatMargin);
                textView.setLayoutParams(layoutParams);
                textView.setBackgroundColor(Color.TRANSPARENT);
                textView.setText("");
                layout.addView(textView);
            }
        }

        // Resize the image of available seat
        @SuppressLint("UseCompatLoadingForDrawables") Drawable availableImage = getResources().getDrawable(R.drawable.seat_available);
        Bitmap bitmap = ((BitmapDrawable) availableImage).getBitmap();
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 50, true));
        // Set the drawable Image programmatically for textview
        available.setCompoundDrawablesWithIntrinsicBounds(d,null,null,null);
        available.setText("Seat is Available");

        // Resize the image of unavailable seat
        @SuppressLint("UseCompatLoadingForDrawables") Drawable unavailableImage = getResources().getDrawable(R.drawable.seat_unavailable);
        Bitmap bitmap2 = ((BitmapDrawable) unavailableImage).getBitmap();
        Drawable d2 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap2, 50, 50, true));
        // Set the drawable Image programmatically for textview
        unAvailable.setCompoundDrawablesWithIntrinsicBounds(d2,null,null,null);
        unAvailable.setText("Seat is Unavailable");

        // Resize the image of selecting seat
        @SuppressLint("UseCompatLoadingForDrawables") Drawable selectingImage = getResources().getDrawable(R.drawable.seat_selecting2);
        Bitmap bitmap3 = ((BitmapDrawable) selectingImage).getBitmap();
        Drawable d3 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap3, 50, 50, true));
        // Set the drawable Image programmatically for textview
        selecting.setCompoundDrawablesWithIntrinsicBounds(d3,null,null,null);
        selecting.setText("Seat is Selecting");

        // Resize the image of reserved seat
        @SuppressLint("UseCompatLoadingForDrawables") Drawable reservedImage = getResources().getDrawable(R.drawable.seat_reserved);
        Bitmap bitmap4 = ((BitmapDrawable) reservedImage).getBitmap();
        Drawable d4 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap4, 50, 50, true));
        // Set the drawable Image programmatically for textview
        reserved.setCompoundDrawablesWithIntrinsicBounds(d4,null,null,null);
        reserved.setText("Seat is Reserved");


        return view;
    }

    /*
     *Function to check the seat that the user chose is whether available,unavailable or reserved
     * */
    @SuppressLint("ResourceAsColor")
    private void chooseSeat (TextView view){
        if ((int) view.getTag() == SEAT_IS_AVAILABLE) {
            if (seatChosen.contains(view.getId() + ",")) {
                seatChosen = seatChosen.replace(+view.getId() + ",", "");
                view.setBackgroundResource(R.drawable.seat_available);
                view.setTextColor(Color.WHITE);
                // Announce the user the seat is unselected
                Toast.makeText(getContext(), "Seat " + view.getId() + " is Unselected", Toast.LENGTH_SHORT).show();
                chooseSeat = false;
                // disable the next button
                if(chooseSeat){
                    next3.setEnabled(true);
                }
                else{
                    next3.setEnabled(false);
                }

            } else {
                seatChosen = seatChosen + view.getId() + ",";
                view.setBackgroundResource(R.drawable.seat_selecting2);
                view.setTextColor(Color.rgb(222,22,25));
                System.out.println("huy ne" + seatChosen);
                // Announce the user the seat is selecting
                Toast.makeText(getContext(), "Seat " + view.getId() + " is Selecting", Toast.LENGTH_SHORT).show();
                chooseSeat = true;
                // enable the next button
                if(chooseSeat){
                    next3.setEnabled(true);
                }
                else{
                    next3.setEnabled(false);
                }
            }
        } else if ((int) view.getTag() == SEAT_IS_UNAVAILABLE) {
            // Announce the user the seat is unavailable
            Toast.makeText(getContext(), "Seat " + view.getId() + " is Unavailable", Toast.LENGTH_SHORT).show();
        } else if ((int) view.getTag() == SEAT_IS_RESERVED) {
            // Announce the user the seat is reserved
            Toast.makeText(getContext(), "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
        }
    }
}