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
import android.view.MenuItem;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.commons.activity.BaseActivity;
import com.egzepsn.rsc.rscapp.enums.FragmentEnum;
import com.egzepsn.rsc.rscapp.helpers.Creator;
import com.egzepsn.rsc.rscapp.modules.initial.InitialActivity;
import com.egzepsn.rsc.rscapp.modules.main.pages.QuizPagerAdapter;
import com.egzepsn.rsc.rscapp.signalr.SignalRService;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private final Context mContext = this;
    private SignalRService mService;
    private boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = new Intent();
        intent.setClass(mContext, SignalRService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
        super.onStop();
    }

    public void sendMessage(String message) {
        if (mBound) {
            mService.sendMessage(message);
        }
    }

    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private final ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to SignalRService, cast the IBinder and get SignalRService instance
            SignalRService.LocalBinder binder = (SignalRService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    private void setupViewPager(ViewPager viewPager) {
        QuizPagerAdapter adapter = new QuizPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(Creator.getFragmentFromEnum(FragmentEnum.QuizListFragment), getString(R.string.quizListTitle));
        adapter.addFragment(Creator.getFragmentFromEnum(FragmentEnum.HistoryFragment), getString(R.string.quizHistory));
        adapter.addFragment(Creator.getFragmentFromEnum(FragmentEnum.SettingsFragment), getString(R.string.settings));
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }
}
