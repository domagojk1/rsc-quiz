package com.egzepsn.rsc.rscapp.modules.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.app.RSCApp;
import com.egzepsn.rsc.rscapp.commons.activity.BaseActivity;
import com.egzepsn.rsc.rscapp.enums.AppStateEnum;
import com.egzepsn.rsc.rscapp.enums.FragmentEnum;
import com.egzepsn.rsc.rscapp.helpers.Creator;
import com.egzepsn.rsc.rscapp.models.CreateTeam;
import com.egzepsn.rsc.rscapp.models.Event;
import com.egzepsn.rsc.rscapp.modules.main.pages.QuizListFragment;
import com.egzepsn.rsc.rscapp.modules.main.pages.QuizPagerAdapter;
import com.egzepsn.rsc.rscapp.modules.main.quiz.QuizFragment;
import com.egzepsn.rsc.rscapp.modules.main.teams.NewTeamDialog;
import com.egzepsn.rsc.rscapp.modules.main.teams.TeamsFragment;
import com.egzepsn.rsc.rscapp.signalr.SignalRService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends BaseActivity implements QuizListFragment.OnItemSelectedListener, NewTeamDialog.OnJoinedTeamListener {
    private Unbinder unbinder;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        RSCApp.getInstance().setAppState(AppStateEnum.SignedIn);
    }

    private void setupViewPager(ViewPager viewPager) {
        QuizPagerAdapter adapter = new QuizPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(Creator.getFragmentFromEnum(FragmentEnum.QuizListFragment), getString(R.string.quizListTitle));
        adapter.addFragment(Creator.getFragmentFromEnum(FragmentEnum.SettingsFragment), getString(R.string.settings));
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public void onSelect(Event event) {
        QuizPagerAdapter adapter = new QuizPagerAdapter(getSupportFragmentManager());
        TeamsFragment fragment = new TeamsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("eventId", event.getId());
        RSCApp.setTeams(event.getTeams());
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.quizListTitle));
        adapter.addFragment(Creator.getFragmentFromEnum(FragmentEnum.SettingsFragment), getString(R.string.settings));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onJoin() {
        QuizPagerAdapter adapter = new QuizPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new QuizFragment(), getString(R.string.quizListTitle));
        adapter.addFragment(Creator.getFragmentFromEnum(FragmentEnum.SettingsFragment), getString(R.string.settings));
        viewPager.setAdapter(adapter);
    }
}
