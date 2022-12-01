package com.example.cm3110_coursework_o_souter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Fragment frag = new map_frag();
        //getSupportFragmentManager().beginTransaction().replace(R.id.mapFrame, frag).commit();
    }
}