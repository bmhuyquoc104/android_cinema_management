package com.example.android_cinema_management;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.android_cinema_management.AccountManagement.SignUp;
import com.example.android_cinema_management.Adapter.DiscountAdapter;
import com.example.android_cinema_management.Adapter.NewsAdapter;
import com.example.android_cinema_management.Model.Discount;
import com.example.android_cinema_management.Model.News;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class NewsFragment extends Fragment {
    //Declare imageview


    private RecyclerView News;
    private ArrayList<News> newsArrayList;
    private NewsAdapter newsAdapter;
    FirebaseFirestore db;


    public NewsFragment() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

//        Back button


        News = view.findViewById(R.id.newsItems);
        News.setLayoutManager(new LinearLayoutManager(getContext()));

//        News database
        db = FirebaseFirestore.getInstance();
        newsArrayList = new ArrayList<News>();

        newsAdapter = new NewsAdapter(getContext(), newsArrayList);
        EventChangeListener();
        News.setAdapter(newsAdapter);
        return view;
    }

    //    Get the data from Firestore
    private void EventChangeListener() {
        db.collection("News").orderBy("Name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                newsArrayList.add(dc.getDocument().toObject(News.class));
                            }

                            newsAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    //    Clear the data on destroy
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (newsAdapter != null) {
            newsAdapter.release();
        }
    }
}