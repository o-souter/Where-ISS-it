package com.example.cm3110_coursework_o_souter;
//Location Object
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;


public class Location {
    private String latitude; //Value for Latitude
    private String longitude; //Value for Longitude
    private String location; //String for location string

    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    @Override
    public String toString() {//toString method
        return "Latitude: " + latitude +
                "\nLongitude:  " + longitude +
                "\nActual Location: " + location;
    }


}
