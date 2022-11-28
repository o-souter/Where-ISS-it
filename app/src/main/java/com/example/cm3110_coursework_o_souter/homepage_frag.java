package com.example.cm3110_coursework_o_souter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homepage_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homepage_frag extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homepage_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homepage_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static homepage_frag newInstance(String param1, String param2) {
        homepage_frag fragment = new homepage_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.homepage_frag, container, false); //Setting the view to a variable
        Button btnISSPage = v.findViewById(R.id.btn_iss_page); //Making a variable to find the button
        btnISSPage.setOnClickListener(this); //Adding a listener

        Button btnMeteorPage = v.findViewById(R.id.btn_meteor_page); //Making a variable to find the button
        btnMeteorPage.setOnClickListener(this); //Adding a listener
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_iss_page) {
            Navigation.findNavController(v).navigate(R.id.iss_locator_frag);
        }
        else if (v.getId() == R.id.btn_meteor_page) {
            Navigation.findNavController(v).navigate(R.id.meteor_locator_frag);
        }
        else {
            //Do nothing
        }
    }
}