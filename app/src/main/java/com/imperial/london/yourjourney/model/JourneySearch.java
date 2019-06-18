package com.imperial.london.yourjourney.model;

import java.util.Random;

public class JourneySearch {

    private String startingStation;
    private String destination;
    private String url = "https://api.tfl.gov.uk/journey/journeyresults/1000266/to/1000013";
    private int maxStairs;
    private int stairsRow;
    private int stairsColumn;
    private int[][] stairsArray;
    private String accessibility = "NoRequirements";


    //Singleton Support
    private static JourneySearch instance;

    //To ensure there is only one copy
    private JourneySearch() {
        //private to prevent anyone else from instantiating
    }

    //If someone calls on user profile, check if one already exists
    public static JourneySearch getInstance() {
// If a user profile has not already been created
        if (instance == null) {
            instance = new JourneySearch();
        }
        return instance;

    }

    public String getStartingStation() {
        return startingStation;
    }

    public void setStartingStation(String firstStation) {
        this.startingStation = firstStation;
    }

    public void setStairsArray() {

        for (int r = 0; r < 10; r++) {

            for (int c = 0; c < 10; c++) {

                final int min = 0;
                final int max = 100;
                final int random = new Random().nextInt((max - min) + 1) + min;

                this.stairsArray[r][c] = random;
            }
        }
    }

    public int getStairsArray (int row, int column){
        return stairsArray[row][column];
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String Destination) {
        this.destination = Destination;
    }


    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String Accessibility) {
        this.accessibility = Accessibility;
    }







    public String getURL() {
        return url;
    }

    public void setURL() {
        this.url = "https://api.tfl.gov.uk/journey/journeyresults/" + startingStation + "UndergroundStation/to/" + destination +"UndergroundStation?accessibilityPreference" + accessibility + "&app_id=cc410ca0&app_key=e7de367c6e2cbafaae28252ca9cc3f06";
    }




    public int getStairs() {return maxStairs;}

    public void setStairs(int stairs) {
        this.maxStairs = stairs;
    }





    public int getStairsRow() {return stairsRow;}

    public void setStairsRow(int stairs) {this.stairsRow = stairs;}





    public int getStairsColumn() {return stairsColumn;}

    public void setStairsColumn(int stairs) {
        this.stairsColumn = stairs;
    }


}



