package com.egzepsn.rsc.rscapp.modules.initial.login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.app.RSCApp;
import com.egzepsn.rsc.rscapp.commons.activity.view.ActivityView;
import com.egzepsn.rsc.rscapp.commons.fragment.BaseFragment;
import com.egzepsn.rsc.rscapp.enums.ActivityEnum;
import com.egzepsn.rsc.rscapp.enums.AppStateEnum;
import com.egzepsn.rsc.rscapp.modules.initial.login.presenter.LoginPresenter;
import com.egzepsn.rsc.rscapp.modules.initial.login.presenter.LoginPresenterImpl;
import com.egzepsn.rsc.rscapp.modules.initial.login.view.LoginView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginFragment extends BaseFragment implements LoginView {

    @BindView(R.id.loader)
    ProgressBar progressBar;

    @BindView(R.id.button_facebook)
    LoginButton facebookButton;

    private LoginPresenter presenter;
    private CallbackManager callbackManager;
    private LoginManager loginManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        Log.d("ACCESSTOKEN", accessToken + "");

        View viewInflater = inflater.inflate(R.layout.login_fragment, container, false);
        setupParent(viewInflater);

        ButterKnife.bind(this, viewInflater);
        presenter = new LoginPresenterImpl(this);

        RSCApp.getInstance().setAppState(AppStateEnum.NotSignedIn);
        return viewInflater;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        callbackManager = CallbackManager.Factory.create();
        facebookButton.setFragment(this);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login(callbackManager, facebookButton);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void navigateToMain() {
        Log.d("NAVIGATE", "MAIN");
    }

    @Override
    public void resetMessage() {
    }

    @Override
    public void showError(String error) {
    }

    @Override
    public void showLoadingIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}