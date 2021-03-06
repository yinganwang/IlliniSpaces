package com.example.vivianfca.studyspaceapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        //direct to google maps button.
        Button directToGMaps = findViewById(R.id.directSearch);
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

        //Activity to launch filter activity
        Button filter = (Button) findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterIntent = new Intent(getApplicationContext(), FilterActivity.class);
                filterIntent.putExtra("com.example.vivianfca.StudySpaceApp.something",
                        "HELLO WORLD");
                startActivity(filterIntent);
            }
        });



    }
}
