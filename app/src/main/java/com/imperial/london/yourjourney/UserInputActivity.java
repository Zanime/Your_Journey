package com.imperial.london.yourjourney;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.imperial.london.yourjourney.model.MinMaxFilter;
import com.imperial.london.yourjourney.model.UserProfile;

public class UserInputActivity extends AppCompatActivity {

    private UserProfile userProfile;
    private EditText stepsInput;
    private Button stairsGoButton;
    private TextWatcher stairsTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String stairsInput = stepsInput.getText().toString().trim();

            //If the user has typed something in then allow the button to be pressed

            if (stairsInput.length() == 0) {
                stairsGoButton.setEnabled(false);
            } else {
                stairsGoButton.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);


        userProfile = UserProfile.getInstance();
        stepsInput = (EditText) findViewById(R.id.stairAnswer);
        stairsGoButton = (Button) findViewById(R.id.stairsGoButton);
        stairsGoButton.setEnabled(false);

        //Preventing user from inputing anything under 0 or above 100
        stepsInput.setFilters(new InputFilter[]{ new MinMaxFilter("0", "99")});

        //Creating a function to disable button when the user hasn't put any stairs in
        stepsInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().trim().length() == 0) {
                    stairsGoButton.setEnabled(false);

                } else {
                    stairsGoButton.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // Set a click listener on that View
        stairsGoButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the go button is clicked on.
            @Override
            public void onClick(View view) {


                int steps = Integer.parseInt(stepsInput.getText().toString());

                userProfile.setNumberOfSteps(steps);

                userProfile.setFirstTime();
                // Create a new intent to open the {@link MainActivity}
                Intent stairsGoIntent = new Intent(UserInputActivity.this, StepFreeActivity.class);

                // Start the new activity
                startActivity(stairsGoIntent);
            }
        });


        // Find the View that shows the skip button
        Button stairsSkipButton = (Button) findViewById(R.id.stairsSkipButton);

        // Set a click listener on that View
        stairsSkipButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the skip button is clicked on.
            @Override
            public void onClick(View view) {

                userProfile.setFirstTime();

                // Create a new intent to open the {@link MainActivity}
                Intent stairsSkipIntent = new Intent(UserInputActivity.this, StepFreeActivity.class);

                // Start the new activity
                startActivity(stairsSkipIntent);
            }
        });

    }
}
