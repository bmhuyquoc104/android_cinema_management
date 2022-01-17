package com.example.android_cinema_management.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.example.android_cinema_management.UserManagement.AdminManagment.UpdateAndDeleteVoucher;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VoucherAdminAdapter extends RecyclerView.Adapter<VoucherAdminAdapter.MyViewHolder>{

    Context context;

    ArrayList<Voucher> voucherArrayList;

    public VoucherAdminAdapter(Context context, ArrayList<Voucher> voucherArrayList) {
        this.context = context;
        this.voucherArrayList = voucherArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_row_of_voucher_admin,parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.voucherName.setText("Voucher name: " + voucherArrayList.get(position).getName());
        holder.pointRequired.setText("Point cost: " +voucherArrayList.get(position).getPointRequired());
        holder.voucherPrice.setText("Actual Price: " + voucherArrayList.get(position).getPrice());
        Picasso.get().load(voucherArrayList.get(position).getImage()).into(holder.voucherImage);
    }

    @Override
    public int getItemCount() {
        return voucherArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView voucherName, pointRequired, voucherPrice;
        ImageView voucherImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            voucherName = itemView.findViewById(R.id.voucher_name_admin);
            pointRequired = itemView.findViewById(R.id.voucher_point_required_admin);
            voucherPrice = itemView.findViewById(R.id.voucher_price_admin);
            voucherImage = itemView.findViewById(R.id.voucher_image_admin);

            itemView.setOnClickListener(view -> {
                Voucher voucher = voucherArrayList.get(getAbsoluteAdapterPosition());
                Intent intent = new Intent(context, UpdateAndDeleteVoucher.class);
                intent.putExtra("voucher",voucher);
                context.startActivity(intent);
            });
        }
    }
}
