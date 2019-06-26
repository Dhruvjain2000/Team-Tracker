package app.tracker.android.teamtracker.Model;

public class UploadReport {
    private String name;
    private int age;
    private String gender;
    private String pincode;
    private String state;
    private String uid;
    private String city;
    private String lat, lon;


    public UploadReport() {
    }

    public UploadReport(String name, int age, String gender, String pincode, String state, String uid, String city, String lat, String lon) {

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.pincode = pincode;
        this.state = state;
        this.uid = uid;
        this.city = city;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
