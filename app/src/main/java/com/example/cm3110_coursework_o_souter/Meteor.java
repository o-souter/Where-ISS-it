package com.example.cm3110_coursework_o_souter;

import java.util.ArrayList;

public class Meteor {
    private String name;
    private int year;
    private float lat;
    private float lon;

    public Meteor(String name, float lat, float lon, int year) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.year = year;
    }

    public String getName() {
        return this.name;
    }
    public float getLatitude() {
        return this.lat;
    }
    public float getLongitude() {
        return this.lon;
    }
    public float getYear() {
        return this.year;
    }


}
