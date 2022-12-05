package com.example.cm3110_coursework_o_souter;

import static java.lang.Double.parseDouble;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

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
    private String latAndLon = "";
    private ArrayList<Double> latAndLonArrayList = new ArrayList<Double>();
    private ArrayList <Double>issLocationStored;
    Location issLocationObj = new Location();
    //private Context ctx;
    public iss_locator_frag() {
        // Required empty public constructor
    }

    //Initialization of widgets
    View v;
    Button btnBackISS;
    TextView coordinateTextView;
    Button refreshBtn;
    Button trackLocationBtn;
    TextView txtCountry;
    public void setIssLocationStored(ArrayList<Double> coords) {
        issLocationStored = coords;
    }
    public ArrayList<Double> getIssLocationStored() {
        return issLocationStored;
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
        Context ctx = fragment.getContext();
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


    TextView txtDistance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.iss_locator_frag, container, false);
        v.setBackgroundColor(Color.CYAN); //Setting the background colour
        btnBackISS = v.findViewById(R.id.btnBackISS); //Making a variable to find the button
        coordinateTextView = v.findViewById(R.id.coordinateTextView);
        btnBackISS.setOnClickListener(this); //Adding a listener
        refreshBtn = v.findViewById(R.id.btnRefresh);
        refreshBtn.setOnClickListener(this); //Adding a listener
        trackLocationBtn = v.findViewById(R.id.locationTrackBtn);
        trackLocationBtn.setOnClickListener(this);
        txtDistance = v.findViewById(R.id.distanceTxtView);
        txtCountry = v.findViewById(R.id.txtCountry);
        downloadAPIDataAndUpdate();



        return v;
    }
    public void downloadAPIDataAndUpdate() {
        //Set to loading values until updated
        coordinateTextView.setText("The ISS's coordinates are:\nLatitude: Loading...\nLongitude: Loading...");
        txtCountry.setText("Loading...");
        //Getting API data from Where the ISS at
        String whereISSurl = "https://api.wheretheiss.at/v1/satellites/25544"; //URL where the ISS data is stored
        RequestQueue queue = Volley.newRequestQueue(this.getContext());

        StringRequest whereTheISSStringRequest = new StringRequest(
                Request.Method.GET, whereISSurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Creating a JSONObject from the string request
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String latitude = jsonObject.getString("latitude");
                    String longitude = jsonObject.getString("longitude");

                    issLocationObj.setLatitude(latitude);
                    issLocationObj.setLongitude(longitude);
                    //Add text
                    coordinateTextView.setText("The ISS's coordinates are: \nLatitude: " + issLocationObj.getLatitude() + " \nLongitude: " + issLocationObj.getLongitude());
                    latAndLon = latitude + "+" + longitude;
                    latAndLonArrayList.add(parseDouble(latitude));
                    latAndLonArrayList.add(parseDouble(longitude));

                    //Getting API data from OpenCageData reverse-geocoding
                    //https://api.opencagedata.com/geocode/version/format?parameters
                    String openCageurl = "https://api.opencagedata.com/geocode/v1/json?q=" + latAndLon.toString() + "&key=39f51af858b4470db1062aba40c2c414";
                    //System.out.println("Test url: " + openCageurl);
                    StringRequest openCageStringRequest = new StringRequest(
                            Request.Method.GET, openCageurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Creating a JSONObject from the string request
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray results = jsonObject.getJSONArray("results");
                                JSONObject item = results.getJSONObject(0);
                                //JSONObject annotations = item.getJSONObject("annotations");
                                String formatted = item.getString("formatted");
                                //System.out.println("Response as follows:");
                                //System.out.println(response);
                                //Add text
                                issLocationObj.setLocation(formatted);
                                //System.out.println(issLocationObj.toString());
                                txtCountry.setText(issLocationObj.getLocation());

                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                                txtCountry.setText("There was an issue with the Country API...");
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Error handling
                            txtCountry.setText("There was an issue with the Country API...");
                        }
                    });
                    //Queue the request
                    queue.add(openCageStringRequest);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    coordinateTextView.setText("There was an issue with the Coordinate API...");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error handling
                coordinateTextView.setText("There was an issue with the Coordinate API...");
            }
        });
        //Queue the request
        queue.add(whereTheISSStringRequest);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBackISS) {
            Navigation.findNavController(v).navigate(R.id.homepage_frag);
        }
        else if (v.getId() == R.id.btnRefresh) {
            //System.out.println("Test button pressed");
            //If refresh button pressed, load the page again
            //Navigation.findNavController(v).navigate(R.id.iss_locator_frag);
            downloadAPIDataAndUpdate();
            //coordinateTextView.setText("The ISS's coordinates are: \nLatitude: " + issLocationObj.getLatitude() + " \nLongitude: " + issLocationObj.getLongitude());
        }
        else if (v.getId() == R.id.locationTrackBtn) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //if (ActivityCompat.checkSelfPermission(iss_locator_frag.this, ))
            }
            //checkUserPermissions(getContext());
            ArrayList<String> userLocation = getLocation();

            System.out.println("Distance between ISS and iceland is " + compareLocations(userLocation) + "km");
            System.out.println("Lat and Lon in an array" + latAndLonArrayList);

            txtDistance.setText("Distance between ISS and iceland is " + compareLocations(userLocation) + "km");

        }
        else {

            //Do nothing
        }
    }
    String testResponse;
    // Research
    // https://www.youtube.com/watch?v=y0gX4FD3nxk
    // https://www.youtube.com/watch?v=mbQd6frpC3g
    private void checkUserPermissions(Context ctx) {
        System.out.println("this is a test");
        System.out.println("User Location permission status: " + ContextCompat.checkSelfPermission(ctx, "android.permission.ACCESS_COARSE_LOCATION"));

        //ActivityResultLauncher<String[]> locationPermissionReq =
        //        registerForActivityResult(new ActivityResultContracts
        //        .RequestMultiplePermissions(), result -> {
        //        Boolean acceptedCoarseLocation = result.getOrDefault(
        //                Manifest.permission.ACCESS_COARSE_LOCATION,false);
        //        )
        //});
    }
    private ArrayList<String> getLocation() {

        String userLat = "64.811384"; //Placeholder value for user location latitude
        String userLon = "-18.302958"; //Placeholder value for longitude
        ArrayList<String> location = new ArrayList<String>();
        location.add(userLat);
        location.add(userLon);


        return location;


    }
    private double compareLocations(ArrayList<String> userLoc) {
        double pi = 3.1415926535; //Using pi in order to calculate using radians
        double x1 = parseDouble(issLocationObj.getLatitude());
        double y1 = parseDouble(issLocationObj.getLongitude());
        //System.out.println("x1: " + x1);
        //System.out.println("y1: " + y1);
        x1 = x1/(180/pi); //x of ISS in radians
        y1 = y1/(180/pi); //y of ISS in radians
        double x2 = parseDouble(userLoc.get(0))/(180/pi); //x of USer Location in radians
        double y2 = parseDouble(userLoc.get(1))/(180/pi); //y of User Location in radians
        //Distance = sqrt(a^2 + b^2)

        //Haversine formula - https://www.geeksforgeeks.org/program-distance-two-points-earth/#:~:text=For%20this%20divide%20the%20values,is%20the%20radius%20of%20Earth.
        double dlon = y2 - x1;
        double dlat = x2 - y1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(x1) * Math.cos(x2)
                * Math.pow(Math.sin(dlon / 2),2);
        System.out.println("a: " + a);
        double c = 2 * Math.asin(Math.sqrt(a));
        System.out.println("c: " + c);
        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;
        DecimalFormat twoDPFormat = new DecimalFormat("0.00");
        // calculate the result
        System.out.println("Distance output test: " + parseDouble(twoDPFormat.format(c * r)));
        return parseDouble(twoDPFormat.format(c * r));



        //return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

}