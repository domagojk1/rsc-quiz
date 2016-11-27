package com.egzepsn.rsc.rscapp.modules.main.teams;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.commons.fragment.BaseFragment;
import com.egzepsn.rsc.rscapp.models.Event;
import com.egzepsn.rsc.rscapp.models.TeamsAdapter;

import java.util.ArrayList;

/**
 * Created by kiki3 on 27.11.2016..
 */

public class TeamsFragment extends BaseFragment {
    private ArrayList<Event> events;
    private RecyclerView recyclerView;
    private TeamsAdapter adapter;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("ONCREATEVIEW", "ok");
        return inflater.inflate(R.layout.fragment_teams, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        button = (Button) view.findViewById(R.id.button_new_team);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("New team")
                        .setPositiveButton(getString(R.string.add), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });
    }
}
