package com.imperial.london.yourjourney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_detail);

        //declare the strings with station names and info here
        String[] stations = getResources().getStringArray(R.array.stationList);
        String[] times = getResources().getStringArray(R.array.busiestTimes);
        String[] lines = getResources().getStringArray(R.array.availableLines);

        //Get that value of item in the listView from the directory activity
        Intent i = getIntent();
        int listItem = i.getIntExtra("value", 0);

        //Get the corresponding station name to the position
        String title = stations[listItem];

        //Set the title to that station name
        TextView stationTitle = (TextView) findViewById(R.id.stationName) ;
        stationTitle.setText(title);

        String time = times[listItem];

        TextView timeTextView = (TextView) findViewById(R.id.time) ;
        timeTextView.setText(time);

        String line = lines[listItem];

        TextView lineTextView = (TextView) findViewById(R.id.lines) ;
        lineTextView.setText(line);



        // Find the View that shows the Back Button
        Button backButton = (Button) findViewById(R.id.backButton);

        // Set a click listener on that View
        backButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the back button is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link Main Activity}
                Intent mainIntent = new Intent(StationDetailActivity.this, MainActivity.class);

                // Start the new activity
                startActivity(mainIntent);
            }
        });
    }
}
