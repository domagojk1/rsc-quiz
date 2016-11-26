package com.egzepsn.rsc.rscapp.modules.main.pages;

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
import com.egzepsn.rsc.rscapp.models.Event;
import com.egzepsn.rsc.rscapp.models.QuizListRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by kiki3 on 26.11.2016..
 */

public class QuizListFragment extends Fragment {
    private ArrayList<Event> events;
    private RecyclerView recyclerView;
    private QuizListRecyclerAdapter adapter;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_list, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_quiz_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        imageView = (ImageView) view.findViewById(R.id.quiz_enabled);
        events = new ArrayList<>();

        events.add(new Event(true, "Quiz name", "18:00", "Ovo je kviz"));
        events.add(new Event(true, "Quiz name", "18:00", "Ovo je kviz"));
        events.add(new Event(true, "Quiz name", "18:00", "Ovo je kviz"));
        events.add(new Event(true, "Quiz name", "18:00", "Ovo je kviz"));

        adapter = new QuizListRecyclerAdapter(events, getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }


}
