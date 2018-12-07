package com.example.vivianfca.studyspaceapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.CheckBox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static android.provider.Settings.System.getString;

public class FetchDataforFilter extends AsyncTask<Void,Void,Void> {


    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    static List<JSONObject> toReturn = new ArrayList<>();
    static JSONArray JA;

    //static String[][] dataArray;

    static String[] typeArr;
    static String[] resourcesArr;
    static String[] noiseArr;

    static String[] nameArr;
    static String[] buildingArr;
    static String[] hoursArr;
    static String[] locArr;
    static String[] addressArr;

    static String finalDisplay = "";
    List<String> filtered = new ArrayList<>();
    //List<JSONObject> toReturn = toReturn;
    List<String> tmp = new ArrayList<>();
    HashMap<String, Integer> hm = new HashMap<>();
    int count = 0;


    @Override
    protected Void doInBackground(Void... voids) {
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
            //System.out.println("printdata" + data);
            JA = new JSONArray(data);
//            for (int i = 0; i < JA.length(); i++) {
//                JSONObject JO = JA.getJSONObject(i);
//
//            }

            //dataArray = new String[8][JA.length()];
            nameArr = new String[JA.length()];
            buildingArr = new String[JA.length()];
            hoursArr = new String[JA.length()];
            typeArr = new String[JA.length()];
            locArr = new String[JA.length()];
            addressArr = new String[JA.length()];
            resourcesArr = new String[JA.length()];
            noiseArr = new String[JA.length()];

            //------------------prepare data arrays for filtering-------------------//

//            dataArray[0] = nameArr;
//            dataArray[1] = buildingArr;
//            dataArray[2] = hoursArr;
//            dataArray[3] = typeArr;
//            dataArray[4] = locArr;
//            dataArray[5] = addressArr;
//            dataArray[6] = resourcesArr;
//            dataArray[7] = noiseArr;


            //-----------------add JSON object to a list for filtering---------------//
            for (int i = 0; i < JA.length(); i++) {
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



                for (int j = 0; j < FilterActivity.filterBoxes.length; j++) {
                    if (FilterActivity.filterBoxes[j].isChecked()) {
                        count++;
//                    checkedString = filterArray[i];
//                    url += checkedString;
                        if (j <= 7) {
                            //queryString = "Type%20of%20Space";

                            String[] splited = typeArr[i].split(",");
                            List<String> spliteList = Arrays.asList(splited);
                            if (spliteList.contains(filterArray[j])) {
                                tmp.add(addressArr[i]);
                                hm.put(addressArr[i], hm.getOrDefault(addressArr[i], 0) + 1);
                            }
                        } else if (j <= 14) {
//                        queryString = "Resources";
                            String[] splited = resourcesArr[i].split(",");
                            List<String> spliteList = Arrays.asList(splited);
                            if (spliteList.contains(filterArray[j])) {
                                tmp.add(addressArr[i]);
                                hm.put(addressArr[i], hm.getOrDefault(addressArr[i], 0) + 1);
                            }
                        } else {
//                        queryString = "Noise%20Level";
                            String[] splited = noiseArr[i].split(",");
                            List<String> spliteList = Arrays.asList(splited);
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

    } catch(IOException | JSONException e)
    {
        e.printStackTrace();
    }
        return null;
    }

//    @Override
//    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
//
//        DisplayActivity.data.setText(this.dataParsed);
//
//    }



}
