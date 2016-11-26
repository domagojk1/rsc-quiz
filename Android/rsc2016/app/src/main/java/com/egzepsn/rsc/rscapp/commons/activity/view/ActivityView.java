package com.egzepsn.rsc.rscapp.commons.activity.view;


import com.egzepsn.rsc.rscapp.enums.ActivityEnum;
import com.egzepsn.rsc.rscapp.enums.FragmentEnum;

public interface ActivityView {
    void showFragment(FragmentEnum fragmentEnum, boolean addToBackStack);
    void showActivity(ActivityEnum activityEnum);
    void showToast(String toastMessage);
    void showTitle(String title);
    void showMessage(String message);
    void pressBack();
    void clearBackStack();
    void clearBackStack(int levelsIgnored);
}
