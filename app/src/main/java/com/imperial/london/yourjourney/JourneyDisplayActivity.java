package com.imperial.london.yourjourney;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.imperial.london.yourjourney.model.Journey;
import com.imperial.london.yourjourney.model.JourneySearch;
import com.imperial.london.yourjourney.model.UserProfile;

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
import java.nio.charset.Charset;
import java.util.Random;


public class JourneyDisplayActivity extends AppCompatActivity {

    private JourneySearch journeySearch;
    private Journey journey;
    private UserProfile userProfile;
    private int[][] stairsTable = new int[65][65];

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = JourneyDisplayActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_display);

        journeySearch = JourneySearch.getInstance();
        journey = Journey.getInstance();
        userProfile = UserProfile.getInstance();

/**
        //Initialising the 2D stairs array with random numbers, testing out using an array to store stairs data
        //could possibly put this into a class
        for (int r = 0; r < 20; r++) {

            for (int c = 0; c < 20; c++) {

                final int min = 0;
                final int max = 100;
                final int random = new Random().nextInt((max - min) + 1) + min;

                stairsTable[r][c] = random;
            }
        }

        // stairsTable[49][5] = 21;
        stairsTable[3][5] = 42;
*/
        // Kick off an {@link AsyncTask} to perform the network request
        JourneyAsyncTask task = new JourneyAsyncTask();
        task.execute();
    }


    /**
     * Update the screen to display information from the given {@link Journey}.
     */
    private void updateUi(Journey journey) {
        String journeyOutline = journeySearch.getStartingStation()
                + " Underground Station \nto \n"
                + journeySearch.getDestination()
                + " Underground Station";
        //Todo - Change line 69 and 71 (journeySearch.gets) and replace them with values from JSON response so that spaces can be added after being removed for URL
        TextView journeyDisplayTitle = (TextView) findViewById(R.id.journeyDisplayTitle);
        journeyDisplayTitle.setText(journeyOutline);


        String time = String.valueOf(journey.time);

        TextView duration = (TextView) findViewById(R.id.startingStationTitle);
        duration.setText("Journey Time: \n" + time + " minutes");


        String instruction = journey.journeyInstructions;

        TextView steps = (TextView) findViewById(R.id.destinationStationTitle);
        steps.setText("Instructions:\n" + instruction);


        int row = journeySearch.getStairsRow();
        int column = journeySearch.getStairsColumn();


        Log.d(LOG_TAG, "The stairs number is " + stairsTable[3][5]);
        Log.d(LOG_TAG, "The current stairs number is " + stairsTable[row][column]);

        //Generating a random number for the 2D stairs array
        final int min = 0;
        final int max = 100;
        final int random = new Random().nextInt((max - min) + 1) + min;

        stairsTable[row][column] = random;
        stairsTable[47][5] = 79;




        journeySearch.setStairs(stairsTable[row][column]);
        String stairs = "Number of stairs is: " + journeySearch.getStairs();


        int maxStairsPreference = userProfile.getNumberOfSteps();
        int current = journeySearch.getStairs();
        // int current = journeySearch.getStairsArray(1,2);

        if (maxStairsPreference < current) {
            stairs += "\n(WARNING: This is over your maximum step preference)";
        }

        TextView stairsTextView = (TextView) findViewById(R.id.stairsTitle);
        stairsTextView.setText(stairs);
    }


    private class JourneyAsyncTask extends AsyncTask<URL, Void, Journey> {

        @Override
        protected Journey doInBackground(URL... urls) {

            String TFL_REQUEST_URL = journeySearch.getURL();
            Log.d(LOG_TAG, "The URL is " + journeySearch.getURL());

            // Create URL object
            URL url = createUrl(TFL_REQUEST_URL);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem with IO", e);
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            Journey displayedJourney = extractFeatureFromJson(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return displayedJourney;
        }


        /**
         * Update the screen with the given earthquake (which was the result of the
         * {@link JourneyAsyncTask}).
         */
        @Override
        protected void onPostExecute(Journey journey) {
            if (journey == null) {
                return;
            }

            updateUi(journey);
        }


        /**
         * Returns new URL object from the given string URL.
         */
        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                //Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }


        /**
         * Make an HTTP request to the given URL and return a String as the response.
         */
        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";

            //Want to check if URL is null first, if it is then we shouldn't try to make the Http request

            //If URL is null, return early with a JSON response that is just an empty string
            if (url == null) {
                return jsonResponse;
            }
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                //Setting up HTTP request
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);

                //Establish HTTP connection with the server
                urlConnection.connect();

                //Receiving response and making sense of it for the app
                //Check the response code to see if connection was successful (200 = successful and 4xx is not)
                if (urlConnection.getResponseCode() == 200) {
                    //If connection is successful now we proceed to read from the input stream and extract the JSON response
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);

                    //Otherwise if there is an error response (i.e. not 200) then we do nothing and let the empty string be returned
                } else {
                    Log.e(LOG_TAG, "Error Response Code: " + urlConnection.getResponseCode());
                }

            } catch (IOException e) {

                Log.e(LOG_TAG, "Problem with IO", e);

            } finally { //Finally will always be executed
                //We disconnect the internet connection and input stream to save memory in app for other tasks
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;

        }


        /**
         * Convert the {@link InputStream} into a String which contains the
         * whole JSON response from the server.
         */
        //More about this in Lesson 2 HTTP Networking part 17
        private String readFromStream(InputStream inputStream) throws IOException {

            //Stringbuilder is a modifyable string, perfect for building responses
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }


        /**
         * Return an {@link Journey} object by parsing out information
         * about the first earthquake from the input earthquakeJSON string.
         */
        private Journey extractFeatureFromJson(String tflJSON) {
            //Need to check if JSON string is empty first (in case there was a bad connection) and handle appropriately
            if (TextUtils.isEmpty(tflJSON)) {
                return null;
            }

            try {
                JSONObject baseJsonResponse = new JSONObject(tflJSON);
                JSONArray journeysArray = baseJsonResponse.getJSONArray("journeys");

                // If there are results in the features array
                for (int i = 0; i < journeysArray.length(); i++) {
                    JSONObject firstJourney = journeysArray.getJSONObject(0);

                    int time = firstJourney.getInt("duration");
                    Log.d(LOG_TAG, "The value of duration is: " + time);

                    JSONArray journeyDetailsArray = firstJourney.getJSONArray("legs");


                    Log.d(LOG_TAG, "It's not this line that's crashing it");

                    StringBuilder summary = new StringBuilder();


                    for (int j = 0; j < journeyDetailsArray.length(); j++) {

                        JSONObject firstStep = journeyDetailsArray.getJSONObject(j);

                        JSONObject instruction = firstStep.getJSONObject("instruction");

                        int stepNumber = j + 1;

                        summary.append("\n" + stepNumber + ". " + instruction.getString("summary"));


                    }
                    // Create a new {@link Journey} object
                    return new Journey(time, summary.toString());
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the TFL JSON results", e);
            }
            return null;

        }


    }


}