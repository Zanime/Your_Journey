package com.imperial.london.yourjourney;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DownloadedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded);


        TextView downloadedTitle = (TextView) findViewById(R.id.errorMessage) ;
        downloadedTitle.setText("None to Display");



        // Find the View that shows the Back Button
        Button backButton = (Button) findViewById(R.id.backButton);

        // Set a click listener on that View
        backButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the back button is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link Main Activity}
                Intent mainIntent = new Intent(DownloadedActivity.this, MainActivity.class);

                // Start the new activity
                startActivity(mainIntent);
            }
        });
    }
}
