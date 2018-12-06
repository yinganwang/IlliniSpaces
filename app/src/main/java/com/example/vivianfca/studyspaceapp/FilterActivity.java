package com.example.vivianfca.studyspaceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class FilterActivity extends AppCompatActivity {
    public static List<String> filteredForDisplay;
    public static String finalDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        //If the filter activity is invoked
        if (getIntent().hasExtra("com.example.vivianfca.StudySpaceApp.something")) {

            //JSON API
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            String URL = "https://api.myjson.com/bins/1dwygq";

            //JSON object
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    URL,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("Rest Response", response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Rest Response", error.toString());
                        }
                    }
            );
            requestQueue.add(objectRequest);

            Button filterSearch = (Button) findViewById(R.id.filterSearch);
            filterSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mapIntent = new Intent(getApplicationContext(), DisplayActivity.class);
                    mapIntent.putExtra("com.example.vivianfca.StudySpaceApp.map",
                            "HELLO MAP");
                    startActivity(mapIntent);
                }
            });


            //set onclick listener of the search button
//            filterSearch.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    FetchDataforFilter process = new FetchDataforFilter();
//                    process.execute();
//                }
//            });

            //types of spaces
            CheckBox studyRoom = (CheckBox) findViewById(R.id.studyRoom);
            CheckBox studyArea = (CheckBox) findViewById(R.id.studyArea);
            CheckBox computerLab = (CheckBox) findViewById(R.id.computerLab);
            CheckBox studio = (CheckBox) findViewById(R.id.studio);
            CheckBox classroom = (CheckBox) findViewById(R.id.classroom);
            CheckBox open = (CheckBox) findViewById(R.id.open);
            CheckBox lounge = (CheckBox) findViewById(R.id.lounge);
            CheckBox cafe = (CheckBox) findViewById(R.id.cafe);

            //Resources
            CheckBox whiteboard = (CheckBox) findViewById(R.id.whiteboard);
            CheckBox outlets = (CheckBox) findViewById(R.id.outlets);
            CheckBox computers = (CheckBox) findViewById(R.id.computers);
            CheckBox scanning = (CheckBox) findViewById(R.id.scanning);
            CheckBox largeDisplay = (CheckBox) findViewById(R.id.largeDisplay);
            CheckBox projector = (CheckBox) findViewById(R.id.projector);
            CheckBox printing = (CheckBox) findViewById(R.id.printing);

            //Noise level
            CheckBox silent = (CheckBox) findViewById(R.id.silent);
            CheckBox notSilent = (CheckBox) findViewById(R.id.notSilent);

            //--------------------Checkbox filtering---------------------------------//

            CheckBox[] filterBoxes = new CheckBox[17];
            filterBoxes[0] = studyRoom;
            filterBoxes[1] = studyArea;
            filterBoxes[2] = computerLab;
            filterBoxes[3] = studio;
            filterBoxes[4] = classroom;
            filterBoxes[5] = open;
            filterBoxes[6] = lounge;
            filterBoxes[7] = cafe;
            filterBoxes[8] = whiteboard;
            filterBoxes[9] = outlets;
            filterBoxes[10] = computers;
            filterBoxes[11] = scanning;
            filterBoxes[12] = largeDisplay;
            filterBoxes[13] = projector;
            filterBoxes[14] = printing;
            filterBoxes[15] = silent;
            filterBoxes[16] = notSilent;

            String[] filterArray = new String[17];
            filterArray[0] = "Study room";
            filterArray[1] = "Study area";
            filterArray[2] = "Computer lab";
            filterArray[3] = "Studio";
            filterArray[4] = "Classroom";
            filterArray[5] = "Open space";
            filterArray[6] = "Lounge";
            filterArray[7] = "Cafe";
            filterArray[8] = "Whiteboards";
            filterArray[9] = "Outlets";
            filterArray[10] = "Computers";
            filterArray[11] = "Scanning";
            filterArray[12] = "Display";
            filterArray[13] = "Projector";
            filterArray[14] = "Printing";
            filterArray[15] = "quiet";
            filterArray[16] = "variable";
//
//            List<String> checkedItems = new ArrayList<>();
//
//            for (int i = 0; i < filterBoxes.length; i++) {
//                if (filterBoxes[i].isChecked()) {
//                    checkedItems.add(filterArray[i]);
//                }
//            }
            final String USER_AGENT = "Yingan/5.0";
            String checkedString = "";
            String url = "https://api.myjson.com/bins/1dwygq/?q={}&filter=";
            List<String> filtered = new ArrayList<>();
//            int count = 0;
            for (int i = 0; i < filterBoxes.length; i++) {
                if (filterBoxes[i].isChecked()) {
//                    count++;
                    checkedString = filterArray[i];
                    url += checkedString;
                    URL obj = null;
                    try {
                        obj = new URL(url);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    HttpURLConnection con = null;
                    try {
                        con = (HttpURLConnection) obj.openConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // optional default is GET
                    try {
                        con.setRequestMethod("GET");
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    }

                    //add request header
                    con.setRequestProperty("User-Agent", USER_AGENT);

                    int responseCode = 0;
                    try {
                        responseCode = con.getResponseCode();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    System.out.println("\nSending 'GET' request to URL : " + url);
//                    System.out.println("Response Code : " + responseCode);

                    BufferedReader in = null;
                    try {
                        in = new BufferedReader(
                                new InputStreamReader(con.getInputStream()));
                        String inputLine = "";
                        while ((inputLine = in.readLine()) != null) {
                            //response.append(inputLine);
                            final HashMap<String, Integer> hm = new HashMap();
                            //final Set<String> set1 = new HashSet<String>();
                            for (String j : filtered) {
                                if (hm.get(j) == i) {
                                    hm.put(j, hm.get(j) + 1);
                                    filtered.add(inputLine);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //StringBuffer response = new StringBuffer();

                    try {
                        in.close();
                        filteredForDisplay = filtered;
                        finalDisplay = filteredForDisplay.toString();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
//    public void sendGet() throws Exception {
//
//
//    }
}
