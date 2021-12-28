package com.example.android_cinema_management.CinemaManagement;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

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

public class CinemaLocation extends FragmentActivity implements OnMapReadyCallback {
    private Cinema currentCinema;
    private GoogleMap mMap;
    private ActivityCinemaLocationBinding binding;
    String currentId, currentName, currentAddress, currentLat, currentLng, currentNumber, currentImage, currentLocationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCinemaLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        if (intent != null) {
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
            currentCinema = new Cinema(currentId, currentName, currentAddress, Double.parseDouble(currentLat), Double.parseDouble(currentLng), currentNumber, currentImage, currentLocationName);
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
        LatLng cinema = new LatLng(currentCinema.getLatitude(),currentCinema.getLongitude());
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
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cinema, 18));

        /***
         * Open bottom sheet to show cinema detail when user click on the marker
         */
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {

                return false;
            }
        });
    }
        private void openCinemaBottomSheet(){

        }
}