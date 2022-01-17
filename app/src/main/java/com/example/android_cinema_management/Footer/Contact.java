package com.example.android_cinema_management.Footer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_cinema_management.R;

public class Contact extends AppCompatActivity {

    private String headOfficeContent = "Floor 2, Rivera Park Saigon - No. 7/28\n" +
            "Thanh Thai street, Ward 14, District 10, Ho Chi Minh City";

    private String customerServiceContent = "Hotline: 1900 6017\n" +
            "Working hours: 8:00 - 22:00\n" +
            "Monday to Sunday, including Public Holidays\n" +
            "Email support: hoidap@universal.vn";

    private String advertisingContent = "Media Sale: +84-28-3636 57 57\n" +
            "Ext: 273 (Mr. Huy)\n" +
            "Hotline: 0981005390\n" +
            "Email: ad.universal@uni.net";

    private String partnershipSponsorContent = "PR Department:+84-28-3636-5757\n" +
            "Ext: 278 (Mr. Kendrick)\n" +
            "Email: ken.dong@universal.net";

    private TextView HeadOfficeContent, CustomerServiceContent, AdvertiseContent, PartnershipSponsorContent;
    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        HeadOfficeContent = findViewById(R.id.headOfficeContent);
        CustomerServiceContent = findViewById(R.id.customerServiceContent);
        AdvertiseContent = findViewById(R.id.advertiseContent);
        PartnershipSponsorContent = findViewById(R.id.partnershipSponsorContent);

        HeadOfficeContent.setText(headOfficeContent);
        CustomerServiceContent.setText(customerServiceContent);
        AdvertiseContent.setText(advertisingContent);
        PartnershipSponsorContent.setText(partnershipSponsorContent);

        Back = findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){

    };
}