package com.example.android.teamtracker.Model;

public class LoginHistory {
    private  String email;
    private String dateAndtime;

    public LoginHistory(String email, String dateAndtime) {
        this.email = email;
        this.dateAndtime = dateAndtime;
    }

    public LoginHistory() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateAndtime() {
        return dateAndtime;
    }

    public void setDateAndtime(String dateAndtime) {
        this.dateAndtime = dateAndtime;
    }
}
