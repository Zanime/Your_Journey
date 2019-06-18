package com.imperial.london.yourjourney.model;

public class UserProfile {

    private int numberOfSteps = 100;
    private int firstTime = 0;
    private String preference = "NoRequirements";

    //Singleton Support
    private static UserProfile instance;

    //To ensure there is only one copy
    private UserProfile() {
        //private to prevent anyone else from instantiating
    }

    //If someone calls on user profile, check if one already exists
    public static UserProfile getInstance() {
// If a user profile has not already been created
        if (instance == null) {
            instance = new UserProfile();
        }
        return instance;

    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }



    public void setFirstTime() {
        this.firstTime = 1;
    }

    public int getFirstTime() {
        return firstTime;
    }



    public String getPreference() {
        return preference;
    }

    public void setPreference(String pref) {
        this.preference = pref;
    }
}



