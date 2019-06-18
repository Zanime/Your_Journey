package com.imperial.london.yourjourney;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.imperial.london.yourjourney.model.UserProfile;

public class LauncherActivity extends AppCompatActivity {

    private UserProfile userprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        userprofile = UserProfile.getInstance();
        int check = userprofile.getFirstTime();

        // determine where to go
        if(check == 0) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


    }
}
