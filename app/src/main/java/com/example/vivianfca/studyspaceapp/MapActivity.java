package com.example.vivianfca.studyspaceapp;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Double> lat = new ArrayList<Double>();
    List<Double> lng = new ArrayList<Double>();
    List<String> addresses = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // filtered addresses from filter Activity
        ArrayList<String> value = getIntent().getStringArrayListExtra("key");
        addresses = value;
        // filtered info waiting to be displayed at map markers from filter Activity
        String[] infostrFiltered = getIntent().getStringArrayExtra("info");

        for (int i = 0; i < value.size(); i++) {
            Geocoder gc = new Geocoder(this);
            try {
                List<Address> list = gc.getFromLocationName(value.get(i), 1);
                Address address = list.get(0);
                lat.add(address.getLatitude());
                lng.add(address.getLongitude());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        for (int i = 0; i < lat.size(); i++) {

            LatLng loc = new LatLng(lat.get(i), lng.get(i));
            mMap.addMarker(new MarkerOptions().position(loc).title(addresses.get(i)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));

        }

    }
}