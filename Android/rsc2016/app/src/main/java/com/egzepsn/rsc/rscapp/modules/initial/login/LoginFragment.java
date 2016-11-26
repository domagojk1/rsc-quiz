package com.egzepsn.rsc.rscapp.modules.initial.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.app.RSCApp;
import com.egzepsn.rsc.rscapp.commons.activity.view.ActivityView;
import com.egzepsn.rsc.rscapp.commons.fragment.BaseFragment;
import com.egzepsn.rsc.rscapp.enums.ActivityEnum;
import com.egzepsn.rsc.rscapp.enums.AppStateEnum;
import com.egzepsn.rsc.rscapp.enums.FragmentEnum;
import com.egzepsn.rsc.rscapp.modules.initial.login.presenter.LoginPresenter;
import com.egzepsn.rsc.rscapp.modules.initial.login.presenter.LoginPresenterImpl;
import com.egzepsn.rsc.rscapp.modules.initial.login.view.LoginView;
import com.egzepsn.rsc.rscapp.modules.initial.register.RegisterFragment;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginFragment extends BaseFragment implements LoginView {

    @BindView(R.id.loader)
    ProgressBar progressBar;

    private LoginPresenter presenter;
    private CallbackManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, viewInflater);
        presenter = new LoginPresenterImpl(this);

        RSCApp.getInstance().setAppState(AppStateEnum.NotSignedIn);
        manager = CallbackManager.Factory.create();

        setupParent(viewInflater);
        return viewInflater;
    }

    @OnClick(R.id.btnLogin)
    public void login() {
        Log.d("LoginFragment", "Login Test");
    }

    @OnClick(R.id.button_facebook)
    public void onClickFacebook(LoginButton button) {
        presenter.login(manager, button);
    }

    @Override
    public void navigateToMain() {
        ((ActivityView)this.getActivity()).showActivity(ActivityEnum.MainActivity);
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