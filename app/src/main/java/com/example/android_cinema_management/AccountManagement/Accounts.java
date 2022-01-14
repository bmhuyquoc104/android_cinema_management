package com.example.android_cinema_management.AccountManagement;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.Adapter.HomeAdapter;
import com.example.android_cinema_management.Adapter.LogInAdapter;
import com.example.android_cinema_management.MovieManagement.MovieInfoTabLayout;
import com.example.android_cinema_management.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.Calendar;
import java.util.Objects;


public class Accounts extends Fragment {
    //Declare adapter and button
    Button register, signIn;
    LogInAdapter adapter;

    //    Declare Textviews
    TextView paymentPolicy, securityPolicy, privacyPolicy, termsAndConditions;
    TextView email, phone, address;

//    Initialize spanned
//    For policies
    Spanned payment = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
            .setHtml("<h1>Payment Policy</h1>" +
                    "<h3>1. Payment Regulation</h3>" +
                    "<p>Customers can choose the following payment methods for the " +
                    "online booking transaction on the Universal website:</p>" +
                    "<p>- Member Reward Points</p>" +
                    "<p>- Universal Gift Card</p>" +
                    "<p>- Ticket Voucher</p>" +
                    "<p>- ATM Card (debit / payment / prepaid cards)</p>" +
                    "<p>- Credit card, debit card, international prepaid card</p>" +
                    "<h3>2. Online Payment Methods</h3>" +
                    "<p>- Membership Point: 01 point is equivalent to 1.000 VND. With these reward points, you can simply pay for all products and services available at Universal similarly to using cash. Please give your membership card to our staff at box office to complete payment using points, or choose to pay with points when booking online. You must use 20 points at minimum for any purchase. To easily check your reward points and transaction history, please visit My Universal on website.</p>" +
                    "<p>- Universal Gift Card: Giftcards can be used to buy movie tickets, concession items at all Universal Cinemas and also applies for online booking. You can buy giftcards at any Universal Cinema with the minimum balance of 300,000VND, with a one-year validity. You can choose from three options when buying giftcards: 300,000 VND, 500,000 VND or 1,000,000VND. Giftcards can be topped up to continue using.</p>" +
                    "<p>- ATM Cards (domestic debit / payment / prepaid cards): To make online payment using domestic ATM card, the card must be registered for internet banking by the card issuer. The transaction must be successfully acknowledged by the returned licensing notice of the payment gateway system (guarantee of balance / limit and customer authentication in accordance with the terms of use of the card).</p>" +
                    "<h3>3. Accepted cards for online payment</h3>" +
                    "<p>- Visa- MasterCard- JCB- American Express- Union Pay</p>"));
    Spanned security = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
            .setHtml("<h1>Security Policy</h1>" +
                    "<h3>1. Guideline for adjusting information</h3>" +
                    "<p>In order to adjust personal information, customers may log-in and adjust information by themselves, except for Full name, Gender, Birthday and ID Number.</p>" +
                    "<h3>2. Universal commits:</h3>" +
                    "<p>All personal information of client collected from the website www.cgv.vn shall be stored carefully; only the client may log-in its account by account’s name and password.</p>" +
                    "<p>In cases where the information server is being attacked by the hackers, resulting the loss of client’s personal information and adversely affecting the client, Universal will immediately inform the client and submit the case to the appropriate authority for investigation.</p>"));
    Spanned privacy = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
            .setHtml("<h1>Privacy Policy</h1>" +
                    "<h3>1. The purpose and scope of collecting information</h3>" +
                    "<p>To avoid any doubt, during payment process on application, Universal only stores the information of the purchase order that has been paid by customer, bank account information shall not be stored</p>" +
                    "<h3>2. The scope of using information<h3>" +
                    "<p>Customer hereby understands and agrees that Universal is obligated to provide customer’s information upon request/ decision of a competent authorities and/or applicable Law. Universal shall not be responsible for confidentiality obligation in this case</p>" +
                    "<h3>3. Time</h3>" +
                    "<p>Customer data shall be stored from the date of registered until the customer requires Universal to delete information. For closed accounts, Universal still keeps customer personal information for fraud prevention, investigation, troubleshooting etc. This information will be stored in Universal’s servers up to twelve (12) months, after that Universal will permanently delete customer personal information.</p>"));
    Spanned tAndC = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
            .setHtml("<h1>Conditions of Website Use</h1>" +
                    "<p>By entering this website, you acknowledge and agree to the Terms of Use.If you do not agree to the following terms, do not use this website.</p>" +
                    "<h3>1. Entry at your own Risk:</h3>" +
                    "<p>By entering this site you acknowledge and agree that you are doing so at your own risk and that Universal and affiliated parties responsible for the production of this website shall not be liable for any damages including any direct, special, incidental, consequential, punitive or any losses, costs or expenses of any kind which may arise, directly or indirectly, through the use of this site or through the download of any materials, including but not limited to anything caused by any viruses, action or inaction of any computer system, phone line, hardware, software or program malfunctions, or any other errors, failures or delays in computer transmissions or network connections</p>" +
                    "<h3>2. Site Content:</h3>" +
                    "<p>The information made available through this web site is provided “as is” and without warranties of any kind. Universal and its affiliates do not warrant or make any representations regarding the use or the results of use of the materials in this web site in terms of their correctness, accuracy, reliability, or otherwise. This content is provided as a service for public benefit and solely intended for non-commercial use. Commercial usage of this content is not authorized without prior written consent by Universal. Although Universal endeavours to ensure that all content provided in this website is up-to-date, we do not warrant that said information is current, accurate or complete. All content is subject to change at any time.</p>" +
                    "<h3>3. Copyright:</h3>" +
                    "<p>All content of the Universal information services is protected by copyright with all rights reserved. All rights to the pages, site content and arrangement are owned by Universal. Copying, modifying, displaying, distributing, transmitting, redelivering through the use of \"framing\" technology, publishing, selling, licensing, creating derivative works or using any content for any purpose without the prior written approval from Universal is strictly prohibited</p>"));

