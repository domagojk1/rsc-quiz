package com.egzepsn.rsc.rscapp.commons.fragment.view;

import com.egzepsn.rsc.rscapp.enums.FragmentEnum;

public interface FragmentView {
    void refresh();
    void showMessage(String message);
    void showError(String errorMessage);
    void showFragment(FragmentEnum fragmentEnum, boolean addToBackStack);
    void showToast(String message);
}
