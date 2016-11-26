package com.egzepsn.rsc.rscapp.modules.initial;

import android.os.Bundle;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.commons.activity.BaseActivity;
import com.egzepsn.rsc.rscapp.enums.FragmentEnum;

public class InitialActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        if (findViewById(R.id.fragment_container) != null) {

            // in case of saved state fragment is set
            if (savedInstanceState != null) {
                return;
            }
            showFragment(FragmentEnum.LoginFragment, false);
        }
    }
}
