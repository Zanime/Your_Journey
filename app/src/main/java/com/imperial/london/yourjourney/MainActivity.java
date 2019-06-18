package com.imperial.london.yourjourney;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.imperial.london.yourjourney.model.JourneySearch;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    //Creating a string array variable to put in the station spinners
    String[] options;
    String startingStation;
    String destinationStation;
    private JourneySearch journeySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        journeySearch = JourneySearch.getInstance();


        //Creating Spinner for Starting Stations
        final Spinner spinnerOne = (Spinner) findViewById(R.id.startingStationSpinner);

        // Creating ArrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapterOne = ArrayAdapter.createFromResource(this,
                R.array.stationList, android.R.layout.simple_spinner_item);


        // Specify layout to be used when list of choices appears
        adapterOne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Applying the adapter to our spinner
        spinnerOne.setAdapter(adapterOne);
        spinnerOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, " You've selected: " + options[position], Toast.LENGTH_SHORT).show();
                journeySearch.setStairsRow(spinnerOne.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Creating Spinner for Destination Stations
        final Spinner spinnerTwo = (Spinner) findViewById(R.id.destinationStationSpinner);

        // Creating ArrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapterTwo = ArrayAdapter.createFromResource(this,
                R.array.stationList, android.R.layout.simple_spinner_item);


        // Specify layout to be used when list of choices appears
        adapterTwo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Applying the adapter to our spinner
        spinnerTwo.setAdapter(adapterTwo);
        spinnerTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, " You select >> " + options[position], Toast.LENGTH_SHORT).show();
                journeySearch.setStairsColumn(spinnerTwo.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        options = MainActivity.this.getResources().getStringArray(R.array.stationList);


        // Find the View that shows the Station Directory Category
        Button goButton = (Button) findViewById(R.id.goButton);

        // Set a click listener on that View
        goButton.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the station directory is clicked on.
            @Override
            public void onClick(View view) {


                //Get spinner position and link to  stairs/gap array
                int row = spinnerOne.getSelectedItemPosition();
                //  Log.d(LOG_TAG, "The row number is " + row);
                journeySearch.setStairsRow(row);

                //Take contents from spinner and put it into journeySearch class
                startingStation = spinnerOne.getSelectedItem().toString();


                switch (startingStation) {
                    case "Aldgate":
                        journeySearch.setStartingStation("1000003");
                        break;
                    case "Paddington":
                        journeySearch.setStartingStation("1000174");
                        break;
                    case "Elephant & Castle":
                        journeySearch.setStartingStation("1000073");
                        break;
                    default:
                        //If station is made up of more than one words then separate them and remove the space for use in the URL
                        if (startingStation.contains(" ")) {
                            String[] separatedStation = startingStation.split(" ");
                            StringBuilder firstStation = new StringBuilder();

                            for (int s = 0; s < separatedStation.length; s++) {

                                String stationWord = separatedStation[s];
                                firstStation.append(stationWord);
                            }


                            journeySearch.setStartingStation(firstStation.toString());


                        } else {
                            journeySearch.setStartingStation(startingStation);
                        }

                }


                int column = spinnerTwo.getSelectedItemPosition();
                Log.d(LOG_TAG, "The column number is " + column);

                journeySearch.setStairsColumn(column);

                //Take contents from spinner and put it into journeySearch class#
                destinationStation = spinnerTwo.getSelectedItem().toString();


                switch (destinationStation) {
                    case "Aldgate":
                        journeySearch.setDestination("1000003");
                        break;
                    case "Paddington":
                        journeySearch.setDestination("1000174");
                        break;

                    case "Elephant & Castle":
                        journeySearch.setDestination("1000073");
                        break;
                    default:
                        //If station is made up of more than one words then separate them and remove the space for use in the URL
                        if (destinationStation.contains(" ")) {
                            String[] separatedStation = destinationStation.split(" ");
                            StringBuilder lastStation = new StringBuilder();

                            for (int w = 0; w < separatedStation.length; w++) {

                                String stationWord = separatedStation[w];
                                lastStation.append(stationWord);
                            }


                            journeySearch.setDestination(lastStation.toString());


                        } else {
                            journeySearch.setDestination(destinationStation);
                        }

                }

                if (row == column) {
                    Toast.makeText(MainActivity.this, "Please Enter Two Different Stations", Toast.LENGTH_SHORT).show();
                } else if (row == 0|| column == 0) {
                    Toast.makeText(MainActivity.this, "Please Enter Both Stations", Toast.LENGTH_SHORT).show();
                } else {
                    //Use input data to create the URL
                    journeySearch.setURL();


                    // Create a new intent to open the {@link DirectoryActivity}
                    Intent journeyDisplayIntent = new Intent(MainActivity.this, JourneyDisplayActivity.class);

                    // Start the new activity
                    startActivity(journeyDisplayIntent);
                }

            }
        });


        // Find the View that shows the Statio Directory Category
        Button settingsButton = (Button) findViewById(R.id.settingsButton);

        // Set a click listener on that View
        settingsButton.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the station directory is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link DirectoryActivity}
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);

                // Start the new activity
                startActivity(settingsIntent);
            }
        });

        // Find the View that shows the Statio Directory Category
        Button directoryButton = (Button) findViewById(R.id.stationDirectory);

        // Set a click listener on that View
        directoryButton.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the station directory is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link DirectoryActivity}
                Intent directoryIntent = new Intent(MainActivity.this, DirectoryActivity.class);

                // Start the new activity
                startActivity(directoryIntent);
            }
        });
    }


    //Disabling back button
    @Override
    public void onBackPressed() {
    }

}