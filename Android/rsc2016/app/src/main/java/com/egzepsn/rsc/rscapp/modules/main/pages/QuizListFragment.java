package com.egzepsn.rsc.rscapp.modules.main.pages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.commons.fragment.BaseFragment;
import com.egzepsn.rsc.rscapp.enums.ActivityEnum;
import com.egzepsn.rsc.rscapp.models.Event;
import com.egzepsn.rsc.rscapp.models.QuizListRecyclerAdapter;
import com.egzepsn.rsc.rscapp.modules.main.MainActivity;
import com.egzepsn.rsc.rscapp.modules.main.RecycleItemClickListener;
import com.egzepsn.rsc.rscapp.modules.main.teams.TeamsActivity;

import java.util.ArrayList;

/**
 * Created by kiki3 on 26.11.2016..
 */

public class QuizListFragment extends BaseFragment {
    OnItemSelectedListener mCallback;

    private ArrayList<Event> events;
    private RecyclerView recyclerView;
    private QuizListRecyclerAdapter adapter;
    private ImageView imageView;

    public interface OnItemSelectedListener {
        public void onSelect();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_list, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_quiz_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        events = new ArrayList<>();

        events.add(new Event(true, "Quiz name", "18:00", "Ovo je kviz"));
        events.add(new Event(true, "Quiz name", "18:00", "Ovo je kviz"));
        events.add(new Event(true, "Quiz name", "18:00", "Ovo je kviz"));
        events.add(new Event(true, "Quiz name", "18:00", "Ovo je kviz"));

        adapter = new QuizListRecyclerAdapter(events, getActivity());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecycleItemClickListener(getActivity(), recyclerView, new RecycleItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mCallback.onSelect();
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
