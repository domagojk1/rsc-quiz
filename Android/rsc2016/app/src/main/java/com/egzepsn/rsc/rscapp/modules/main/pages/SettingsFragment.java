package com.egzepsn.rsc.rscapp.modules.main.pages;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.modules.initial.InitialActivity;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kiki3 on 26.11.2016..
 */

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.logout_text)
    public void onLogout() {
        LoginManager.getInstance().logOut();
        AccessToken.setCurrentAccessToken(null);
        Intent intent = new Intent(getActivity(), InitialActivity.class);
        startActivity(intent);
    }
}
