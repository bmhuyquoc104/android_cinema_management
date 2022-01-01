package com.example.android_cinema_management;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.android_cinema_management.Adapter.DiscountAdapter;
import com.example.android_cinema_management.Model.Discount;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class DiscountAndTicket extends Fragment {
    private RecyclerView Discount;
    private DiscountAdapter discountAdapter;
    private TextInputLayout cinemaMenu, monthMenu;
    private AutoCompleteTextView cinemaACT, monthACT;

    public DiscountAndTicket() {
        // Required empty public constructor
    }

    public static DiscountAndTicket newInstance(String param1, String param2) {
        DiscountAndTicket fragment = new DiscountAndTicket();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        For viewing the discounts
        View view = inflater.inflate(R.layout.fragment_discount_and_ticket, container, false);
        Discount = view.findViewById(R.id.discount);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        Discount.setLayoutManager(gridLayoutManager);

        discountAdapter = new DiscountAdapter(getContext(), getListDiscount());
        Discount.setAdapter(discountAdapter);

//        Cinema drop-down menu
        cinemaMenu = view.findViewById(R.id.cinema);
        cinemaACT = view.findViewById(R.id.cinemaItems);

        String[] cinemas = {"Cinema1", "Cinema2", "Cinema3"};
        ArrayAdapter<String> cinemaAdapter = new ArrayAdapter<>(getContext(), R.layout.cinemas_list, cinemas);
        cinemaACT.setAdapter(cinemaAdapter);
        cinemaACT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), (String)parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private List<com.example.android_cinema_management.Model.Discount> getListDiscount() {
//        Add data(objects) to be displayed
        List<Discount> list = new ArrayList<>();
        list.add(new Discount("Black Friday", "23/11/21-26/11/21"));
        list.add(new Discount("Anniversary", "19/11/21-21/11/21"));
        list.add(new Discount("Big Sale", "2/2/22-4/2/22"));
        list.add(new Discount("New Year", "1/1/22"));
        list.add(new Discount("Big Sale", "2/2/22-4/2/22"));

        return list;
    }

//    Clear the data on destroy
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (discountAdapter != null) {
            discountAdapter.release();
        }
    }
}