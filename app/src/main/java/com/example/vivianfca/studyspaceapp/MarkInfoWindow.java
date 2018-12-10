package com.example.vivianfca.studyspaceapp;

import android.app.AppComponentFactory;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MarkInfoWindow implements GoogleMap.InfoWindowAdapter {
    private HashMap<Marker, String> MarkersInfo;
    private View mWindow;
    private Context mContext;


    public MarkInfoWindow(Context context) {
        this.mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
        Log.d("ADAPTER", "here");
    }


    private void rendowWindowText(Marker marker, View view) {

        String title = marker.getTitle();
        TextView tvTitle = (TextView) view.findViewById(R.id.title);
//        ArrayList<String> displayMark = getIntent().getString




        if (tvTitle != null && !tvTitle.equals("")) {
            tvTitle.setText(title);
        }
        String snippet= marker.getSnippet();
        TextView tvSnippet = (TextView) view.findViewById(R.id.snippet);
        if (tvTitle != null && !tvTitle.equals("")) {
            tvSnippet.setText(snippet);
        }

    }

//    mMap.setOnMarkerClickListener(new OnMarkerClickListener() {

        @Override
        public View getInfoWindow (Marker marker){
            rendowWindowText(marker, mWindow);
            return mWindow;
        }

        @Override
        public View getInfoContents (Marker marker){
            rendowWindowText(marker, mWindow);
            return mWindow;
        }
//    });

    //    public MarkInfoWindow(HashMap<Marker, String> MarkersInfo) {
//        this.MarkersInfo = MarkersInfo;
//    }
//    @Override
//    public View getInfoContents(Marker marker) {
//        return null;
//    }
//    @Override
//    public View getInfoWindow(final Marker marker) {
//        String mark = MarkersInfo.get(marker);
//        if (mark != null) {
//            LayoutInflater inflater = (LayoutInflater) Controller.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.item_stop_marker_info, null);
//
//            TextView stopName = (TextView) view.findViewById(R.id.stop_name);
//            stopName.setText(stop.getString("name"));
//
//            TextView stopLine = (TextView) view.findViewById(R.id.stop_line);
//            stopLine.setText(stop.getString("line"));
//        }
//        return view;
//    }
}
