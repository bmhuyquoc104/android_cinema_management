package com.example.android_cinema_management.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_cinema_management.Model.Voucher;
import com.example.android_cinema_management.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.MyViewHolder>{
    // Initialize array list for voucher
    private ArrayList<Voucher> voucherList;
    // Initialize context
    private Context context;
    //Declare vietnamese currency format
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    public VoucherAdapter(ArrayList<Voucher> voucherList, Context context) {
        this.voucherList = voucherList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_row_of_voucher,parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VoucherAdapter.MyViewHolder holder, int position) {
        String priceFormat = formatter.format(Double.parseDouble(voucherList.get(position).getVoucherPrice()));

        holder.price.setText("Price: "+ priceFormat + " VNĐ");
        holder.pointRequire.setText("Point required: " +voucherList.get(position).getPointRequire());
        holder.name.setText("Name: " +voucherList.get(position).getVoucherName());
        holder.exchange.setOnClickListener(View ->{

        });
        Picasso.get().load(voucherList.get(position).getVoucherImage()).into(holder.voucherImage);
    }

    @Override
    public int getItemCount() {
        return  voucherList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView price,pointRequire,name;
        ImageView voucherImage;
        Button exchange;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.voucher_price);
            pointRequire = itemView.findViewById(R.id.voucher_point_required);
            name = itemView.findViewById(R.id.voucher_name);
            voucherImage = itemView.findViewById(R.id.voucher_image);
            exchange = itemView.findViewById(R.id.voucher_exchange_bt);
        }
    }
}
