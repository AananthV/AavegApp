package com.example.aaveg2020.events;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aaveg2020.R;

import java.util.List;

public class EventPointAdapter extends RecyclerView.Adapter<EventPointAdapter.ViewHolder> {
    private List<Point> pointList;
    private Context mContext;
    private Drawable lightBackground, darkBackground;

    public EventPointAdapter() {
    }

    public EventPointAdapter(List<Point> pointList, Context mContext) {
        this.pointList = pointList;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.event_point_row, parent, false);
        darkBackground = mContext.getResources().getDrawable(R.drawable.event_points_table_drawable_dark);
        lightBackground = mContext.getResources().getDrawable(R.drawable.event_points_table_drawable_light);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Point point = pointList.get(position);
        holder.rankText.setText(String.valueOf(point.getRank()));
        holder.pointText.setText(String.valueOf(point.getPoints()));
        if ((point.getRank() % 2) == 0) {
            holder.rankLayout.setBackground(darkBackground);
            holder.pointLayout.setBackground(darkBackground);
        } else {
            holder.rankLayout.setBackground(lightBackground);
            holder.pointLayout.setBackground(lightBackground);
        }
    }

    @Override
    public int getItemCount() {
        return pointList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView rankText, pointText;
        public final LinearLayout rankLayout, pointLayout;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            rankText = view.findViewById(R.id.point_rank);
            pointText = view.findViewById(R.id.point_value);
            rankLayout = view.findViewById(R.id.point_rank_layout);
            pointLayout = view.findViewById(R.id.point_value_layout);
        }
    }
}
