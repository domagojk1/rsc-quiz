package com.egzepsn.rsc.rscapp.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;

import com.egzepsn.rsc.rscapp.enums.AppStateEnum;
import com.egzepsn.rsc.rscapp.models.User;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MaterialModule;
import com.activeandroid.ActiveAndroid;

/**
 * Created by domagoj on 11/23/16.
 */

public class RSCApp extends Application {
    private static RSCApp instance;
    protected static AppStateEnum appState = AppStateEnum.NotSignedIn;
    protected static User loggedUser;

    public static RSCApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);
        Iconify.with(new MaterialModule());
        ActiveAndroid.initialize(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public SharedPreferences getPreferences() {
        return getPreferences();
    }

    public AppStateEnum getAppState() {
        return RSCApp.appState;
    }

    public void setAppState(AppStateEnum appState) {
        RSCApp.appState = appState;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        RSCApp.loggedUser = loggedUser;
    }
}
