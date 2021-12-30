package com.example.android_cinema_management.SearchItemManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android_cinema_management.Adapter.CinemaAdapter;
import com.example.android_cinema_management.CinemaManagement.CinemaFragment;
import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.R;

import java.util.ArrayList;


public class SearchFilterByCinemaFragment extends Fragment {
    //Declare editText, button,textview
    EditText searchCinema;
    Button buttonFilter;
    TextView result;
    ArrayList<Cinema> searchList, cinemaList;
    //Declare recyclerview
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //Declare adapter
    private CinemaAdapter cinemaAdapter;
    public SearchFilterByCinemaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\
        View view = inflater.inflate(R.layout.fragment_search_filter_by_cinema_fragement1, container, false);
        //Binding to xml value
        searchCinema = view.findViewById(R.id.frag1SearchCinemaSearchBar);
        buttonFilter = view.findViewById(R.id.searchByCinemaFilters);
        result = view.findViewById(R.id.searchByCinemaResult);
        recyclerView = view.findViewById(R.id.searchByCinemaRecyclerView);
        recyclerView.setHasFixedSize(true);
        //Instantiate array list
        searchList = new ArrayList<>();
        cinemaList = CinemaFragment.cinemaArrayList;
        System.out.println("huy ne" + cinemaList);
        //Use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());

        // Instantiate adapter
        cinemaAdapter = new CinemaAdapter(getContext(), cinemaList);
        // Set layout for recycler view
        recyclerView.setLayoutManager(layoutManager);
        // Set adapter for recycler view
        recyclerView.setAdapter(cinemaAdapter);
        searchCinema.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String inputText = searchCinema.getText().toString();
                searchList = new ArrayList<>();
                searchCinemaByAllAttributes(cinemaList,searchList,inputText);
                // Instantiate adapter
                cinemaAdapter = new CinemaAdapter(getContext(), searchList);
                // Set layout for recycler view
                recyclerView.setLayoutManager(layoutManager);
                // Set adapter for recycler view
                recyclerView.setAdapter(cinemaAdapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    private void searchCinemaByAllAttributes(ArrayList<Cinema>cinemaList, ArrayList<Cinema>searchList,String keyword){
        for (Cinema cinema: cinemaList){
            if (cinema.getCity().trim().toLowerCase().contains(keyword.trim().toLowerCase()) ||
                    cinema.getAddress().trim().toLowerCase().contains(keyword.trim().toLowerCase()) ||
                    cinema.getContactNumber().trim().toLowerCase().contains(keyword.trim().toLowerCase()) ||
                    cinema.getLocationName().trim().toLowerCase().contains(keyword.trim().toLowerCase()) ||
                    cinema.getName().trim().toLowerCase().contains(keyword.trim().toLowerCase())){
                searchList.add(cinema);
            }
            else{
                
            }
          }
        }
    }

