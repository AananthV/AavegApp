package com.example.aaveg2020.Scoreboard.ui.Overall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaveg2020.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class TotalPointsAdapter extends RecyclerView.Adapter<TotalPointsAdapter.ViewHolder>{

    Context mContext;
    OverallModel scores;

    public TotalPointsAdapter(Context mContext, OverallModel scores) {
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
        switch (position){
            case 0:
                holder.hostelName.setText("Agate");
                Picasso.with(mContext).load(R.drawable.agate).into(holder.hostelImage);
                holder.hostelScore.setText(Integer.toString(scores.getAgate()));
                break;
            case 1:
                holder.hostelName.setText("Azurite");
                Picasso.with(mContext).load(R.drawable.azurite).into(holder.hostelImage);
                holder.hostelScore.setText(Integer.toString(scores.getAzurite()));
                break;
            case 2:
                holder.hostelName.setText("Bloodstone");
                Picasso.with(mContext).load(R.drawable.bloodstone).into(holder.hostelImage);
                holder.hostelScore.setText(Integer.toString(scores.getBloodstone()));
                break;
            case 3:
                holder.hostelName.setText("Cobalt");
                Picasso.with(mContext).load(R.drawable.cobalt).into(holder.hostelImage);
                holder.hostelScore.setText(Integer.toString(scores.getCobalt()));
                break;
            case 4:
                holder.hostelName.setText("Opal");
                Picasso.with(mContext).load(R.drawable.opal).into(holder.hostelImage);
                holder.hostelScore.setText(Integer.toString(scores.getOpal()));
                break;
        }

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
