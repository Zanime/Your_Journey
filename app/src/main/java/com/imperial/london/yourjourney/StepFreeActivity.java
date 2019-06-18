package com.imperial.london.yourjourney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.imperial.london.yourjourney.model.JourneySearch;
import com.imperial.london.yourjourney.model.UserProfile;

public class StepFreeActivity extends AppCompatActivity {

    private UserProfile userProfile;
    private JourneySearch journeySearch;
    private Button stepGoButton;
    RadioGroup rg;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_free);

        userProfile = UserProfile.getInstance();
        journeySearch = JourneySearch.getInstance();
        rg = (RadioGroup) findViewById(R.id.stepRadioGroup);


        stepGoButton = (Button) findViewById(R.id.stepGoButton);
        //stepGoButton.setEnabled(false);

        // Set a click listener on that View
        stepGoButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the go button is clicked on.
            @Override
            public void onClick(View view) {


                // Create a new intent to open the {@link MainActivity}
                Intent stepGoIntent = new Intent(StepFreeActivity.this, MainActivity.class);

                // Start the new activity
                startActivity(stepGoIntent);
            }
        });


        // Find the View that shows the skip button
        Button stepSkipButton = (Button) findViewById(R.id.stepSkipButton);

        // Set a click listener on that View
        stepSkipButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the skip button is clicked on.
            @Override
            public void onClick(View view) {

                userProfile.setPreference("No Preference");
                journeySearch.setAccessibility("NoRequirements");


                // Create a new intent to open the {@link MainActivity}
                Intent stepSkipIntent = new Intent(StepFreeActivity.this, MainActivity.class);

                // Start the new activity
                startActivity(stepSkipIntent);
            }
        });
    }

    //Assigning what happens when we click the radio button
    public void rbClick(View v) {
        int radiobuttonid = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radiobuttonid);
        String access = rb.getText().toString();

        //If station is made up of more than one words then separate them and remove the space for use in the URL
        if (access.contains(" ")) {
            String[] splitAccess = access.split(" ");
            StringBuilder finalAccess = new StringBuilder();

            for (int s = 0; s < splitAccess.length; s++) {

                String tempAccess = splitAccess[s];
                finalAccess.append(tempAccess);
            }

            userProfile.setPreference(access);


            journeySearch.setAccessibility(finalAccess.toString());


        }
    }

}
