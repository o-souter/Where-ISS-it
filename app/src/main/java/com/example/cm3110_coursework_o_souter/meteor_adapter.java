package com.example.cm3110_coursework_o_souter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class meteor_adapter extends RecyclerView.Adapter<meteor_adapter.MeteorViewHolder>{
    private Context ctx;
    private List<Meteor> meteorList;

    

    
    @NonNull
    @Override
    public MeteorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(ctx).inflate(R.layout.meteor, parent, false);
        MeteorViewHolder viewHolder = new MeteorViewHolder(itemView, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MeteorViewHolder holder, int position) {
        Meteor meteor = this.meteorList.get(position);
        //Initializing widgets for card
        TextView name = holder.meteorView.findViewById(R.id.meteorName);
        TextView date = holder.meteorView.findViewById(R.id.txtDate);
        TextView hazard = holder.meteorView.findViewById(R.id.txtPotentiallyHazardous);
        TextView diameter = holder.meteorView.findViewById(R.id.txtDiameter);
        //TextView lon = holder.meteorView.findViewById(R.id.txtLongitude);
        if (meteor.getName().length() > 20) {
            name.setText(meteor.getName().substring(0, 20) + "..."); //Name shortening in case name too long for card
        }
        else {
            name.setText(meteor.getName());
        }


        date.setText(meteor.getDate());
        if (meteor.getHazard()) {
            hazard.setText("Potentially Hazardous"); //If hazardous, mark as hazardous
        }
        else {
            hazard.setText("Non-Hazardous");
        }
        diameter.setText(meteor.getDiameter() + "m Diameter");
        //lon.setText(meteor.getLongitude());
    }

    @Override
    public int getItemCount() {
        return meteorList.size();
    }

    public meteor_adapter(Context ctx, List<Meteor> meteorList) {
        super();
        this.ctx = ctx;
        this.meteorList = meteorList;
    }

    public void setMeteors(List<Meteor> meteorList) {//Method to set meteors in the list

        this.meteorList = meteorList;
        //System.out.println("Tking in some meteors as follows");
        //System.out.println(this.meteorList);
    }

    public class MeteorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private View meteorView;
            private meteor_adapter adapter;
            public TextView name;


            public MeteorViewHolder(View meteorView, meteor_adapter adapter) {
                super(meteorView);
                this.meteorView = meteorView;
                this.adapter = adapter;
                this.meteorView.setOnClickListener(this);

            }
            @Override
            public void onClick(View v) {
                //This method was not used in the end
            }
        }
}
