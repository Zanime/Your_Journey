package com.imperial.london.yourjourney;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.imperial.london.yourjourney.model.UserProfile;

public class WelcomeActivity extends AppCompatActivity {

    private UserProfile userProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

/** Code for trying to change the launcher activity dynamically
        userProfile = UserProfile.getInstance();

        if (userProfile.getFirstTime() == 1)
        {
            // Create a new intent to open the {@link DirectoryActivity}
            Intent startIntent = new Intent(WelcomeActivity.this, MainActivity.class);

            // Start the new activity
            startActivity(startIntent);
        }

*/
        // Find the View that shows the start button
        Button startButton = (Button) findViewById(R.id.startButton);

        // Set a click listener on that View
        startButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the start button is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link DirectoryActivity}
                Intent startIntent = new Intent(WelcomeActivity.this, MainActivity.class);

                // Start the new activity
                startActivity(startIntent);
            }
        });




        // Find the View that shows the sign up button
        Button signUpButton = (Button) findViewById(R.id.signUpButton);

        // Set a click listener on that View
        signUpButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the start button is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the user input
                Intent signUpIntent = new Intent(WelcomeActivity.this, UserInputActivity.class);

                // Start the new activity
                startActivity(signUpIntent);
            }
        });
    }
}
