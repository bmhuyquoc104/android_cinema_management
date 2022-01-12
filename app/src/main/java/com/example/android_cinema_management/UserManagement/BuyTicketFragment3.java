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
    ViewGroup layout;

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
//            + "UU_AAAAAAAUUUU_RR/"
//            + "RR_AAAAAAAAAAA_AA/"
//            + "AA_UUAAAAAUUUU_AA/"
//            + "AA_AAAAAAUUUUU_AA/"
            + "_________________/";

    List<TextView> seatViewList = new ArrayList<>();
    int seatSize = 100;
    int seatGaping = 10;

    int STATUS_AVAILABLE = 1;
    int STATUS_UNAVAILABLE = 2;
    int STATUS_RESERVED = 3;
    String selectedIds = "";

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
        // Function to change fragment and send data to next step in buying ticket

        next3.setEnabled(false);
        next3.setOnClickListener(View -> {
            Bundle bundle = new Bundle();
            bundle.putString("seat", selectedIds);
            BuyTicketFragment4 fragment4 = new BuyTicketFragment4();
            fragment4.setArguments(bundle);
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction transaction =
                    fm.beginTransaction();
            transaction.replace(R.id.buy_ticket_frame_layout, fragment4).addToBackStack("fragment4").commit();

        });

        layout = view.findViewById(R.id.layoutSeat);

        seats = "/" + seats;

        LinearLayout layoutSeat = new LinearLayout(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);
        layout.addView(layoutSeat);

        LinearLayout layout = null;

        int count = 0;

        for (int index = 0; index < seats.length(); index++) {
            if (seats.charAt(index) == '/') {
                layout = new LinearLayout(getContext());
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layoutSeat.addView(layout);
            } else if (seats.charAt(index) == 'U') {
                count++;
                TextView textView = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                textView.setLayoutParams(layoutParams);
                textView.setPadding(0, 0, 0, 2 * seatGaping);
                textView.setId(count);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.seat_unavailable);
                textView.setTextColor(Color.BLACK);
                textView.setTag(STATUS_UNAVAILABLE);
                textView.setText(count + "");
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11);
                layout.addView(textView);
                seatViewList.add(textView);
                textView.setOnClickListener(View -> {
                    chooseSeat(textView);
                });
            } else if (seats.charAt(index) == 'A') {
                count++;
                TextView textView = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                textView.setLayoutParams(layoutParams);
                textView.setPadding(0, 0, 0, 2 * seatGaping);
                textView.setId(count);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.seat_available);
                textView.setText(count + "");
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11);
                textView.setTextColor(Color.WHITE);
                textView.setTag(STATUS_AVAILABLE);
                layout.addView(textView);
                seatViewList.add(textView);
                textView.setOnClickListener(View -> {
                    chooseSeat(textView);
                });
            } else if (seats.charAt(index) == 'R') {
                count++;
                TextView textView = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                textView.setLayoutParams(layoutParams);
                textView.setPadding(0, 0, 0, 2 * seatGaping);
                textView.setId(count);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.seat_reserved);
                textView.setText(count + "");
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11);
                textView.setTextColor(Color.BLACK);
                textView.setTag(STATUS_RESERVED);
                layout.addView(textView);
                seatViewList.add(textView);
                textView.setOnClickListener(View -> {
                    chooseSeat(textView);
                });
            } else if (seats.charAt(index) == '_') {
                TextView textView = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                textView.setLayoutParams(layoutParams);
                textView.setBackgroundColor(Color.TRANSPARENT);
                textView.setText("");
                layout.addView(textView);
            }
        }


        @SuppressLint("UseCompatLoadingForDrawables") Drawable availableImage = getResources().getDrawable(R.drawable.seat_available);
        Bitmap bitmap = ((BitmapDrawable) availableImage).getBitmap();
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 50, true));
        available.setCompoundDrawablesWithIntrinsicBounds(d,null,null,null);
        available.setText("Seat is Available");

        @SuppressLint("UseCompatLoadingForDrawables") Drawable unavailableImage = getResources().getDrawable(R.drawable.seat_unavailable);
        Bitmap bitmap2 = ((BitmapDrawable) unavailableImage).getBitmap();
        Drawable d2 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap2, 50, 50, true));
        unAvailable.setCompoundDrawablesWithIntrinsicBounds(d2,null,null,null);
        unAvailable.setText("Seat is Unavailable");

        @SuppressLint("UseCompatLoadingForDrawables") Drawable selectingImage = getResources().getDrawable(R.drawable.seat_selecting2);
        Bitmap bitmap3 = ((BitmapDrawable) selectingImage).getBitmap();
        Drawable d3 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap3, 50, 50, true));
        selecting.setCompoundDrawablesWithIntrinsicBounds(d3,null,null,null);
        selecting.setText("Seat is Selecting");

        @SuppressLint("UseCompatLoadingForDrawables") Drawable reservedImage = getResources().getDrawable(R.drawable.seat_reserved);
        Bitmap bitmap4 = ((BitmapDrawable) reservedImage).getBitmap();
        Drawable d4 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap4, 50, 50, true));
        reserved.setCompoundDrawablesWithIntrinsicBounds(d4,null,null,null);
        reserved.setText("Seat is Reserved");


        return view;
    }

    @SuppressLint("ResourceAsColor")
    private void chooseSeat (TextView view){
        if ((int) view.getTag() == STATUS_AVAILABLE) {
            if (selectedIds.contains(view.getId() + ",")) {
                selectedIds = selectedIds.replace(+view.getId() + ",", "");
                view.setBackgroundResource(R.drawable.seat_available);
                view.setTextColor(Color.WHITE);
                Toast.makeText(getContext(), "Seat " + view.getId() + " is Unselected", Toast.LENGTH_SHORT).show();
                chooseSeat = false;
                if(chooseSeat){
                    next3.setEnabled(true);
                }
                else{
                    next3.setEnabled(false);
                }

            } else {
                selectedIds = selectedIds + view.getId() + ",";
                view.setBackgroundResource(R.drawable.seat_selecting2);
                view.setTextColor(Color.rgb(222,22,25));
                System.out.println("huy ne" + selectedIds);
                Toast.makeText(getContext(), "Seat " + view.getId() + " is Selecting", Toast.LENGTH_SHORT).show();
                chooseSeat = true;
                if(chooseSeat){
                    next3.setEnabled(true);
                }
                else{
                    next3.setEnabled(false);
                }
            }
        } else if ((int) view.getTag() == STATUS_UNAVAILABLE) {
            Toast.makeText(getContext(), "Seat " + view.getId() + " is Unavailable", Toast.LENGTH_SHORT).show();
        } else if ((int) view.getTag() == STATUS_RESERVED) {
            Toast.makeText(getContext(), "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
        }
    }
}
