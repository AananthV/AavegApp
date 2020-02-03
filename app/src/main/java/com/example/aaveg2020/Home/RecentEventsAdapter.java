package com.example.aaveg2020.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaveg2020.R;
import com.example.aaveg2020.Scoreboard.EventsModel;
import com.example.aaveg2020.events.EventsUtils;

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
        switch (events.get(position).getPosition())
        {
            case "1st":
                //holder.parent.setBackgroundResource(R.drawable.gold);
                holder.img.setImageResource(R.drawable.gold);
                break;
            case "2nd":
                //holder.parent.setBackgroundResource(R.drawable.silver);
                holder.img.setImageResource(R.drawable.silver);
                break;
            case "3rd":
                //holder.parent.setBackgroundResource(R.drawable.bronze);
                holder.parent.setBackgroundResource(R.drawable.bronze);
                break;
        }
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
        CardView card;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hostelEvent = itemView.findViewById(R.id.recent_events_card_text);
            parent=itemView.findViewById(R.id.hostel_events_parent);
            card=itemView.findViewById(R.id.hostel_events_card);
            img=itemView.findViewById(R.id.hostel_events_img);
        }
    }
}
