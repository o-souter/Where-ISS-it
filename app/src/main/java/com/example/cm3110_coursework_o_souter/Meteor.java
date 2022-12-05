package com.example.cm3110_coursework_o_souter;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
@Entity(tableName="Meteor")
public class Meteor {
    @NonNull
    @PrimaryKey(autoGenerate=true)
    private int uid;

    private String name;
    private String date;
    //private String lat;
    //private String lon;
    private boolean hazard;
    private long diameter;

    public int getUid() {
        return this.uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public Meteor(String name, boolean hazard, String date, long diameter) {
        //this.uid = uid;
        this.name = name;
        //this.lat = lat;
        //this.lon = lon;
        this.date = date;
        this.hazard = hazard;
        this.diameter = diameter;
    }

    public String getName() {
        return this.name;
    }

    //public String getLatitude() {
    //    return this.lat;
    //}

    //public String getLongitude() {
    //    return this.lon;
    //}

    public String getDate() {
        return this.date;
    }

    public boolean getHazard() {
        return this.hazard;
    }

    public long getDiameter() {
        return this.diameter;
    }

    @Override
    public String toString() {
        return "Meteor name: " + getName() + ", Hazardous:  " + getHazard() + ", Date: " + getDate() + ", Diameter: " + getDiameter();
    }


}
