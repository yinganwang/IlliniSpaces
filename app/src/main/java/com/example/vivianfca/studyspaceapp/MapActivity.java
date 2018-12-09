package com.example.vivianfca.studyspaceapp;

import android.Manifest;
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
import android.view.View;
import android.widget.Toast;


//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    List<Double> lat = new ArrayList<>();
    List<Double> lng = new ArrayList<>();
    ArrayList<String> info = new ArrayList<>();
    ArrayList<String> addresses = new ArrayList<>();

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


//        System.out.println("lllll" + infostrFiltered);

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

//        String[] infoarr = (String[]) info.toArray();
        String infostr = "";

        for (String r : info) {
            infostr += r + "\n";
        }
//        System.out.println(info + "ojojoj");
        String[] infostrSplited = infostr.split("\\|");
//        System.out.println(infostr + "0h0h0h");

//        List<Marker> displayMark = new ArrayList<>();
        // Add markers and move camera.
        for (int i = 0; i < lat.size(); i++) {
            for (int j = 0; j < infostrSplited.length; j++) {

                LatLng loc = new LatLng(lat.get(i), lng.get(i));


//                infostrrrrSplited = infostrrrr[j].split("\\|");

                String[] tripleSplited = infostrSplited[j].split("\\*");
//                System.out.println(tripleSplited[1] + "hahaha");
//                System.out.println(infostrSplited[j] + "jijiji");



                mMap.addMarker(new MarkerOptions().position(loc).title(tripleSplited[0]).snippet(infostrSplited[j]));

                mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));


            }
        }


    }

    //<-------------------------------------------new current location code------------------------------------------------------>

}