package com.imperial.london.yourjourney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.imperial.london.yourjourney.model.UserProfile;

public class PreferencesActivity extends AppCompatActivity {

    private UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        userProfile = UserProfile.getInstance();

        String stairs = Integer.toString(userProfile.getNumberOfSteps());
        TextView stairsTest = (TextView) findViewById(R.id.stepsNumber) ;

        if (userProfile.getNumberOfSteps() == 100){
            stairsTest.setText("No preference");
        }
        else{
            stairsTest.setText(stairs);
        }


        String preference = userProfile.getPreference();
        TextView stepPref = (TextView) findViewById(R.id.sFAnswer);
        stepPref.setText(preference);


        // Find the View that shows the Statio Directory Category
        Button editPrefButton = (Button) findViewById(R.id.editPreferences);

        // Set a click listener on that View
        editPrefButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the station directory is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link DirectoryActivity}
                Intent editPrefIntent = new Intent(PreferencesActivity.this, UserInputActivity.class);

                // Start the new activity
                startActivity(editPrefIntent);
            }
        });



    }


}
