package com.example.android_cinema_management.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.android_cinema_management.Model.Voucher;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.UserManagement.VoucherActivity;
import com.example.android_cinema_management.database.VoucherDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;

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
    //Declare progress dialog
    ProgressDialog pd;
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

        Spanned successMessage = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
                .setHtml(
                        "<h3> You have successfully exchange this voucher. </p>" +
                                "<p>Please come to the nearest branch with your <strong > UniCard and ID </strong> to receive " +
                                "the voucher.</p>" +
                                "<h3> Thank you for using our service! </h3>"));

        Spanned errorMessage = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
                .setHtml(
                        "<h3> You don't have enough points to exchange this voucher. </p>" +
                                "<p>Please try another voucher or come back later.</p>" +
                                "<h3> Thank you for using our service! </h3>"));



        pd = new ProgressDialog(context);
        String priceFormat = formatter.format(Double.parseDouble(voucherList.get(position).getVoucherPrice()));

        holder.price.setText("Price: "+ priceFormat + " VNĐ");
        holder.pointRequire.setText("Point required: " +voucherList.get(position).getPointRequire());
        holder.name.setText("Name: " +voucherList.get(position).getVoucherName());

        holder.exchange.setOnClickListener(View ->{
            // Get the points from the database
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
                            // Check if the user still have enough point to make an exchange
                            if(Integer.parseInt(totalPoints) >= Integer.parseInt(voucherList.get(position).getPointRequire())){
                                int remainPoints = Integer.parseInt(totalPoints) - Integer.parseInt(voucherList.get(position).getPointRequire());
                                totalPoints = Integer.toString(remainPoints);
                                // Update the text view total points in voucher activity in real time
                                VoucherActivity.updateTotalPoints(VoucherActivity.point,totalPoints);
                                VoucherActivity.currentPoint = totalPoints;
                                // Update the new point to database
                                db.collection("Users").document(mUser.getUid()).update("point", totalPoints);
                                VoucherDatabase.postUserVoucher(pd,db,context,firebaseAuth,mUser,
                                        voucherList.get(position).getVoucherPrice(),voucherList.get(position).getVoucherName(),
                                        voucherList.get(position).getPointRequire());
                                openSuccessfulDialog(R.raw.exchange_success,successMessage,context);

                            }
                            else{
                                openSuccessfulDialog(R.raw.warning,errorMessage,context);
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

    @SuppressLint("SetTextI18n")
    public static void openSuccessfulDialog(int raw, Spanned dialogMessage, Context context) {
        // Initialize new dialog
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Set content for dialog
        dialog.setContentView(R.layout.successful_dialog);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        // set the dialog to top
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        // Disable cancel by clicking randomly on the screen
        dialog.setCancelable(false);

        dialog.show();

        TextView message = dialog.findViewById(R.id.successfullyMessage);
        LottieAnimationView view = dialog.findViewById(R.id.animationView);
        view.setAnimation(raw);
        message.setText(dialogMessage);
        ImageView close = dialog.findViewById(R.id.successful_dialog_close);
        /*
         * Function to dismiss the dialog
         * */
        close.setOnClickListener(View -> {
            dialog.dismiss();
        });

    }
}
