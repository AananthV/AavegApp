package com.example.aaveg2020.Scoreboard.ui.Overall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaveg2020.Home.HomeFragment;
import com.example.aaveg2020.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TotalPointsAdapter extends RecyclerView.Adapter<TotalPointsAdapter.ViewHolder>{

    Context mContext;
    ArrayList<HomeFragment.HostelScore> scores;

    public TotalPointsAdapter(Context mContext, ArrayList<HomeFragment.HostelScore> scores) {
        this.mContext = mContext;
        this.scores = scores;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standings_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.hostelName.setText(scores.get(position).getName());
                switch (scores.get(position).getName()){
                    case "Agate":
                        Picasso.with(mContext).load(R.drawable.agate).into(holder.hostelImage);
                        break;
                    case "Azurite":
                        Picasso.with(mContext).load(R.drawable.azurite).into(holder.hostelImage);
                        break;
                    case "Bloodstone":
                        Picasso.with(mContext).load(R.drawable.bloodstone).into(holder.hostelImage);
                        break;
                    case "Cobalt":
                        Picasso.with(mContext).load(R.drawable.cobalt).into(holder.hostelImage);
                        break;
                    case "Opal":
                        Picasso.with(mContext).load(R.drawable.opal).into(holder.hostelImage);
                        break;

                }
                holder.hostelScore.setText(Integer.toString(scores.get(position).getScore()));
    }

    @Override
    public int getItemCount() {
        return 5;
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
