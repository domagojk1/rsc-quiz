package com.egzepsn.rsc.rscapp.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egzepsn.rsc.rscapp.R;

import java.util.ArrayList;

/**
 * Created by kiki3 on 26.11.2016..
 */

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {
    private ArrayList<Team> teams;
    private Context context;

    public TeamsAdapter(ArrayList<Team> teams, Context context) {
        this.teams = teams;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.team_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.teamName.setText(teams.get(position).getName());
        holder.placesAvailable.setText(teams.get(position).getUsers().size());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView teamName = (TextView) itemView.findViewById(R.id.team_name);
        public TextView placesAvailable = (TextView) itemView.findViewById(R.id.team_space);

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
