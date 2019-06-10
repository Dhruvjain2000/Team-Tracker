package com.example.android.teamtracker.Forms;

public class Final {
    String name;
    String currentLocation;
    String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Final(String name, String currentLocation, String date) {

        this.name = name;
        this.currentLocation = currentLocation;
        this.date = date;
    }
}
