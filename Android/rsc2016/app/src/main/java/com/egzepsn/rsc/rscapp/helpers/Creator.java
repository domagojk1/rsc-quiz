package com.egzepsn.rsc.rscapp.helpers;

import android.support.v4.app.Fragment;

import com.egzepsn.rsc.rscapp.enums.ActivityEnum;
import com.egzepsn.rsc.rscapp.enums.FragmentEnum;
import com.egzepsn.rsc.rscapp.modules.initial.InitialActivity;
import com.egzepsn.rsc.rscapp.modules.initial.login.LoginFragment;
import com.egzepsn.rsc.rscapp.modules.initial.register.RegisterFragment;
import com.egzepsn.rsc.rscapp.modules.main.MainActivity;
import com.egzepsn.rsc.rscapp.modules.main.home.HomeFragment;


public class Creator {

    public static Fragment getFragmentFromEnum(FragmentEnum fragmentEnum) {
        switch (fragmentEnum) {
            case LoginFragment:
                return new LoginFragment();
            case RegisterFragment:
                return new RegisterFragment();
            case HomeFragment:
                return new HomeFragment();
            default:
                return new LoginFragment();
        }
    }

    public static Class getActivityFromEnum(ActivityEnum activityEnum) {
        switch (activityEnum) {
            case InitialActivity:
                return InitialActivity.class;
            case MainActivity:
                return MainActivity.class;
            default:
                return InitialActivity.class;
        }
    }
}

