package com.example.cm3110_coursework_o_souter;

import java.util.ArrayList;

public class Meteor {
    private String name;
    private String date;
    private String lat;
    private String lon;
    private boolean hazard;

    public Meteor(String name, boolean hazard, String date) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.date = date;
        this.hazard = hazard;
    }

    public String getName() {
        return this.name;
    }

    public String getLatitude() {
        return this.lat;
    }

    public String getLongitude() {
        return this.lon;
    }

    public String getDate() {
        return this.date;
    }

    public boolean getHazardous() {
        return this.hazard;
    }

    @Override
    public String toString() {
        return "Meteor name: " + getName() + ", Hazardous:  " + getHazardous() + ", Date: " + getDate();
    }


}
