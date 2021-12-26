package com.example.android_cinema_management.Footer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_cinema_management.R;

public class CopyRight extends AppCompatActivity {

    private String companyContent = "Business registration certificate: 0303675393, registered" +
            " for the first time on 31/7/2008, registered for the fifth change on 14/10/2015, " +
            "issued by HCMC Department of Planning and Investment.\n" +
            "Address: Floor 2, Rivera Park Saigon - No. 7/28 Thanh Thai street, Ward 14, " +
            "District 10, HCMC.\n" +
            "Hotline: 1900 6017\n" +
            "COPYRIGHT 2021 CJ UNIVERSAL. All RIGHTS RESERVED .";

    private TextView CompanyContent;
    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_right);

        CompanyContent = findViewById(R.id.companyContent);
        CompanyContent.setText(companyContent);

        Back = findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}