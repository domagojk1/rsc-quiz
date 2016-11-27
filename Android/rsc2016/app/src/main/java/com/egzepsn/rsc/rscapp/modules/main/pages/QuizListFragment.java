package com.egzepsn.rsc.rscapp.modules.main.pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.models.Event;
import com.egzepsn.rsc.rscapp.models.QuizListRecyclerAdapter;
import com.egzepsn.rsc.rscapp.modules.main.RecycleItemClickListener;
import com.egzepsn.rsc.rscapp.modules.main.teams.TeamsActivity;
import com.egzepsn.rsc.rscapp.modules.main.teams.TeamsFragment;

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
                if (events.get(position).isEnabled()) {
                    Intent intent = new Intent(getActivity(), TeamsActivity.class);
                    startActivity(intent);
                }
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
