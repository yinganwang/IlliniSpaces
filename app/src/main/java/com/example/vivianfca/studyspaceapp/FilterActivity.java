package com.example.vivianfca.studyspaceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
//import com.example.vivianfca.studyspaceapp.FetchDataforFilter;

public class FilterActivity extends AppCompatActivity {
    //public static List<String> filteredForDisplay;
    public CheckBox[] filterBoxes = new CheckBox[17];
    //public String toPass = "hooo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        //If the filter activity is invoked
        if (getIntent().hasExtra("com.example.vivianfca.StudySpaceApp.something")) {


            Button filterSearch = (Button) findViewById(R.id.filterSearch);

            //set onclick listener of the search button
            filterSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



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

                    String data = "";
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
                    List<String> filtered = new ArrayList<>();
                    List<JSONObject> readytoReturn = new ArrayList<>();
                    List<String> tmp = new ArrayList<>();
                    HashMap<String, Integer> hm = new HashMap<>();
                    int count = 0;

                    try {
                        URL url = new URL("https://api.myjson.com/bins/1dwygq");

                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String line = "";
                        while (line != null) {
                            line = bufferedReader.readLine();
                            data = data + line;
                        }
                        String[] splited = data.split("\\s*,\\s*", -1);
                        //System.out.println("printdata" + data);
                        JA = new JSONArray(data);
//                        JSONObject obj = new JSONObject(data);
//                        System.out.println("zizizizi" + JA);
//                        JA = new JSONArray();
//                        JSONArray Jarray = obj.getJSONArray("Address");

                        //dataArray = new String[8][JA.length()];

                        nameArr = new String[splited.length];
                        buildingArr = new String[splited.length];
                        hoursArr = new String[splited.length];
                        typeArr = new String[splited.length];
                        locArr = new String[splited.length];
                        addressArr = new String[splited.length];
                        resourcesArr = new String[splited.length];
                        noiseArr = new String[splited.length];
                        


                        //-----------------add JSON object to a list for filtering---------------//
                        for (int i = 0; i < splited.length; i++) {
                            JSONObject JO = (JSONObject) JA.get(i);

                            nameArr[i] = (String) JO.get("Name");
                            buildingArr[i] = (String) JO.get("Building");
                            hoursArr[i] = (String) JO.get("Hours");
                            typeArr[i] = (String) JO.get("Type of Space");
                            locArr[i] = (String) JO.get("Location");
                            addressArr[i] = (String) JO.get("Address");
                            resourcesArr[i] = (String) JO.get("Resources");
                            noiseArr[i] = (String) JO.get("Noise Level");

                            toReturn.add(JO);
                            //System.out.println(JO);


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


                            //-------------------------filtering--------------------------------//



                            for (int j = 0; j < filterBoxes.length; j++) {
                                if (filterBoxes[j].isChecked()) {
                                    count++;
//                    checkedString = filterArray[i];
//                    url += checkedString;
                                    if (j <= 7) {
                                        //queryString = "Type%20of%20Space";

                                        String[] splitedagain = typeArr[i].split(",");
                                        List<String> spliteList = Arrays.asList(splitedagain);
                                        if (spliteList.contains(filterArray[j])) {
                                            tmp.add(addressArr[i]);
                                            hm.put(addressArr[i], hm.getOrDefault(addressArr[i], 0) + 1);
                                        }
                                    } else if (j <= 14) {
//                        queryString = "Resources";
                                        String[] splitedagain = resourcesArr[i].split(",");
                                        List<String> spliteList = Arrays.asList(splitedagain);
                                        if (spliteList.contains(filterArray[j])) {
                                            tmp.add(addressArr[i]);
                                            hm.put(addressArr[i], hm.getOrDefault(addressArr[i], 0) + 1);
                                        }
                                    } else {
//                        queryString = "Noise%20Level";
                                        String[] splitedagain = noiseArr[i].split(",");
                                        List<String> spliteList = Arrays.asList(splitedagain);
                                        if (spliteList.contains(filterArray[j])) {
                                            tmp.add(addressArr[i]);
                                            hm.put(addressArr[i], hm.getOrDefault(addressArr[i], 0) + 1);
                                        }
                                    }
                                }
                            }
                        }
                        for (String a : tmp) {
                            if (a != null && hm.get(a) == count - 1 && !(filtered.contains(a))) {
                                filtered.add(a);
                            }
                        }
                        for (String a : filtered) {
                            finalDisplay += ("/n" + a);
                        }
                        System.out.println("xixi" + finalDisplay);
                        Intent i= new Intent(getApplicationContext(), DisplayActivity.class);
                        i.putExtra("key",finalDisplay);
                        // for explicit intents
                        // Intent i= new Intent(ActivityName.this,SecondActivity.class);
                        // parameter 1 is the key
                        // parameter 2 is the value
                        // your value
                        startActivity(i);


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

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


//                    FetchDataforFilter process = new FetchDataforFilter();
//                    process.execute();

                    Intent displayIntent = new Intent(getApplicationContext(), DisplayActivity.class);
                    startActivity(displayIntent);

                }
            });

            //types of spaces



//
//            List<String> checkedItems = new ArrayList<>();
//
//            for (int i = 0; i < filterBoxes.length; i++) {
//                if (filterBoxes[i].isChecked()) {
//                    checkedItems.add(filterArray[i]);
//                }
//            }
//            final String USER_AGENT = "Yingan/5.0";
//            String checkedString = "";
//            String queryString = "";
//            String queryString2 = "";
//            String url = "https://api.myjson.com/bins/1dwygq/?" + queryString + "=" + queryString2;

//                    queryString2 = filterArray[i];

//                    URL obj = null;
//                    try {
//                        obj = new URL(url);
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    }
//                    HttpURLConnection con = null;
//                    try {
//                        con = (HttpURLConnection) obj.openConnection();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    // optional default is GET
//                    try {
//                        con.setRequestMethod("GET");
//                    } catch (ProtocolException e) {
//                        e.printStackTrace();
//                    }
//
//                    //add request header
//                    con.setRequestProperty("User-Agent", USER_AGENT);
//
//                    int responseCode = 0;
//                    try {
//                        responseCode = con.getResponseCode();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
////                    System.out.println("\nSending 'GET' request to URL : " + url);
////                    System.out.println("Response Code : " + responseCode);
//
//                    BufferedReader in = null;
//                    try {
//                        in = new BufferedReader(
//                                new InputStreamReader(con.getInputStream()));
//                        String inputLine = "";
//                        while ((inputLine = in.readLine()) != null) {
//                            //response.append(inputLine);
//                            final HashMap<String, Integer> hm = new HashMap();
//                            //final Set<String> set1 = new HashSet<String>();
//                            for (String j : filtered) {
//                                if (hm.get(j) == i) {
//                                    hm.put(j, hm.get(j) + 1);
//                                    filtered.add(inputLine);
//                                }
////                                if (hm.get(j) == null) {
////                                    NullPointerException e = new NullPointerException();
////                                    e.printStackTrace();
////                                }
//                            }
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    //StringBuffer response = new StringBuffer();
//
//                    try {
//                        in.close();
//                        filteredForDisplay = filtered;
//                        finalDisplay = filteredForDisplay.toString();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }




        }
    }
//    public static void update(TextView tv){
//        tv.setText(finalDisplay);
//        if (finalDisplay.length() == 0) {
//            System.out.println("hahahhahhahhahhhaha");
//        }
//    }
//    public void sendGet() throws Exception {
//
//
//    }
}
