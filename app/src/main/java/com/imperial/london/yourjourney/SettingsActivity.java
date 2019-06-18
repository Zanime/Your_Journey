package com.imperial.london.yourjourney;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        // Find the View that shows the Statio Directory Category
        Button editProfileButton = (Button) findViewById(R.id.editProfile);

        // Set a click listener on that View
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the station directory is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link DirectoryActivity}
                Intent editProfileIntent = new Intent(SettingsActivity.this, PreferencesActivity.class);

                // Start the new activity
                startActivity(editProfileIntent);
            }
        });


        // Find the View that shows the Statio Directory Category
        Button downloadedButton = (Button) findViewById(R.id.downloadedJourneys);

        // Set a click listener on that View
        downloadedButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the station directory is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link DirectoryActivity}
                Intent downloadedIntent = new Intent(SettingsActivity.this, DownloadedActivity.class);

                // Start the new activity
                startActivity(downloadedIntent);
            }
        });






        // Find the View that shows the Statio Directory Category
        Button tubeMapButton = (Button) findViewById(R.id.tubeMap);

        // Set a click listener on that View
        tubeMapButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the station directory is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link DirectoryActivity}
                Intent tubeMapIntent = new Intent(SettingsActivity.this, TubeMapActivity.class);

                // Start the new activity
                startActivity(tubeMapIntent);
            }
        });









        // Find the View that shows the Statio Directory Category
        Button directoryButton = (Button) findViewById(R.id.station_Directory);

        // Set a click listener on that View
        directoryButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the station directory is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link DirectoryActivity}
                Intent directoryIntent = new Intent(SettingsActivity.this, DirectoryActivity.class);

                // Start the new activity
                startActivity(directoryIntent);
            }
        });









        // Find the View that shows the Back Button
        Button backButton = (Button) findViewById(R.id.backButton);

        // Set a click listener on that View
        backButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the back button is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link Main Activity}
                Intent mainIntent = new Intent(SettingsActivity.this, MainActivity.class);

                // Start the new activity
                startActivity(mainIntent);
            }
        });
    }
}
