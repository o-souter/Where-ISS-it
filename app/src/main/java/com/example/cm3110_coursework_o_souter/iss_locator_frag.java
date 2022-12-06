package com.example.cm3110_coursework_o_souter;

import static com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY;
import static java.lang.Double.parseDouble;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

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
    String[] permissionsRequired = new String[]{
        Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
    };
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
    private Boolean fineLocationGranted;
    private Boolean coarseLocationGranted;
    private FusedLocationProviderClient fusedLocationClient;
    private ActivityResultLauncher<String[]> locationPermissionReq;
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
        registerForLocationPermissions();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());


    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        locationPermissionReq.unregister();
    }
    private void registerForLocationPermissions() {
        locationPermissionReq = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result ->{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
                coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false);
            }
            else {
                fineLocationGranted = (ContextCompat.checkSelfPermission(
                        getContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED);
                coarseLocationGranted = (ContextCompat.checkSelfPermission(
                        getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED);
            }
        });
    }

    private Boolean checkIfPermissionsGranted() {
        if (fineLocationGranted != null && fineLocationGranted) {
            //Fine location has been granted
            return true;
        }
        else if (coarseLocationGranted != null && coarseLocationGranted) {
            //Coarse location has been granted only
            return true;
        }
        //No permissions granted
        return false;
    }

    TextView txtDistance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.iss_locator_frag, container, false);

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
        v.setBackgroundColor(Color.CYAN); //Setting the background colour


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
                    String openCageUrl = "https://api.opencagedata.com/geocode/v1/json?q=" + latAndLon.toString() + "&key=39f51af858b4470db1062aba40c2c414";
                    //System.out.println("Test url: " + openCageurl);
                    StringRequest openCageStringRequest = new StringRequest(
                            Request.Method.GET, openCageUrl, new Response.Listener<String>() {
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
                                txtCountry.setText("There was an issue with the Country API. Check your Internet Connection");
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
                    coordinateTextView.setText("There was an issue with the Coordinate API. Check your Internet Connection");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error handling
                coordinateTextView.setText("There was an issue with the Coordinate API. Check your Internet Connection");
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
            Location userLocation = getLocation();
            //System.out.println("Distance between ISS and RGU is " + compareLocations(userLocation) + "km");
            //System.out.println("Lat and Lon in an array" + latAndLonArrayList);
            if (checkIfPermissionsGranted()){
                trackLocationBtn.setText("Refresh");
                getLocation();
                txtDistance.setText("Distance between ISS and your location is " + compareLocations(userLocation) + "km");
            }
            else {
                requestLocationPermissions();
            }

        }
        else {

            //Do nothing
        }
    }
    private void requestLocationPermissions() {
        locationPermissionReq.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }
    Double userLat = 57.11899994915085;
    Double userLon = -2.1377501050394203;
    private Location getLocation() {
        Location userLoc = new Location();
        //userLat;
        //String userLon = "";
        //RGU Coordinate placeholder values 57.11899994915085, -2.1377501050394203
        //String userLat = "57.11899994915085"; //Placeholder value for user location latitude
        //String userLon = "-2.1377501050394203"; //Placeholder value for longitude

        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestLocationPermissions();
            return null;
        }

        //Getting location
        GoogleApiAvailability googleApiAvailability = new GoogleApiAvailability();
        if (ConnectionResult.SUCCESS == googleApiAvailability.isGooglePlayServicesAvailable(getContext())) {
            //Google play services
            Executor exec = Executors.newSingleThreadExecutor();
            fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
                    .addOnSuccessListener(exec, new OnSuccessListener<android.location.Location>() {
                        @Override
                        public void onSuccess(android.location.Location location) {
                            //Get last location
                            if (location != null){
                                System.out.println("Location successfully gathered, updating values...");
                                userLat = location.getLatitude();
                                userLon = location.getLongitude();
                            }
                            else {
                                txtDistance.setText("Error with location, try refreshing or checking your permissions");
                            }
                        }
                    });
        }
        else {
            //Use LocationManager
            LocationManager locationManager = (LocationManager)getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

            //Check for GPS
            boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (gpsEnabled) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    locationManager.getCurrentLocation(LocationManager.GPS_PROVIDER, null,
                    Executors.newSingleThreadExecutor(),
                            new Consumer<android.location.Location>(){
                        @Override
                        public void accept(android.location.Location location) {
                            if (location != null) {
                                System.out.println("Location successfully gathered, updating values...");
                                userLat = location.getLatitude();
                                userLon = location.getLongitude();
                            }
                            else {
                                txtDistance.setText("Error with location, try refreshing or checking your permissions");
                            }
                        }
                    });
                }
            }
        }

        //ArrayList<String> location = new ArrayList<String>();
        //location.add(userLat.toString());
        //location.add(userLon.toString());
        userLoc.setLatitude(userLat.toString());
        userLoc.setLongitude(userLon.toString());
        //return location;

        return userLoc;
    }
    private double compareLocations(Location userLoc) {
        double pi = 3.1415926535; //Using pi in order to calculate using radians
        double x1 = parseDouble(issLocationObj.getLatitude());
        double y1 = parseDouble(issLocationObj.getLongitude());

        x1 = x1/(180/pi); //x of ISS in radians
        y1 = y1/(180/pi); //y of ISS in radians
        double x2 = parseDouble(userLoc.getLatitude())/(180/pi); //x of USer Location in radians
        double y2 = parseDouble(userLoc.getLongitude())/(180/pi); //y of User Location in radians


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
    }

}