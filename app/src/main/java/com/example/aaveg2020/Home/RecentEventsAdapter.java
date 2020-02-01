package com.example.aaveg2020.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaveg2020.R;
import com.example.aaveg2020.Scoreboard.EventsModel;

import java.util.ArrayList;

public class RecentEventsAdapter extends RecyclerView.Adapter<RecentEventsAdapter.ViewHolder> {

    ArrayList<EventsModel> events;

    public RecentEventsAdapter(ArrayList<EventsModel> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView= LayoutInflater.from(parent.getContext()).inflate(R.layout.hostel_events_card,parent,false);
        ViewHolder holder=new ViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hostelEvent.setText(events.get(position).getEvent()+"\n"+events.get(position).getPosition());
       // holder.parent.getLayoutParams().height=holder.parent.getWidth();
    }

    @Override
    public int getItemCount() {
        if(events!=null)
            return events.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout parent;
        TextView hostelEvent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hostelEvent = itemView.findViewById(R.id.recent_events_card_text);
            parent=itemView.findViewById(R.id.hostel_events_parent);
        }
    }
}
