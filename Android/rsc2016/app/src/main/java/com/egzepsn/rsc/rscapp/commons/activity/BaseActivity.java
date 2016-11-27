package com.egzepsn.rsc.rscapp.commons.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.app.RSCApp;
import com.egzepsn.rsc.rscapp.commons.activity.view.ActivityView;
import com.egzepsn.rsc.rscapp.commons.fragment.BaseFragment;
import com.egzepsn.rsc.rscapp.enums.ActivityEnum;
import com.egzepsn.rsc.rscapp.enums.AppStateEnum;
import com.egzepsn.rsc.rscapp.enums.FragmentEnum;
import com.egzepsn.rsc.rscapp.helpers.Creator;
import com.egzepsn.rsc.rscapp.modules.main.home.HomeFragment;


public class BaseActivity extends AppCompatActivity implements ActivityView, BaseFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onFragmentInteraction(Uri uri) { }

    @Override
    public void showActivity(ActivityEnum activityEnum)
    {
        if(RSCApp.getInstance().getAppState() != AppStateEnum.NotSignedIn) {
            Class activityClass = Creator.getActivityFromEnum(activityEnum);
            Intent intent = new Intent(this, activityClass);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void showFragment(FragmentEnum fragmentEnum, boolean addToBackStack) {
        AppStateEnum appStateEnum = RSCApp.getInstance().getAppState();
        Fragment fragment = Creator.getFragmentFromEnum(fragmentEnum);

        if (fragment != null) {
            if(fragment.getClass() == HomeFragment.class && appStateEnum == AppStateEnum.SignedIn)
                return;

            fragment.setArguments(getIntent().getExtras());

            if (addToBackStack) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment,fragment.getClass().toString())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(fragment.getClass().toString())
                        .commit();
            }
            else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment,fragment.getClass().toString())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        }
    }

    @Override
    public void showToast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void pressBack() {
        onBackPressed();
    }

    @Override
    public void clearBackStack() {
        for (int i = 1; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void clearBackStack(int levelsIgnored) {
        for (int i = 1 + levelsIgnored; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
            getSupportFragmentManager().popBackStack();
        }
    }
}
