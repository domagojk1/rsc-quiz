package com.egzepsn.rsc.rscapp.modules.main.teams;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.commons.activity.BaseActivity;
import com.egzepsn.rsc.rscapp.enums.FragmentEnum;
import com.egzepsn.rsc.rscapp.models.Event;
import com.egzepsn.rsc.rscapp.models.TeamsAdapter;

import java.util.ArrayList;

/**
 * Created by kiki3 on 26.11.2016..
 */

public class TeamsActivity extends BaseActivity {
    private ArrayList<Event> events;
    private RecyclerView recyclerView;
    private TeamsAdapter adapter;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.d("ONCREATE", "TEAMSACTIVITY");
        setContentView(R.layout.activity_quiz);
        showFragment(FragmentEnum.TeamsFragment, false);
    }
}
