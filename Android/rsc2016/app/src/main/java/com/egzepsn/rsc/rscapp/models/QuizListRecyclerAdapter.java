package com.egzepsn.rsc.rscapp.models;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.egzepsn.rsc.rscapp.R;

import java.util.ArrayList;

/**
 * Created by kiki3 on 26.11.2016..
 */

public class QuizListRecyclerAdapter extends RecyclerView.Adapter<QuizListRecyclerAdapter.ViewHolder>  {
    private ArrayList<Event> events;
    private Context context;

    public QuizListRecyclerAdapter(ArrayList<Event> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.quizName.setText(events.get(position).getName());
        holder.quizDescription.setText(events.get(position).getDescription());
        holder.quizTime.setText(events.get(position).getDateTime());
        if (events.get(position).isOpen()) holder.cardView.setBackgroundColor(Color.parseColor("#EC407A"));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView quizName;
        public TextView quizDescription;
        public TextView quizTime;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            quizName = (TextView) itemView.findViewById(R.id.name_quiz);
            quizDescription = (TextView) itemView.findViewById(R.id.description);
            quizTime = (TextView) itemView.findViewById(R.id.quiz_time);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
