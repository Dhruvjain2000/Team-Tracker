package app.tracker.android.teamtracker.Model;

public class DMS {
    String lat,lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public DMS(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
