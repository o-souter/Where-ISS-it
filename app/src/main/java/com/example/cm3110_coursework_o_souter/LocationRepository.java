/*package com.example.cm3110_coursework_o_souter;

import android.content.Context;

import java.util.ArrayList;

public class LocationRepository {
    private LocationDAO repoLocationDAO;

    public LocationRepository(Context context) {
        super();
        repoLocationDAO = LocationDatabase.getInstance(context).locationDAO();
    }
    //Stores locations locally on the phone in a RoomSQL database
    public void storeLocations(ArrayList<Location> locations) {
        this.repoLocationDAO.insert(locations);
    }
    //Deletes locations locally on the phone from RoomSQL database
    public void deleteLocations(ArrayList<Location> locations) {
        this.repoLocationDAO.delete(locations);
    }
    //Stores one location locally on the phone in a RoomSQL database
    public void storeLocations(Location location) {
        this.repoLocationDAO.insert(location);
    }
    //Deletes one location locally on the phone from RoomSQL database
    public void deleteLocations(Location location) {
        this.repoLocationDAO.delete(location);
    }

    public ArrayList<Location> getLocations(String location, String timeStamp) {
        return this.repoLocationDAO.findByTime(location, timeStamp);
    }
}
*/