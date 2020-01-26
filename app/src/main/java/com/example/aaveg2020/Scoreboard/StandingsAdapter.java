package com.example.aaveg2020.Scoreboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaveg2020.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.ViewHolder> {

    Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView hostelImage;
        TextView hostelName,hostelScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hostelImage=itemView.findViewById(R.id.standings_hostel_pic);
            hostelName=itemView.findViewById(R.id.standings_hostel_name);
            hostelScore=itemView.findViewById(R.id.standings_hostel_score);
        }
    }
}
