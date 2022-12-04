package com.example.cm3110_coursework_o_souter;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Dao
public interface LocationDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Location location);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(ArrayList<Location> locations);

    @Delete
    public void delete(Location location);

    @Delete
    public void delete(ArrayList<Location> locations);

    @Query("SELECT * FROM location WHERE location LIKE :location AND timeStamp LIKE :timeStamp ORDER BY timeStamp ASC")
    public ArrayList<Location> findByTime(String location, String timeStamp);


    //@Query("SELECT * from location")
}
