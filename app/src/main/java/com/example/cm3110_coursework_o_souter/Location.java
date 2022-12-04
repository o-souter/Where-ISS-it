package com.example.cm3110_coursework_o_souter;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

@Entity(tableName="location")
public class Location {
    //Date dateCalendar = LocalDateTime.now();
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String latitude;
    private String longitude;
    private String location;
    private String timeStamp ="bruh "; //dateCalendar.getTime().toString();" //need to work on this!

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
    public int getUid() {
        return this.uid;
    }
    public void setUid(int uid){
        this.uid = uid;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }


    @Override
    public String toString() {
        return "Latitude: " + latitude +
                "\nLongitude:  " + longitude +
                "\nActual Location: " + location +
                "\nTimestamp: "; //+ timeStamp;
    }


}
