package com.egzepsn.rsc.rscapp.modules.main.teams;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.models.Event;
import com.egzepsn.rsc.rscapp.models.TeamsAdapter;

import java.util.ArrayList;

/**
 * Created by kiki3 on 26.11.2016..
 */

public class TeamsActivity extends AppCompatActivity {
    private ArrayList<Event> events;
    private RecyclerView recyclerView;
    private TeamsAdapter adapter;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_teams);
        button = (Button) findViewById(R.id.button_new_team);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("New team")
                        .setPositiveButton(getString(R.string.add), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .setNegativeButton(R.string.cancel_, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });
    }
}
