package com.example.cm3110_coursework_o_souter.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cm3110_coursework_o_souter.Meteor;

import java.util.List;

@Dao
public interface MeteorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Meteor m);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<Meteor> meteorList);

    @Delete
    public void delete(Meteor m);

    @Delete
    public void delete(List<Meteor> meteorList);

    @Query("SELECT * FROM meteor WHERE date LIKE :date")
    public List<Meteor> getTodaysMeteors(String date);

}
