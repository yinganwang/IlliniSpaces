package com.example.vivianfca.studyspaceapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {

    public static TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        data = (TextView) findViewById(R.id.textView);

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
}
