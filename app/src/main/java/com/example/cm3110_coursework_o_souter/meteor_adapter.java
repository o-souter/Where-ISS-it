package com.example.cm3110_coursework_o_souter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

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
        TextView name = holder.meteorView.findViewById(R.id.meteorName);
        TextView date = holder.meteorView.findViewById(R.id.txtDate);
        TextView lat = holder.meteorView.findViewById(R.id.txtLatitude);
        TextView lon = holder.meteorView.findViewById(R.id.txtLongitude);
        name.setText(meteor.getName());
        date.setText(meteor.getDate());
        lat.setText(meteor.getLatitude());
        lon.setText(meteor.getLongitude());
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

    public class MeteorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private View meteorView;
            private meteor_adapter adapter;
            public TextView name;


            public MeteorViewHolder(View meteorView, meteor_adapter adapter) {
                super(meteorView);
                this.meteorView = meteorView;
                this.adapter = adapter;
                //TextView txtName = meteorView.findViewById(R.id.meteorName);
                //TextView txtLatitude = meteorView.findViewById(R.id.txtLatitude);
                //TextView txtLongitude = meteorView.findViewById(R.id.txtLongitude);
                //TextView txtDate = meteorView.findViewById(R.id.txtDate);
                this.meteorView.setOnClickListener(this);

            }
            @Override
            public void onClick(View v) {
                //Stuff
            }
        }
}