package com.imperial.london.yourjourney.model;


/**
 * {@Event} represents an earthquake event. It holds the details
 * of that event such as title (which contains magnitude and location
 * of the earthquake), as well as time, and whether or not a tsunami
 * alert was issued during the earthquake.
 */
public class Journey {

    /**
     * Time that the earthquake happened (in milliseconds)
     */
    public int time;
    public String journeyInstructions = "Jubilee Line to Green Park";

    //Singleton Support
    private static Journey instance;

    //To ensure there is only one copy
    private Journey() {
        //private to prevent anyone else from instantiating
    }

    //If someone calls on user profile, check if one already exists
    public static Journey getInstance() {
// If a user profile has not already been created
        if (instance == null) {
            instance = new Journey();
        }
        return instance;

    }


    public Journey(int duration, String instructions) {
        time = duration;
        journeyInstructions = instructions;
    }
}





