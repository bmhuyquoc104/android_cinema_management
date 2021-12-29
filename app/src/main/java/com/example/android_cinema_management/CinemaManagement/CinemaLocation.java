package com.example.android_cinema_management.CinemaManagement;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_cinema_management.Model.Cinema;
import com.example.android_cinema_management.R;
import com.example.android_cinema_management.databinding.ActivityCinemaLocationBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

public class CinemaLocation extends FragmentActivity implements OnMapReadyCallback {
    // Declare class
    private Cinema currentCinema;
    private GoogleMap mMap;
    private ActivityCinemaLocationBinding binding;
    /// Declare string
    String currentId, currentName, currentAddress, currentLat, currentLng, currentNumber, currentImage, currentLocationName, currentRate, currentReview;

    // Declare bottom sheet
    BottomSheetDialog globalSheetTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCinemaLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // Receive the intent from other classes
        Intent intent = getIntent();
        // Check whether the intent is null or not to get the data
        if (intent != null) {
            // Check the key from intent and get the correct value
            if (intent.hasExtra("address")) {
                currentAddress = intent.getStringExtra("address");
            }
            if (intent.hasExtra("lat")) {
                currentLat = intent.getStringExtra("lat");
            }
            if (intent.hasExtra("lng")) {
                currentLng = intent.getStringExtra("lng");
            }
            if (intent.hasExtra("name")) {
                currentName = intent.getStringExtra("name");
            }
            if (intent.hasExtra("id")) {
                currentId = intent.getStringExtra("id");
            }
            if (intent.hasExtra("imageUrl")) {
                currentImage = intent.getStringExtra("imageUrl");
            }
            if (intent.hasExtra("contactNumber")) {
                currentNumber = intent.getStringExtra("contactNumber");
            }
            if (intent.hasExtra("locationName")) {
                currentLocationName = intent.getStringExtra("locationName");
            }
            if (intent.hasExtra("rate")){
                currentRate = intent.getStringExtra("rate");
            }
            if (intent.hasExtra("review")){
                currentReview = intent.getStringExtra("review");
            }
            // Create currentCinema instance by values received when the user choose the specific cinema
            currentCinema = new Cinema(currentId, currentName, currentAddress, Double.parseDouble(currentLat), Double.parseDouble(currentLng), Double.parseDouble(currentRate), currentNumber, currentImage, currentLocationName,currentReview);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        /**
         *
         *  Automatic move and animate camera to the cinema location when the map is ready
         */
        // Add a marker in cinema and animate the camera
        LatLng cinema = new LatLng(currentCinema.getLatitude(), currentCinema.getLongitude());
        mMap.getUiSettings().setZoomControlsEnabled(true);
        @SuppressLint("UseCompatLoadingForDrawables")
        //Customize the pixel of the marker image import to drawable
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.cinema_location_icon);
        Bitmap b = bitmapDrawable.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 150, 170, true);
        // Create new markers
        MarkerOptions mMarker = new MarkerOptions();
        mMarker.position(cinema).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        // Add marker to map
        mMap.addMarker(mMarker.position(cinema));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cinema, 16));

        /***
         * Open bottom sheet to show cinema detail when user click on the marker
         */
        mMap.setOnMarkerClickListener(location -> {
            openCinemaBottomSheet(R.layout.cinema_description_bottom_sheet);
            return false;
        });
    }

    @SuppressLint("SetTextI18n")
    private void openCinemaBottomSheet(int bottomSheetLayout) {
        // Create a view
        View viewDialog = getLayoutInflater().inflate(bottomSheetLayout, null);
        // Create bottom sheet dialog with layout and theme
        BottomSheetDialog cinemaBottomSheetDialog = new BottomSheetDialog(this,R.style.BottomSheetDialogTheme);
        globalSheetTracker = cinemaBottomSheetDialog;
        cinemaBottomSheetDialog.setCancelable(false);
        cinemaBottomSheetDialog.setCanceledOnTouchOutside(false);
        // Set content for bottom sheet
        cinemaBottomSheetDialog.setContentView(viewDialog);
        //Start bottom sheet
        cinemaBottomSheetDialog.show();

        // Declare and binding the value to XML layout
        ImageView imageUrl = viewDialog.findViewById(R.id.cinemaDescriptionImage);
        TextView category = viewDialog.findViewById(R.id.cinemaDescriptionCategory);
        TextView phone = viewDialog.findViewById(R.id.cinemaDescriptionPhone);
        TextView address = viewDialog.findViewById(R.id.cinemaDescriptionAddress);
        TextView rate = viewDialog.findViewById(R.id.cinemaDescriptionRate);
        TextView review = viewDialog.findViewById(R.id.cinemaDescriptionReview);
        TextView name = viewDialog.findViewById(R.id.cinemaDescriptionTitle);
        ImageView close = viewDialog.findViewById(R.id.cinemaDescriptionClose);
        // Render the UI dynamically by the current cinema
        // Use this library to render the image by url
        Picasso.get().load(currentCinema.getImageUrl()).into(imageUrl);
        category.setText("Movie Theater");
        phone.setText(currentCinema.getContactNumber());
        name.setText(currentCinema.getName());
        address.setText(currentCinema.getAddress());
        rate.setText(Double.toString(currentCinema.getRate()));
        review.setText(currentCinema.getReview());

        // close the bottom sheet
        close.setOnClickListener(view ->{
           cinemaBottomSheetDialog.dismiss();
        });
    }
}