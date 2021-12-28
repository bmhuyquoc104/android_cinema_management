package com.example.android_cinema_management;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android_cinema_management.Adapter.CinemaAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class SearchCinema extends Fragment implements AdapterView.OnItemSelectedListener {

    RecyclerView recyclerView;
    CinemaAdapter cinemaAdapter;
    EditText editText_searchCinema;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ViewGroup onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_cinema, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_cinema_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<com.example.android_cinema_management.Model.Cinema> options = new FirebaseRecyclerOptions.Builder<com.example.android_cinema_management.Model.Cinema>().setQuery(FirebaseDatabase.getInstance().getReference().child("Cinema"), com.example.android_cinema_management.Model.Cinema.class).build();

        cinemaAdapter = new CinemaAdapter(options);
        recyclerView.setAdapter(cinemaAdapter);

        editText_searchCinema = (EditText) view.findViewById(R.id.editText_searchCinema);
        editText_searchCinema.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = editText_searchCinema.getText().toString();
                txtSearch(str);
            }
        });

        Spinner spinner = (Spinner) view.findViewById(R.id.cinemaFilter);
        spinner.setOnItemSelectedListener(this);

        List<String> district = new ArrayList<String>();
        district.add("District");
        district.add("1");
        district.add("2");
        district.add("Bao Loc");
        district.add("testing district");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,district);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        cinemaAdapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        cinemaAdapter.stopListening();
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<com.example.android_cinema_management.Model.Cinema> options = new FirebaseRecyclerOptions.Builder<com.example.android_cinema_management.Model.Cinema>().setQuery(FirebaseDatabase.getInstance().getReference().child("Cinema").orderByChild("name").startAt(str).endAt(str+"~"), com.example.android_cinema_management.Model.Cinema.class).build();
        cinemaAdapter = new CinemaAdapter(options);
        cinemaAdapter.startListening();
        recyclerView.setAdapter(cinemaAdapter);
    }

    private void txtFilter(String str){
        FirebaseRecyclerOptions<com.example.android_cinema_management.Model.Cinema> options = new FirebaseRecyclerOptions.Builder<com.example.android_cinema_management.Model.Cinema>().setQuery(FirebaseDatabase.getInstance().getReference().child("Cinema").orderByChild("name").startAt(str).endAt(str+"~"), com.example.android_cinema_management.Model.Cinema.class).build();
        cinemaAdapter = new CinemaAdapter(options);
        cinemaAdapter.startListening();
        recyclerView.setAdapter(cinemaAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        txtFilter(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}