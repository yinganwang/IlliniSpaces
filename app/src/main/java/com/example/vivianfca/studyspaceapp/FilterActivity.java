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


public class FilterActivity extends AppCompatActivity {

    CheckBox[] filterBoxes = new CheckBox[17];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

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


        //If the filter activity is invoked
        if (getIntent().hasExtra("com.example.vivianfca.StudySpaceApp.something")) {

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

                    String finalDisplay = "";
                    ArrayList<String> filtered = new ArrayList<>();

                    JSONObject JO;

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
                        List<String> info = new ArrayList<>();
                        String infostr = "";


                        for (int i = 0; i < JA.length(); i++) {
                            int count = 0;

                            List<String> tmp = new ArrayList<>();
                            HashMap<String, Integer> hm = new HashMap<>();

                            JO = (JSONObject) JA.get(i);

                            tmpcount++;


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
                                }//end of if checked
                            }//end of j loop

                            if (count == 0) {
                                tmp.add(addressArr[i]);
                            }


                            if (count != 0) {
                                for (String a : tmp) {
                                    if (a != null && hm.get(a) == count && !(filtered.contains(a))) {
                                        filtered.add(a);
                                        info.add(nameArr[i]);
                                        info.add("~");
                                        info.add(buildingArr[i]);
                                        info.add("~");
                                        info.add(hoursArr[i]);
                                        info.add("~");
                                        info.add(typeArr[i]);
                                        info.add("~");
                                        info.add(locArr[i]);
                                        info.add("~");
                                        info.add(addressArr[i]);
                                        info.add("~");
                                        info.add(resourcesArr[i]);
                                        info.add("~");
                                        info.add(noiseArr[i]);
                                        info.add("`");
                                    }
                                }
                            } else {
                                for (String a : tmp) {
                                    if (a != null && !(filtered.contains(a))) {
                                        filtered.add(a);
                                        info.add(nameArr[i]);
                                        info.add("~");
                                        info.add(buildingArr[i]);
                                        info.add("~");
                                        info.add(hoursArr[i]);
                                        info.add("~");
                                        info.add(typeArr[i]);
                                        info.add("~");
                                        info.add(locArr[i]);
                                        info.add("~");
                                        info.add(addressArr[i]);
                                        info.add("~");
                                        info.add(resourcesArr[i]);
                                        info.add("~");
                                        info.add(noiseArr[i]);
                                        info.add("`");
                                    }
                                }
                            }
                        }//end of i loop

                        //@param filtered: a list of filtered addresses
                        //@param info: a list of filtered object info
                        //infostr: a string converted from info and is very very long with formatting
                        for (String r : info) {
                            infostr += r;
                        }
                        Log.d("come", infostr);



                        //infostrArray: an array of Strings of filtered objects(after merge, every object in the same location take into consideration
                        String[] infostrArray = infostr.split("`");

                        ArrayList<String> infostrSplited = new ArrayList<> (Arrays.asList(infostrArray));

                        System.out.println("xixi" + finalDisplay);
                        Intent displayIntent = new Intent(FilterActivity.this, MapActivity.class);
                        displayIntent.putStringArrayListExtra("key", filtered);
                        displayIntent.putStringArrayListExtra("info", infostrSplited);
                        displayIntent.putExtra("Addinfo", finalDisplay);

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
    }
}
