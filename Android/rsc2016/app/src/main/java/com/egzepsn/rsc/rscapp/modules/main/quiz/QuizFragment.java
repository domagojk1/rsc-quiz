package com.egzepsn.rsc.rscapp.modules.main.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.commons.fragment.BaseFragment;

/**
 * Created by kiki3 on 27.11.2016..
 */

public class QuizFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }
}
