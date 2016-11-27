package com.egzepsn.rsc.rscapp.modules.main.quiz;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.commons.fragment.BaseFragment;
import com.egzepsn.rsc.rscapp.models.Event;
import com.egzepsn.rsc.rscapp.modules.main.pages.QuizListFragment;

/**
 * Created by kiki3 on 27.11.2016..
 */

public class QuizFragment extends BaseFragment {
    OnFinishedListener mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    public interface OnFinishedListener {
         void onFinish();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (QuizFragment.OnFinishedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCallback.onFinish();
            }
        }, 1000);
    }
}
