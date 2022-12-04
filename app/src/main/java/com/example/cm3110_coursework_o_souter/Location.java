package com.example.cm3110_coursework_o_souter;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.time.LocalTime;
import java.util.Calendar;

@Entity(tableName="location")
public class Location {
    Calendar dateCalendar = Calendar.getInstance();
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String latitude;
    private String longitude;
    private String location;
    private String timeStamp = dateCalendar.getTime().toString();

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
    public String getTimeStamp() {
        return timeStamp;
    }


    @Override
    public String toString() {
        return "Latitude: " + latitude +
                "\nLongitude:  " + longitude +
                "\nActual Location: " + location +
                "\nTimestamp: " + timeStamp;
    }


}
