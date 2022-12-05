package com.example.cm3110_coursework_o_souter.data;

import android.content.Context;

import com.example.cm3110_coursework_o_souter.Meteor;

import java.util.List;

public class MeteorRepo {
    private MeteorDAO repoMeteorDAO;

    public MeteorRepo(Context ctx) {
        super();
        repoMeteorDAO = MeteorDatabase.getInstance(ctx).meteorDAO();
    }

    //Method to add a list of meteors to the database stored locally on device
    public void storeMeteors(List<Meteor> meteorList) {
        this.repoMeteorDAO.insert(meteorList);
    }


    //Method to delete a list of meteors from the database stored locally on device
    public void deleteMeteors(List<Meteor> meteorList) {
        this.repoMeteorDAO.delete(meteorList);
    }
    //Method to get todays meteors
    public List<Meteor> getTodaysMeteors(String date) {
        return this.repoMeteorDAO.getTodaysMeteors(date);

    }

}

