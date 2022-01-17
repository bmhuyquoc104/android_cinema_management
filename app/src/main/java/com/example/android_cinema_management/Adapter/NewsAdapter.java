package com.example.android_cinema_management.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.News;
import com.example.android_cinema_management.NewsDetails;
import com.example.android_cinema_management.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private List<News> mListNews;
    private Context mContext;

    public NewsAdapter(Context context, List<News> mListNews) {
        this.mContext = context;
        this.mListNews = mListNews;
    }

//    Inflate the layout
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
//        Get the news object
        News news = mListNews.get(position);
        if (news == null) {
            return;
        }

//        Set content for the discounts
        holder.tvName.setText(news.getName());
        holder.tvDate.setText(news.getMonth());
        Picasso.get().load(news.getImage()).into(holder.ivNews);

        holder.NewsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(news);
            }
        });

    }

//    Go to the detail of a news
    private void onClickGoToDetail(News news) {
        Intent intent = new Intent(mContext, NewsDetails.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_news", news);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (mListNews != null) {
            return mListNews.size();
        }
        return 0;
    }

    //    Release the data after work is done
    public void release() {
        mContext = null;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
//        Declaring the views
        private TextView tvName, tvDate;
        private ImageView ivNews;

        private ConstraintLayout NewsLayout;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            NewsLayout = itemView.findViewById(R.id.newsLayout);

            tvName = itemView.findViewById(R.id.name);
            tvDate = itemView.findViewById(R.id.date);

            ivNews = itemView.findViewById(R.id.news);
        }
    }
}
