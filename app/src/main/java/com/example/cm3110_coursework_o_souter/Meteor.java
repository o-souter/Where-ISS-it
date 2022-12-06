package com.example.cm3110_coursework_o_souter;
//Meteor Object
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
@Entity(tableName="Meteor")
public class Meteor {
    @NonNull
    @PrimaryKey(autoGenerate=true)
    private int uid; //ID for database

    private String name; //String for name
    private String date; //String for date
    private boolean hazard; //Boolean for hazardous or not
    private long diameter; //Long for diameter

    public int getUid() {
        return this.uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public Meteor(String name, boolean hazard, String date, long diameter) {
        this.name = name;
        this.date = date;
        this.hazard = hazard;
        this.diameter = diameter;
    }

    public String getName() {
        return this.name;
    }

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
    public String toString() {//toString method
        return "Meteor name: " + getName() + ", Hazardous:  " + getHazard() + ", Date: " + getDate() + ", Diameter: " + getDiameter();
    }


}
