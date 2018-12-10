package com.example.vivianfca.studyspaceapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;


//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapActivity extends FragmentActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, OnMapReadyCallback {


    public GoogleMap mMap;
    List<Double> lat = new ArrayList<>();
    List<Double> lng = new ArrayList<>();
    ArrayList<String> info = new ArrayList<>();
    ArrayList<String> addresses = new ArrayList<>();
    List<Marker> markers = new ArrayList<>();

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

        info = getIntent().getStringArrayListExtra("info");

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
        Marker mark = null;
        int markerCount = 0;

        mMap.setInfoWindowAdapter(new MarkInfoWindow(MapActivity.this));


        // Add markers and move camera.
        for (int i = 0; i < lat.size(); i++) {

            LatLng loc = new LatLng(lat.get(i), lng.get(i));

            String[] splited = info.get(i).split("~");


            mark = mMap.addMarker(new MarkerOptions().position(loc).title(splited[1])
                    .snippet("Name: " + splited[0] + "\n"
                            + "Hours: " + splited[2] + "\n"
                            + "Type of Space: " + splited[3] + "\n"
                            + "Location: " + splited[4] + "\n"
                            + "Address: " + splited[5] + "\n"
                            + "Resources: " + splited[6] + "\n"
                            + "Noise Level: " + splited[7]));

                markers.add(mark);
                markerCount++;







                mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));


            }
            if (markerCount == 0) {
                Toast.makeText(
                        MapActivity.this,
                        "Sorry, no results found! Please choose filters again!",
                        Toast.LENGTH_LONG
                ).show();

            }


        //<-------------------------------------------new current location code------------------------------------------------------>





        //current loc code
        if (!(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            mMap.setMyLocationEnabled(true);

            mMap.getUiSettings().setMyLocationButtonEnabled(true);

            mMap.setOnMyLocationButtonClickListener(this);
            mMap.setOnMyLocationClickListener(this);
        }
//
        LatLng one = new LatLng(40.096980, -88.213220);
        LatLng two = new LatLng(40.118495, -88.235699);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        //add them to builder
        builder.include(one);
        builder.include(two);

        LatLngBounds bounds = builder.build();

        //get width and height to current display screen
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        // 20% padding
        int padding = (int) (width * 0.001);

        //set latlong bounds
        mMap.setLatLngBoundsForCameraTarget(bounds);

        //move camera to fill the bound to screen
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));

        //set zoom to level to current so that you won't be able to zoom out viz. move outside bounds
        mMap.setMinZoomPreference(mMap.getCameraPosition().zoom);

        }

    public void onMyLocationClick(@NonNull Location location) {
    }

    public boolean onMyLocationButtonClick() {
        return false;
    }
    }
