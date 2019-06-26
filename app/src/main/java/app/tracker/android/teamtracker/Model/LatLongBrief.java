package app.tracker.android.teamtracker.Model;

public class LatLongBrief {
    private DMS latLong;

    public DMS getLatLong() {
        return latLong;
    }

    public void setLatLong(DMS latLong) {
        this.latLong = latLong;
    }

    public LatLongBrief(DMS latLong) {

        this.latLong = latLong;
    }
}
