package com.egzepsn.rsc.rscapp.modules.main.pages;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.app.RSCApp;
import com.egzepsn.rsc.rscapp.commons.fragment.BaseFragment;
import com.egzepsn.rsc.rscapp.models.Event;
import com.egzepsn.rsc.rscapp.models.QuizListRecyclerAdapter;
import com.egzepsn.rsc.rscapp.models.Team;
import com.egzepsn.rsc.rscapp.modules.main.RecycleItemClickListener;
import com.egzepsn.rsc.rscapp.rest.ApiModule;
import com.egzepsn.rsc.rscapp.rest.ApiService;
import com.facebook.CallbackManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        public void onSelect(Event event);
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
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApiService service = ApiModule.createService(ApiService.class);
        Call<ArrayList<Event>> call = service.getEvents();
        call.enqueue(new Callback<ArrayList<Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                events = new ArrayList<Event>(response.body());
                adapter = new QuizListRecyclerAdapter(events, getActivity());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                Log.d("ONFAILURE", "");
            }
        });

        recyclerView.addOnItemTouchListener(new RecycleItemClickListener(getActivity(), recyclerView, new RecycleItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (events.get(position).isOpen()) {
                    mCallback.onSelect(events.get(position));
                }
                else {
                    Snackbar.make(view, "Event is not available to participate", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));
    }
}
