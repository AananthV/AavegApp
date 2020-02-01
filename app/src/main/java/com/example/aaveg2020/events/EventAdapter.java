package com.example.aaveg2020.events;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aaveg2020.R;

import java.util.List;

public class EventAdapter extends RecyclerView
        .Adapter<EventAdapter.ViewHolder> {

    private final List<Event> mValues;
    private final OnEventClickListener onItemClickListener;
    private Context mContext;

    EventAdapter(List<Event> items, OnEventClickListener onItemClickListener) {
        this.mValues = items;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.event_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event event = mValues.get(position);
        holder.bind(event, onItemClickListener);
        holder.eventTitleNameView.setText(event.getName());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView eventTitleNameView;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            eventTitleNameView = view.findViewById(R.id.event_title);
        }

        public void bind(final Event event, final OnEventClickListener listener) {
            view.setOnClickListener(v -> listener.onClickEvent(event));
        }
    }
}
