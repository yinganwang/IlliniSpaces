package com.example.vivianfca.studyspaceapp;

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

public class FilterActivity extends AppCompatActivity {

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
                    Intent mapIntent = new Intent(getApplicationContext(), MapActivity.class);
                    mapIntent.putExtra("com.example.vivianfca.StudySpaceApp.map",
                            "HELLO MAP");
                    startActivity(mapIntent);
                }
            });


            //set onclick listener of the search button
            /**filterSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FetchDataforFilter process = new FetchDataforFilter();
                    process.execute();
                }
            });
             */

            //types of spaces
            CheckBox studyRoom = (CheckBox) findViewById(R.id.studyRoom);
            CheckBox studyArea = (CheckBox) findViewById(R.id.studyArea);
            CheckBox computerLab = (CheckBox) findViewById(R.id.computerLab);
            CheckBox studio = (CheckBox) findViewById(R.id.studio);
            CheckBox classroom = (CheckBox) findViewById(R.id.classroom);
            CheckBox open = (CheckBox) findViewById(R.id.open);
            CheckBox lounge = (CheckBox) findViewById(R.id.lounge);
            CheckBox cafe = (CheckBox) findViewById(R.id.cafe);
            CheckBox outdoor = (CheckBox) findViewById(R.id.outdoor);

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

            //for (int i = 0; i < JA.length(); i++)
        }
    }
}
