package com.example.android_cinema_management.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cinema_management.Model.Discount;
import com.example.android_cinema_management.R;

import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder>{

    private List<Discount> mListDiscount;

    public DiscountAdapter(List<Discount> mListDiscount) {
        this.mListDiscount = mListDiscount;
    }

    @NonNull
    @Override
    public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discount, parent, false);
        return new DiscountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {
        Discount discount = mListDiscount.get(position);
        if (discount == null) {
            return;
        }

        holder.tvName.setText(discount.getName());
        holder.tvDate.setText(discount.getDate());
    }

    @Override
    public int getItemCount() {
        if (mListDiscount != null) {
            return mListDiscount.size();
        }
        return 0;
    }

    public class DiscountViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvDate;

        public DiscountViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvDate = itemView.findViewById(R.id.date);
        }
    }
}
