package com.egzepsn.rsc.rscapp.modules.main.teams;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.app.RSCApp;
import com.egzepsn.rsc.rscapp.commons.fragment.BaseFragment;
import com.egzepsn.rsc.rscapp.models.TeamsAdapter;
import com.egzepsn.rsc.rscapp.modules.main.RecycleItemClickListener;

/**
 * Created by kiki3 on 27.11.2016..
 */

public class TeamsFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private TeamsAdapter adapter;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teams, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        button = (Button) view.findViewById(R.id.button_new_team);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View dialogView = inflater.inflate(R.layout.dialog_new_team, null);
        Log.d("EVENTID", getArguments().getInt("eventId") + "");
        final int eventId = getArguments().getInt("eventId");

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_teams);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TeamsAdapter(RSCApp.getTeams(), getActivity());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecycleItemClickListener(getActivity(), recyclerView, new RecycleItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                DialogFragment dialogFragment = new NewTeamDialog();
                Bundle args = new Bundle();
                args.putInt("eventId", eventId);
                dialogFragment.setArguments(args);
                dialogFragment.show(fragmentManager, "");
            }
        });

    }
}