//    For contact
    Spanned emailSpanned = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
            .setHtml("<h1>Email</h1>" +
                    "<p>universal.cine@gmail.com.vn</p>"));
    Spanned phoneSpanned = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
            .setHtml("<h1>Phone</h1>" +
                    "<p>Hotline: 1900 6017</p>" +
                    "<p>Media Sale: +84-28-3636 57 57</p>" +
                    "<p>PR Department:+84-28-3636-5757</p>" +
                    "<p>Marketing Department:+84-28-3636 57 57</p>"));
    Spanned addressSpanned = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
            .setHtml("<h1>Address</h1>" +
                    "<p>Floor 2, Rivera Park Saigon - No. 7/28 Thanh Thai street, Ward 14, District 10, HCMC.</p>"));
    public Accounts() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);
        register = view.findViewById(R.id.ac_register);
        signIn = view.findViewById(R.id.ac_signIn);
        // Initialize fragment manager
        FragmentManager fm = getParentFragmentManager();
        // Initialize adapter
        adapter = new LogInAdapter(fm,getLifecycle());
        // Function click change to another fragment
        register.setOnClickListener(View ->{
            Intent intent = new Intent(getContext(),SignUp.class);
            //Send intent to sign Up activity

            // Start intent
            try {
                startActivity(intent);
            }
            // Exception if activity is not found
            catch (ActivityNotFoundException e){
                Toast.makeText(getContext(),"Oops!! Something wrong, Please try again!" ,Toast.LENGTH_LONG).show();
            }
        });

        signIn.setOnClickListener(View ->{
            FragmentTransaction transaction =
                    fm.beginTransaction();
            transaction.replace(R.id.ma_container, new SignInFragment()).commit();
        });

//        Declare the policies
        paymentPolicy = view.findViewById(R.id.ac_payment_policy);
        paymentPolicy.setOnClickListener(View -> {
            openPolicyDialog(paymentPolicy.getText().toString());
        });

        securityPolicy = view.findViewById(R.id.ac_security_policy);
        securityPolicy.setOnClickListener(View -> {
            openPolicyDialog(securityPolicy.getText().toString());
        });

        privacyPolicy = view.findViewById(R.id.ac_privacy_policy);
        privacyPolicy.setOnClickListener(View -> {
            openPolicyDialog(privacyPolicy.getText().toString());
        });

        termsAndConditions = view.findViewById(R.id.ac_terms_and_conditions);
        termsAndConditions.setOnClickListener(View -> {
            openPolicyDialog(termsAndConditions.getText().toString());
        });

//        Company's info
        email = view.findViewById(R.id.ac_email);
        email.setOnClickListener(View -> {
            openPolicyDialog(email.getText().toString());
        });

        phone = view.findViewById(R.id.ac_phone);
        phone.setOnClickListener(View -> {
            openPolicyDialog(phone.getText().toString());
        });

        address = view.findViewById(R.id.ac_address);
        address.setOnClickListener(View -> {
            openPolicyDialog(address.getText().toString());
        });

        return view;
    }

    private void openPolicyDialog(String policy) {
        // Initialize new dialog
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Set content for dialog
        dialog.setContentView(R.layout.open_policy_dialog);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        // set the dialog to top
        windowAttributes.gravity = Gravity.TOP;
        window.setAttributes(windowAttributes);
        // Disable cancel by clicking randomly on the screen
        dialog.setCancelable(false);

        dialog.show();
//        Initialize TextViews
        TextView policyHeader = dialog.findViewById(R.id.policy_header);
        HtmlTextView policyContent = dialog.findViewById(R.id.policy_content);

        policyHeader.setText(policy);
        switch (policy) {
            case "Payment Policy":
                policyContent.setText(payment);
                break;
            case "Security Policy":
                policyContent.setText(security);
                break;
            case "Privacy Policy":
                policyContent.setText(privacy);
                break;
            case "Terms and conditions":
                policyContent.setText(tAndC);
                break;
            case "Email":
                policyContent.setText(emailSpanned);
                break;
            case "Phone":
                policyContent.setText(phoneSpanned);
                break;
            case "Address":
                policyContent.setText(addressSpanned);
                break;
        }

//        Set spanned text on textview


        ImageButton close = dialog.findViewById(R.id.policy_close_button);
        /*
         * Function to dismiss the dialog
         * */
        close.setOnClickListener(View -> {
            dialog.dismiss();
        });
    }
}