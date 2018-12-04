package com.example.vivianfca.studyspaceapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchDataforFilter extends AsyncTask<Void,Void,Void> {
    String data = "";
    String dataParsed = "";
    String singleParsed = "";


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

            JSONArray JA = new JSONArray(data);

            String[][] dataArray = new String[8][JA.length()];
            String[] nameArr = new String[JA.length()];
            String[] buildingArr = new String[JA.length()];
            String[] hoursArr = new String[JA.length()];
            String[] typeArr = new String[JA.length()];
            String[] locArr = new String[JA.length()];
            String[] addressArr = new String[JA.length()];
            String[] resourcesArr = new String[JA.length()];
            String[] noiseArr = new String[JA.length()];

            //------------------prepare data arrays for filtering-------------------//
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



                String[] typeSplited = typeArr[i].split(",");

                String[] typeSplited = typeArr[i].split(",");
            }

            dataArray[0] = nameArr;
            dataArray[1] = buildingArr;
            dataArray[2] = hoursArr;
            dataArray[3] = typeArr;
            dataArray[4] = locArr;
            dataArray[5] = addressArr;
            dataArray[6] = resourcesArr;
            dataArray[7] = noiseArr;








            //----------------------parse data after filtering--------//

//            for (int i = 0; i < JA.length(); i++) {
//                JSONObject JO = (JSONObject) JA.get(i);
//                singleParsed = "Name" + JO.get("Name") + "\n"
//                        + "Building" + JO.get("Building") + "\n"
//                        + "Hours" + JO.get("Hours") + "\n"
//                        + "Type of Space" + JO.get("Type of Space") + "\n"
//                        + "Location" + JO.get("Location") + "\n"
//                        + "Address" + JO.get("Address") + "\n"
//                        + "Resources" + JO.get("Resources") + "\n"
//                        + "Noise Level" + JO.get("Noise Level");
//                dataParsed = dataParsed + singleParsed + "\n";
//
//
//                String[] singleVariable = new String[8];
//                dataArray[i] = singleDataArray;
//            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        DisplayActivity.data.setText(this.dataParsed);

    }

}
