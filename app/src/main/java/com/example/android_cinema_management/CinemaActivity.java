package com.example.android_cinema_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class CinemaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CinemaAdapter cinemaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerCinemaView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Cinema> options = new FirebaseRecyclerOptions.Builder<Cinema>().setQuery(FirebaseDatabase.getInstance().getReference().child("Cinema"),Cinema.class).build();

        cinemaAdapter = new CinemaAdapter(options);
        recyclerView.setAdapter(cinemaAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cinemaAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        cinemaAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtSearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<Cinema> options = new FirebaseRecyclerOptions.Builder<Cinema>().setQuery(FirebaseDatabase.getInstance().getReference().child("Cinema").orderByChild("name").startAt(str).endAt(str+"~"),Cinema.class).build();
        cinemaAdapter = new CinemaAdapter(options);
        cinemaAdapter.startListening();
        recyclerView.setAdapter(cinemaAdapter);
    }
}