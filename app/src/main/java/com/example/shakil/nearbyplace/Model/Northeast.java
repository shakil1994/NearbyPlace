package com.example.shakil.nearbyplace.Model;

/**
 * Created by Shakil on 2/5/2018.
 */

public class Northeast {
    private String lng;
    private String lat;

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "ClassPojo [lng = "+lng+", lat = "+lat+"]";
    }
}
