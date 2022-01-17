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

import com.example.android_cinema_management.Model.Discount;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.TicketAndDiscountManagement.DiscountDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder>{

    //Declare List<Discount> and Context
    private List<Discount> mListDiscount;
    private Context mContext;

    public DiscountAdapter(Context context, List<Discount> mListDiscount) {
        this.mContext = context;
        this.mListDiscount = mListDiscount;
    }

    @NonNull
    @Override
    public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_row_of_discount, parent, false);
        return new DiscountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {
        Discount discount = mListDiscount.get(position);
        if (discount == null) {
            return;
        }

//        Set content for the discounts
        holder.tvName.setText(discount.getName());
        holder.tvDate.setText(discount.getMonth());
        Picasso.get().load(discount.getImage()).into(holder.ivDiscount);

        holder.DiscountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(discount);
            }
        });
    }

//    Go to the detail of a discount
    private void onClickGoToDetail(Discount discount) {
        Intent intent = new Intent(mContext, DiscountDetails.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_discount", discount);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (mListDiscount != null) {
            return mListDiscount.size();
        }
        return 0;
    }

//    Release the data after work is done
    public void release() {
        mContext = null;
    }

    public class DiscountViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvDate;
        private ImageView ivDiscount;

        private ConstraintLayout DiscountLayout;

        public DiscountViewHolder(@NonNull View itemView) {
            super(itemView);
            DiscountLayout = itemView.findViewById(R.id.discountLayout);

            tvName = itemView.findViewById(R.id.name);
            tvDate = itemView.findViewById(R.id.date);

            ivDiscount = itemView.findViewById(R.id.discount);
        }
    }
}
