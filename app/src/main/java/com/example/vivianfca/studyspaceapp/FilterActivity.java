package com.example.vivianfca.studyspaceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
//import com.example.vivianfca.studyspaceapp.FetchDataforFilter;

public class FilterActivity extends AppCompatActivity {
    //public List<String> filteredForDisplay;

    //public String toPass = "hooo";

    CheckBox[] filterBoxes = new CheckBox[17];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

//        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        progressBar.setMax(10);


        Button filterSearch = (Button) findViewById(R.id.filterSearch);
        filterSearch.setText("Search");


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

        String[] typeArr;
        String[] resourcesArr;
        String[] noiseArr;

        String[] nameArr;
        String[] buildingArr;
        String[] hoursArr;
        String[] locArr;
        String[] addressArr;

        String finalDisplay = "";
        List<String> filtered = new ArrayList<>();

        List<String> tmp = new ArrayList<>();
        HashMap<String, Integer> hm = new HashMap<>();
        int count = 0;

        String[] splited;
        List<String> splitedList = new ArrayList<>();






        //If the filter activity is invoked
        if (getIntent().hasExtra("com.example.vivianfca.StudySpaceApp.something")) {


//            Intent displayIntent = new Intent(getApplicationContext(), DisplayActivity.class).putExtra("key2", filterBoxes);
//            startActivity(displayIntent);


            //set onclick listener of the search button
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    //--------------------Checkbox filtering---------------------------------//


                    String allData = "happy";

                    List<JSONObject> toReturn = new ArrayList<>();
                    JSONArray JA;

                    String[] typeArr;
                    String[] resourcesArr;
                    String[] noiseArr;

                    String[] nameArr;
                    String[] buildingArr;
                    String[] hoursArr;
                    String[] locArr;
                    String[] addressArr;

                    String finalDisplay = "hahahah";
                    List<String> filtered = new ArrayList<>();
                    List<JSONObject> readytoReturn = new ArrayList<>();
                    List<String> tmp = new ArrayList<>();
                    HashMap<String, Integer> hm = new HashMap<>();




//                    progressBar.setVisibility(View.INVISIBLE);
//                    progressBar.setProgress(0);
//                    switch (v.getId()) {
//                        case R.id.filterSearch:
//                            try {
//                                String allData = new FetchDataforFilter().execute().get();
//                            } catch (ExecutionException e) {
//                                e.printStackTrace();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            break;
//                    }
                    try {
                        allData = new FetchDataforFilter().execute().get();
                        System.out.println("uuuuu" + allData);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    //--------------------filtering data-----------------//
                    try {
                        JA = new JSONArray(allData);
                        nameArr = new String[JA.length()];
                        buildingArr = new String[JA.length()];
                        hoursArr = new String[JA.length()];
                        typeArr = new String[JA.length()];
                        locArr = new String[JA.length()];
                        addressArr = new String[JA.length()];
                        resourcesArr = new String[JA.length()];
                        noiseArr = new String[JA.length()];

                        String[] filterArray = new String[17];

                        filterArray[0] = "Study room";
                        filterArray[1] = "Study area";
                        filterArray[2] = "Computer lab";
                        filterArray[3] = "Production studio";
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

                        int tmpcount = 0;



                        //System.out.println("sssss" + JA.length());
                        for (int i = 0; i < JA.length(); i++) {
                            int count = 0;

                            JSONObject JO = (JSONObject) JA.get(i);
                            tmpcount++;
                            //System.out.println("bbbbb" + tmpcount + JO );

                            nameArr[i] = (String) JO.get("Name");
                            buildingArr[i] = (String) JO.get("Building");
                            hoursArr[i] = (String) JO.get("Hours");
                            typeArr[i] = (String) JO.get("Type of Space");
                            locArr[i] = (String) JO.get("Location");
                            addressArr[i] = (String) JO.get("Address");
                            resourcesArr[i] = (String) JO.get("Resources");
                            noiseArr[i] = (String) JO.get("Noise Level");

                            for (int j = 0; j < 17; j++) {
                                if (filterBoxes[j].isChecked()) {
                                    count++;
                                    if (j <= 7) {
                                        String[] splitedagain = typeArr[i].split(",");

//                                        System.out.println("bbbbb" + count + splitedagain.toString());
                                        List<String> spliteList = Arrays.asList(splitedagain);
                                        if (spliteList.contains(filterArray[j])) {
                                            tmp.add(addressArr[i]);
                                            hm.put(addressArr[i], hm.getOrDefault(addressArr[i], 0) + 1);
                                        }
                                    } else if (j <= 14) {
                                        String[] splitedagain = resourcesArr[i].split(",");
                                        List<String> spliteList = Arrays.asList(splitedagain);
                                        if (spliteList.contains(filterArray[j])) {
                                            tmp.add(addressArr[i]);
                                            hm.put(addressArr[i], hm.getOrDefault(addressArr[i], 0) + 1);
                                        }
                                    } else {
                                        String splitedagain = noiseArr[i];
                                        if (splitedagain.equals(filterArray[j])) {
                                            tmp.add(addressArr[i]);
                                            hm.put(addressArr[i], hm.getOrDefault(addressArr[i], 0) + 1);
                                        }
                                    }



                                }

                            }
                            for (String a : tmp) {
                                if (a != null && hm.get(a) == count && !(filtered.contains(a))) {
                                    filtered.add(a);
                                }
                            }
                        }
                        finalDisplay = filtered.toString();
                        System.out.println("xixi" + finalDisplay);
                        Intent displayIntent = new Intent(FilterActivity.this, DisplayActivity.class);
                        displayIntent.putExtra("key", finalDisplay);
                        // for explicit intents
                        // Intent i= new Intent(ActivityName.this,SecondActivity.class);
                        // parameter 1 is the key
                        // parameter 2 is the value
                        // your value
                        startActivity(displayIntent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            };

            filterSearch.setOnClickListener(listener);


        }


//        Intent test = new Intent(FilterActivity.this, DisplayActivity.class);
//        startForegroundService(test);
    }
}












    //----------------------parse data after filtering--------//


//            singleParsed = "Name" + JO.get("Name") + "\n"
//                    + "Building" + JO.get("Building") + "\n"
//                    + "Hours" + JO.get("Hours") + "\n"
//                    + "Type of Space" + JO.get("Type of Space") + "\n"
//                    + "Location" + JO.get("Location") + "\n"
//                    + "Address" + JO.get("Address") + "\n"
//                    + "Resources" + JO.get("Resources") + "\n"
//                    + "Noise Level" + JO.get("Noise Level");
//            dataParsed = dataParsed + singleParsed + "\n";

//
//                String[] singleVariable = new String[8];
//                dataArray[i] = singleDataArray;
//            }
