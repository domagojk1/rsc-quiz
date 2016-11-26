package com.egzepsn.rsc.rscapp.modules.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.commons.activity.BaseActivity;
import com.egzepsn.rsc.rscapp.enums.FragmentEnum;
import com.egzepsn.rsc.rscapp.modules.main.home.HomeFragment;
import com.egzepsn.rsc.rscapp.signalr.SignalRService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    private final Context mContext = this;
    private SignalRService mService;
    private boolean mBound = false;

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (fragmentContainer != null) {
            // in case of saved state fragment is set
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getName());
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment fragment1 : (fragments != null ? fragments : new ArrayList<Fragment>())) {
                String fragment1Tag = fragment1.getTag();
                String fragmentTag = HomeFragment.class.getName();
                if (fragment1Tag == fragmentTag) {
                    return;
                }
            }
            //if (savedInstanceState != null) {
            if (fragment != null) {
                return;
            }
            showFragment(FragmentEnum.HomeFragment, false);
        }

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
}
