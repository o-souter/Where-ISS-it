package com.example.cm3110_coursework_o_souter.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cm3110_coursework_o_souter.Meteor;

@Database(entities = {Meteor.class}, version = 1)
public abstract class MeteorDatabase extends RoomDatabase {
    public abstract MeteorDAO meteorDAO();

    private static MeteorDatabase INSTANCE;

    public static MeteorDatabase getInstance(final Context ctx){
        if (INSTANCE == null) {
            synchronized (MeteorDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ctx, MeteorDatabase.class, "MeteorDatabase")
                            .allowMainThreadQueries().fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
    //private MeteorDatabase(){
    //    super();
    //}

}
