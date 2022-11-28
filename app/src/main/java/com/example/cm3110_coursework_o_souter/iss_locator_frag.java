package com.example.cm3110_coursework_o_souter;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link iss_locator_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class iss_locator_frag extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public iss_locator_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment iss_locator_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static iss_locator_frag newInstance(String param1, String param2) {
        iss_locator_frag fragment = new iss_locator_frag();
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
        View v =  inflater.inflate(R.layout.iss_locator_frag, container, false);
        v.setBackgroundColor(Color.CYAN); //Setting the background colour
        Button btnBackISS = v.findViewById(R.id.btnBackISS); //Making a variable to find the button
        TextView testText = v.findViewById(R.id.testTextViewISS);
        btnBackISS.setOnClickListener(this); //Adding a listener
        //Getting API data
        String url = "https://api.wheretheiss.at/v1/satellites/25544"; //URL where the ISS data is stored
        RequestQueue queue = Volley.newRequestQueue(this.getContext());

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Add text
                        testText.setText("Response is: " + response.substring(0, 300));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Error handling
                        testText.setText("An error occurred.");
                    }
                });
        queue.add(stringRequest);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBackISS) {
            Navigation.findNavController(v).navigate(R.id.homepage_frag);
        }
        else {

            //Do nothing
        }
    }
    String testResponse;

}