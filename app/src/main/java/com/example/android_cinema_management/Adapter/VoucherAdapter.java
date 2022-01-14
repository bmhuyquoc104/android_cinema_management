package com.example.android_cinema_management.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_cinema_management.Model.Voucher;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.VoucherActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
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
    //Declare Firebase services
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser mUser = firebaseAuth.getCurrentUser();
    String userId = mUser.getUid();
    //Declare string
    String totalPoints = "";
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
    public void onBindViewHolder(@NonNull VoucherAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String priceFormat = formatter.format(Double.parseDouble(voucherList.get(position).getVoucherPrice()));

        holder.price.setText("Price: "+ priceFormat + " VNĐ");
        holder.pointRequire.setText("Point required: " +voucherList.get(position).getPointRequire());
        holder.name.setText("Name: " +voucherList.get(position).getVoucherName());
//        holder.exchange.setEnabled(false);


        holder.exchange.setOnClickListener(View ->{
            DocumentReference docRef = db.collection("Users").document(userId);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot docSnap = task.getResult();
                        if (docSnap != null){
                            totalPoints= docSnap.getString("point");
                            //Function to only disable and enable the button by the total points
                            assert totalPoints != null;
                            if(Integer.parseInt(totalPoints) >= Integer.parseInt(voucherList.get(position).getPointRequire())){
                                int remainPoints = Integer.parseInt(totalPoints) - Integer.parseInt(voucherList.get(position).getPointRequire());
                                totalPoints = Integer.toString(remainPoints);
                                VoucherActivity.updateTotalPoints(VoucherActivity.point,totalPoints);
                                VoucherActivity.currentPoint = totalPoints;
                                System.out.println(VoucherActivity.currentPoint);
                                System.out.println("TotalPoints: "+ remainPoints);
                                db.collection("Users").document(mUser.getUid()).update("point", totalPoints);
                                Toast.makeText(context,"You have successfully get this voucher!. Your remain points are: " +totalPoints,Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(context,"You don't have enough points to exchange this voucher!",Toast.LENGTH_SHORT).show();
                            }


                        }
                    }
                }
            });





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
