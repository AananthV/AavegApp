package com.example.aaveg2020.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.aaveg2020.R;

import java.util.List;

public class ClusterAdapter extends RecyclerView
        .Adapter<ClusterAdapter.ViewHolder> {

    private List<Cluster> clusters;

    private OnClusterClickListener onItemClickListener;

    public ClusterAdapter(List<Cluster> clusters, OnClusterClickListener onItemClickListener) {
        this.clusters = clusters;
        this.onItemClickListener = onItemClickListener;
    }
    private Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.cluster_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cluster cluster = clusters.get(position);
        holder.bind(cluster, onItemClickListener);
        String clusterName = cluster.getId();
//        holder.clusterImg.setImageDrawable(EventsUtils.getClusterDrawable(clusterName, mContext.getResources()));
        holder.clusterNameText.setText(clusterName);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new FitCenter(), new RoundedCorners(16));
        Glide.with(mContext)
                .load(EventsUtils.getClusterDrawable(clusterName, mContext.getResources()))
                .apply(requestOptions)
                .into(holder.clusterImg);
    }

    @Override
    public int getItemCount() {
        return clusters.size();
    }

    public List<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(List<Cluster> clusters) {
        this.clusters = clusters;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView eventTitleNameView;
        final ImageView clusterImg;
        final TextView clusterNameText;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            eventTitleNameView = view.findViewById(R.id.event_title);
            clusterImg = view.findViewById(R.id.cluster_image);
            clusterNameText = view.findViewById(R.id.cluster_name);
        }

        public void bind(final Cluster cluster, final OnClusterClickListener listener) {
            view.setOnClickListener(v -> listener.onClickCluster(cluster));
        }
    }
}

