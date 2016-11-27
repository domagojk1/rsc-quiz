package com.egzepsn.rsc.rscapp.modules.main.pages;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.modules.initial.InitialActivity;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kiki3 on 26.11.2016..
 */

public class SettingsFragment extends Fragment {
    private CircleImageView imageView;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, null);
        SharedPreferences preferences = this.getActivity().getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE);
        imageView = (CircleImageView) view.findViewById(R.id.profile_image);
        textView = (TextView) view.findViewById(R.id.username);

        textView.setText(preferences.getString("userEmail", ""));
        Picasso.with(getActivity()).load(preferences.getString("userPicture", "")).into(imageView);

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
