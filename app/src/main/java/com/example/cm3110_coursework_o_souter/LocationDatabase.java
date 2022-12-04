package com.example.cm3110_coursework_o_souter;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Location.class}, version = 1)
public abstract class LocationDatabase extends RoomDatabase {

    public abstract LocationDAO locationDAO();

    private static LocationDatabase INSTANCE;

    public static LocationDatabase getInstance(final Context context){
        if (INSTANCE == null) {
            synchronized (LocationDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, LocationDatabase.class, "LocationDatabase")
                            .allowMainThreadQueries().fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

    private LocationDatabase() {
        super();
    }
}
