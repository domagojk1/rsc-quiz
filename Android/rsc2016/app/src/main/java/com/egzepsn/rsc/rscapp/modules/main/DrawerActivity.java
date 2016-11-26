package com.egzepsn.rsc.rscapp.modules.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.widget.ListView;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.adapters.NavigationDrawerAdapter;
import com.egzepsn.rsc.rscapp.commons.activity.BaseActivity;
import com.egzepsn.rsc.rscapp.modules.main.home.view.HomeView;

import butterknife.BindView;

/**
 * Created by domagoj on 11/24/16.
 */

public class DrawerActivity extends BaseActivity implements HomeView {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.left_drawer) ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationDrawerAdapter navigationDrawerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        setupNavigationDrawer();
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_initial, menu);
        return true;
    }

    private void setupNavigationDrawer() {
        navigationDrawerAdapter = new NavigationDrawerAdapter(this, drawerList);
        drawerList.setAdapter(navigationDrawerAdapter);
        drawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }
}
