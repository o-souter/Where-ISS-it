package com.example.cm3110_coursework_o_souter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Fragment frag = new map_frag();
        //getSupportFragmentManager().beginTransaction().replace(R.id.mapFrame, frag).commit();
        //View v =

    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.activity_main, container, false);
        ProgressBar mainBar = v.findViewById(R.id.progressBarMain);
        mainBar.setVisibility(v.VISIBLE);


        return v;
    }

}