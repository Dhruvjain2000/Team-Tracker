package app.tracker.android.teamtracker.Model;

public class LoginHistory {
    private  String email;
    private String dateAndtime;
    private String location;

    public LoginHistory(String email, String dateAndtime, String location) {
        this.email = email;
        this.dateAndtime = dateAndtime;
        this.location = location;
    }

    public LoginHistory() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
