package com.example.cm3110_coursework_o_souter;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cm3110_coursework_o_souter.data.MeteorRepo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link meteor_locator_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class meteor_locator_frag extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private MeteorRepo repoLocal;
    private Calendar dateCalendar;
    private String date;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    List<Meteor> meteorList;
    meteor_adapter mAdapter;

    //private List meteorList;
    public meteor_locator_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment meteor_locator_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static meteor_locator_frag newInstance(String param1, String param2) {
        meteor_locator_frag fragment = new meteor_locator_frag();
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
        System.out.println("Wiping repo, creating new one...");
        this.repoLocal = new MeteorRepo(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.meteor_locator_frag, container, false);
        v.setBackgroundColor(Color.GRAY); //Setting the background colour


        Button btnBackMeteor = v.findViewById(R.id.btnBackMeteor); //Making a variable to find the button
        btnBackMeteor.setOnClickListener(this); //Adding a listener
        //RecyclerView meteorList = v.findViewById(R.id.recycleViewMeteors);
        //TextView txtMeteorCoords = v.findViewById(R.id.coordinateTextView); //Variable for the textview to display the coordinates

        return v;
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBackMeteor) {
            Navigation.findNavController(v).navigate(R.id.homepage_frag);
        }
        else {
            //Do nothing
        }

    }

    private void getAPIDataAndFillRecycler() {
         meteorList = new ArrayList<Meteor>();

        //Testing code - uncomment this code and comment out other code out to test with placeholder meteor values
        /*
        Meteor testMeteor1 = new Meteor("Brian", "69.3242", "37.3837", "2002");
        Meteor testMeteor2 = new Meteor("James", "21.32342", "1.12323", "1996");
        Meteor testMeteor3 = new Meteor("Fred", "27.9642", "5.26372", "2020");
        Meteor testMeteor4 = new Meteor("Ethel", "87.3732", "7.32932", "2012");

        meteorList.add(testMeteor1);
        meteorList.add(testMeteor2);
        meteorList.add(testMeteor3);
        meteorList.add(testMeteor4);
        */

        dateCalendar = Calendar.getInstance();
        //String theDate = dateFormat.format(dateCalendar.getTime());
        System.out.println("The date : " + dateFormat.format(dateCalendar.getTime()));
        date = dateFormat.format(dateCalendar.getTime());
        //dateCalendar.add(Calendar.DAY_OF_MONTH, -7);
        //String date2 = dateFormat.format(dateCalendar.getTime());
        //System.out.println("Date 1: " + date1);
        //System.out.println("Date 2: " + date2);
        String url = "https://api.nasa.gov/neo/rest/v1/feed?start_date=" + date + "&end_date=" + date + "&api_key=90bu8heDmxK3JCKpJBJvV5eejPHI0kDaRBTP4WAH";
        //System.out.println("Test URL: " + url);
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        System.out.println("Beginning API request...");
        StringRequest meteorRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Response received");
                //Creating a JSONObject from the string request
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject near_earth_objects = jsonObject.getJSONObject("near_earth_objects");
                    JSONArray meteor_array = near_earth_objects.getJSONArray(date);
                    System.out.println("Beginning meteor loop...");
                    for (int i = 0; i < meteor_array.length(); i++) {
                        JSONObject item = meteor_array.getJSONObject(i);

                        String meteorName = item.getString("name");
                        //System.out.println("Meteor name: " + meteorName);
                        Boolean hazardous = item.getBoolean("is_potentially_hazardous_asteroid");
                        JSONObject diameters = item.getJSONObject("estimated_diameter");
                        JSONObject diameterM = diameters.getJSONObject("meters");
                        long diameterMin = Math.round(diameterM.getDouble("estimated_diameter_min"));
                        System.out.println("Diameter Min: " + diameterMin);

                        //System.out.println("Hazard Level: " + hazardous);
                        Meteor newMeteor = new Meteor(meteorName, hazardous, date, diameterMin);
                        meteorList.add(newMeteor);
                        System.out.println("Meteor string");
                        System.out.println(newMeteor.toString());
                    }
                    System.out.println("API data downloaded successfully");
                    removeLoadingBar();
                    //Store data if it exists


                    System.out.println("Meteor List size: " + meteorList.size());
                    if (meteorList.size() > 0) {
                        System.out.println("Meteors downloaded from API, storing in repo...");
                        repoLocal.storeMeteors(meteorList);
                        System.out.println("Stored in repo with contentsL " + repoLocal.getTodaysMeteors(date));

                    }
                    fillRecycler(meteorList);






                    System.out.println("Meteor List 1: ");
                    //for (int j = 0; j < meteorList.size(); j++) {
                    //    System.out.println("Meteor: " + meteorList.get(j).toString());
                    //}



                    //System.out.println("First meteor in list name : " + meteorName);


                }
                catch (JSONException e){
                    e.printStackTrace();
                    System.out.println("API Error!");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error handling
                System.out.println("API Error!");
            }
        });
        queue.add(meteorRequest);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        this.meteorList = new ArrayList<Meteor>();
        this.view = view;

        //Set up RV
        RecyclerView meteorRv = view.findViewById(R.id.recycleViewMeteors);
        //RecyclerView meteorRv = getActivity().findViewById(R.id.recycleViewMeteors);
        meteorRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new meteor_adapter(getContext(), meteorList);
        meteorRv.setAdapter(mAdapter);
        //Check if there is already a list of meteors stored, if so, load that

        List<Meteor> cached = repoLocal.getTodaysMeteors(date);
        System.out.println("Repo contents when view created: " + repoLocal.getTodaysMeteors(date));
        System.out.println("Cached: " + cached);
        System.out.println("Meteors in repo" + this.repoLocal.getTodaysMeteors(date));
        //meteorList.addAll(this.repoLocal.getTodaysMeteors(date));
        System.out.println("Size of cache: " + cached.size());
        if (cached.size() > 0) {
            //If cached data exists, use this instead of downloading
            //for (int i = 0; i < cached.size(); i++) {
            //    meteorList.add(cached.get(i));
            //}
            this.meteorList.addAll(this.repoLocal.getTodaysMeteors(date));
            mAdapter.setMeteors(meteorList);
            mAdapter.notifyDataSetChanged();
            System.out.println("Items in adapter offline: " + mAdapter.getItemCount());
        }
        //Otherwise download
        else {
            System.out.println("Cache Empty, downloading data from API");
            getAPIDataAndFillRecycler();

        }
    }

    private void fillRecycler(List<Meteor> meteorList) {
        //System.out.println("Received array list: " + meteorList);
        mAdapter.setMeteors(meteorList);
        mAdapter.notifyDataSetChanged();


    }
    private void removeLoadingBar() {
        ProgressBar meteorBar = view.findViewById(R.id.meteorLoadingBar);
        meteorBar.setVisibility(view.GONE);
    }
}