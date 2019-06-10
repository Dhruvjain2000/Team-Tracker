package com.example.android.teamtracker.Forms;

public class Initial {
    String name;
    String currentLocation;
    String clientLocation;
    String clientName;
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

    public String getClientLocation() {
        return clientLocation;
    }

    public void setClientLocation(String clientLocation) {
        this.clientLocation = clientLocation;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Initial(String name, String currentLocation, String clientLocation, String clientName, String date) {

        this.name = name;
        this.currentLocation = currentLocation;
        this.clientLocation = clientLocation;
        this.clientName = clientName;
        this.date = date;
    }
}
