package com.example.android_cinema_management;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android_cinema_management.Adapter.CinemaAdapter;
import com.example.android_cinema_management.Model.Cinema;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.FirestoreGrpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class SearchCinema extends Fragment {

    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private CinemaAdapter cinemaAdapter;
    private List<Cinema> list;
    EditText searchCinema;
    String str;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ViewGroup onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_cinema, container, false);

        recyclerView = view.findViewById(R.id.recycler_cinema_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        searchCinema = (EditText) view.findViewById(R.id.editText_searchCinema);

        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        cinemaAdapter = new CinemaAdapter(this, list);
        recyclerView.setAdapter(cinemaAdapter);

        searchCinema.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                str = searchCinema.getText().toString();
                SearchData(str);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        showData();


        return view;
    }

    private void showData(){

        db.collection("Cinema").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for (DocumentSnapshot snapshot: task.getResult()){
                            Cinema cinema = new Cinema(
                                    snapshot.getString("id"),
                                    snapshot.getString("name"),
                                    snapshot.getString("address"),
                                    snapshot.getString("latitude"),
                                    snapshot.getString("longitude"),
                                    snapshot.getString("contactNumber"),
                                    snapshot.getString("imageURL"),
                                    snapshot.getString("locationName"));
                            list.add(cinema);
                        }
                        cinemaAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void SearchData(String str){
        db.collection("Cinema").whereEqualTo("name", str).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for (DocumentSnapshot doc: Objects.requireNonNull(task.getResult())){
                            Cinema cinema = new Cinema(
                                    doc.getString("id"),
                                    doc.getString("name"),
                                    doc.getString("address"),
                                    doc.getString("latitude"),
                                    doc.getString("longitude"),
                                    doc.getString("contactNumber"),
                                    doc.getString("imageURL"),
                                    doc.getString("loactionName"));
                            list.add(cinema);
                        }
                        System.out.println(list.toString());
                        cinemaAdapter = new CinemaAdapter(SearchCinema.this, list);
                        recyclerView.setAdapter(cinemaAdapter);
//                        cinemaAdapter.notifyDataSetChanged();
                    }
                });
    }
}