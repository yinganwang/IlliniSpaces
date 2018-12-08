package com.example.vivianfca.studyspaceapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        //startForeground(100, new NotificationCompat.Builder(this).build());

        TextView data = (TextView) findViewById(R.id.spaces);
        //Bundle extras = getIntent().getExtras();


//        String value = getIntent().getStringExtra("key");
//        //get the value based on the key
//        data.setText(value);

//        String next = FilterActivity.finalDisplay;
//
//        //System.out.println("CS125" + next);
//        //next = getResources().getString(R.string.spaces_result_displayed);
//        data.setText(next);
//
//
//
//
////        data.setText("null");
//        data.setText(FilterActivity.finalDisplay);
//        System.out.println("cocococococo"+ FilterActivity.finalDisplay);
//        FilterActivity.update(data);

        //direct to google maps button.
        Button directToGMaps = findViewById(R.id.directToGMaps);
        directToGMaps.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri navigationIntentUri = Uri.parse("google.navigation:q=" + 12f + "," + 2f);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);


                //message to display if maps isn't installed.
                try
                {
                    startActivity(mapIntent);
                }
                catch(ActivityNotFoundException ex)
                {
                    Toast.makeText(getApplicationContext(), "Please install a maps application", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    public static void setData(TextView data) {
//        data.setText(FilterActivity.finalDisplay);
//    }
}
