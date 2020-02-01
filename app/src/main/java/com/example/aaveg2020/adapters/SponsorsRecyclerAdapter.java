package com.example.aaveg2020.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaveg2020.R;
import com.example.aaveg2020.sponsors.SponsorModel;

import java.util.List;

public class SponsorsRecyclerAdapter extends RecyclerView.Adapter<SponsorsRecyclerAdapter.SponsorHolderClass> {

    private List<SponsorModel> sponsorData;

    public SponsorsRecyclerAdapter() {
        // empty
    }

    public SponsorsRecyclerAdapter(List<SponsorModel> sponsorData) {
        this.sponsorData = sponsorData;
    }

    @NonNull
    @Override
    public SponsorHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_single_sponsor, parent, false);

        return new SponsorHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SponsorHolderClass holder, int position) {
        holder.sponsorName.setText(sponsorData.get(position).name);
        holder.sponsorPartnerName.setText(sponsorData.get(position).title);
        //holder.sponsorImage.setImageResource();
    }

    @Override
    public int getItemCount() {
        return sponsorData.size();
    }

    public class SponsorHolderClass extends RecyclerView.ViewHolder {

        ImageView sponsorImage;
        TextView sponsorPartnerName;
        TextView sponsorName;

        public SponsorHolderClass(@NonNull View itemView) {
            super(itemView);

            sponsorImage = (ImageView) itemView.findViewById(R.id.iv_sponsors);
            sponsorPartnerName = (TextView) itemView.findViewById(R.id.tv_partner_sponsor_item);
            sponsorName = (TextView) itemView.findViewById(R.id.tv_sponsor_name);

        }
    }
}
