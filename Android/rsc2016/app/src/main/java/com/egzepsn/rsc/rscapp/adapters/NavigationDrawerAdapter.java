package com.egzepsn.rsc.rscapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by domagoj on 11/24/16.
 */

public class NavigationDrawerAdapter extends ArrayAdapter<NavigationItem> {

    private ListView navDrawer;
    private List<NavigationItem> navItems;

    public NavigationDrawerAdapter(Context context, ListView drawer) {
        super(context, 0, getNavigationItems());
        this.navDrawer = drawer;
        navItems = getNavigationItems();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final NavigationItem navigationItem = getItem(position);
        navDrawer.setOnItemClickListener(NavigationClickListener);
        return convertView;
    }

    public static List<NavigationItem> getNavigationItems() {
        List<NavigationItem> navigationItems = new ArrayList<>();
        return navigationItems;
    }

    private ListView.OnItemClickListener NavigationClickListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            NavigationItem navigationItem = (NavigationItem) navItems.get(position);
        }
    };
}
