package com.egzepsn.rsc.rscapp.modules.initial.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.app.RSCApp;
import com.egzepsn.rsc.rscapp.commons.fragment.BaseFragment;
import com.egzepsn.rsc.rscapp.enums.AppStateEnum;
import com.egzepsn.rsc.rscapp.modules.initial.login.presenter.LoginPresenter;
import com.egzepsn.rsc.rscapp.modules.initial.login.presenter.LoginPresenterImpl;
import com.egzepsn.rsc.rscapp.modules.initial.login.view.LoginView;
import com.egzepsn.rsc.rscapp.modules.main.MainActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginFragment extends BaseFragment implements LoginView, GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.loader)
    ProgressBar progressBar;

    @BindView(R.id.button_facebook)
    LoginButton facebookButton;

    @BindView(R.id.button_google)
    SignInButton signInButton;

    private LoginPresenter presenter;
    private CallbackManager callbackManager;
    private LoginManager loginManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AccessToken token = AccessToken.getCurrentAccessToken();
        if (token != null) {
            goToMain();
        }
        //TODO: same for google

        View viewInflater = inflater.inflate(R.layout.login_fragment, container, false);
        setupParent(viewInflater);

        ButterKnife.bind(this, viewInflater);
        presenter = new LoginPresenterImpl(this);

        RSCApp.getInstance().setAppState(AppStateEnum.NotSignedIn);
        return viewInflater;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this.getActivity())
                .enableAutoManage(this.getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        signInButton.setSize(SignInButton.SIZE_STANDARD);

        callbackManager = CallbackManager.Factory.create();
        facebookButton.setFragment(this);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginFacebook(callbackManager, facebookButton);
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
        goToMain();
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    private void goToMain() {
        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        startActivity(intent);
        this.getActivity().finish();
    }
}