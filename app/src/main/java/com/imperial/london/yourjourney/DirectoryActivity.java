package com.imperial.london.yourjourney;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DirectoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);


        // Create a string of stations from array stored in strings.xml
        String[] stations = getResources().getStringArray(R.array.stationList);
        //ArrayList<String> wordsList = new ArrayList<String>(Arrays.asList(words));

        //Because we want to recycle to views, we convert our string array into an Array Adapter
        ArrayAdapter<String> stationsAdapter =
                new ArrayAdapter<String>(this, R.layout.list_item, R.id.oneText, stations);

        //Initialise the listView and then fill it with our list of stations
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(stationsAdapter);

        //Making clicking on a station send you to its page
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //When clicked starts a new intent and passes the position in the listView that click was in
                Intent showStationDetail = new Intent(getApplicationContext(), StationDetailActivity.class);
                showStationDetail.putExtra("value", position);
                startActivity(showStationDetail);
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
                Intent mainIntent = new Intent(DirectoryActivity.this, MainActivity.class);

                // Start the new activity
                startActivity(mainIntent);
            }
        });
    }
}
