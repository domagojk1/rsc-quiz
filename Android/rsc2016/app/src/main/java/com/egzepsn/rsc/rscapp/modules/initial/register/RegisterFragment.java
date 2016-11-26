package com.egzepsn.rsc.rscapp.modules.initial.register;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.commons.fragment.BaseFragment;
import com.egzepsn.rsc.rscapp.modules.initial.register.presenter.RegisterPresenter;
import com.egzepsn.rsc.rscapp.modules.initial.register.presenter.RegisterPresenterImpl;
import com.egzepsn.rsc.rscapp.modules.initial.register.view.RegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by domagoj on 11/24/16.
 */

public class RegisterFragment extends BaseFragment implements RegisterView {
    private RegisterPresenter presenter;

    @BindView(R.id.reg_username) EditText etUsername;
    @BindView(R.id.reg_password) EditText etPassword;
    @BindView(R.id.reg_confpassword) EditText etConfirmPassword;
    @BindView(R.id.txt_reg_message) TextView tvMessage;
    @BindView(R.id.loader_register) ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RegisterPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnRegister)
    public void onClickRegister(Button button) {
        presenter.register(
                etUsername.getText().toString(),
                etPassword.getText().toString(),
                etConfirmPassword.getText().toString());
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
    public void navigateToLogin() {
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void showError(String error) {
        tvMessage.setText(error);
    }

    @Override
    public void resetMessage() {
        tvMessage.setText("");
    }
}
