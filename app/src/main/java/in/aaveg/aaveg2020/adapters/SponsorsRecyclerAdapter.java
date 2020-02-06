package in.aaveg.aaveg2020.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aaveg2020.R;
import in.aaveg.aaveg2020.events.EventsUtils;
import in.aaveg.aaveg2020.sponsors.SibiActivity;
import in.aaveg.aaveg2020.sponsors.SponsorModel;

import java.util.List;

public class SponsorsRecyclerAdapter extends RecyclerView.Adapter<SponsorsRecyclerAdapter.SponsorHolderClass> {

    private List<SponsorModel> sponsorData;
    private Context mContext;
    private int count=0;

    public SponsorsRecyclerAdapter() {
        // empty
    }

    public SponsorsRecyclerAdapter(List<SponsorModel> sponsorData, Context context) {
        this.sponsorData = sponsorData;
        mContext = context;
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

        if(position==6){
            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count++;
                    if(count>=5)
                    {
                        count=0;
                        Intent intent=new Intent(mContext, SibiActivity.class);
                        mContext.startActivity(intent);
                    }
                }
            });
        }

        Glide.with(mContext)
                .load(sponsorData.get(position).logo)
                .into(holder.sponsorImage);

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
        ConstraintLayout parent;

        public SponsorHolderClass(@NonNull View itemView) {
            super(itemView);

            sponsorImage = (ImageView) itemView.findViewById(R.id.iv_sponsors);
            sponsorPartnerName = (TextView) itemView.findViewById(R.id.tv_partner_sponsor_item);
            sponsorName = (TextView) itemView.findViewById(R.id.tv_sponsor_name);
            parent=itemView.findViewById(R.id.sponsor_item_parent);
            parent.setBackground(EventsUtils.getHostelCard(itemView.getResources()));
        }
    }
}
