package com.egzepsn.rsc.rscapp.modules.main.quiz;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.commons.fragment.BaseFragment;

/**
 * Created by kiki3 on 27.11.2016..
 */

public class QuestionFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_question, container, false);
    }
}
