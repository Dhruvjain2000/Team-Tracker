package com.example.android.teamtracker.RequestParts;

public class UploadRequest {
    String name;
    String partRequest;
    int count;
    String city;
    int pincode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartRequest() {
        return partRequest;
    }

    public void setPartRequest(String partRequest) {
        this.partRequest = partRequest;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public UploadRequest(String name, String partRequest, int count, String city, int pincode) {
        this.name = name;
        this.partRequest = partRequest;
        this.count = count;
        this.city = city;
        this.pincode = pincode;
    }
}

