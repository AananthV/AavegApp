package com.example.aaveg2020.events;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.aaveg2020.R;

import java.util.List;

public class EventsFragment extends Fragment implements OnEventClickListener {
    private Cluster cluster;
    private List<Event> events;
    private RecyclerView eventsRecyclerView;
    private OnFragmentChangeListener fragmentChangeListener;
    private EventAdapter adapter;

    View dialog;
    AlertDialog loadingDialog;
    Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialog = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null);
        TextView tv = dialog.findViewById(R.id.progressDialog_textView);
        tv.setText("Loading...");
        loadingDialog = new AlertDialog.Builder(context).setView(dialog).setCancelable(false).create();
        loadingDialog.show();
    }

    public EventsFragment(Cluster cluster, OnFragmentChangeListener fragmentChangeListener) {
        this.cluster = cluster;
        this.fragmentChangeListener = fragmentChangeListener;
    }

    public EventsFragment(int contentLayoutId, Cluster cluster, OnFragmentChangeListener fragmentChangeListener) {
        super(contentLayoutId);
        this.cluster = cluster;
        this.fragmentChangeListener = fragmentChangeListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_events, container, false);
        eventsRecyclerView = mView.findViewById(R.id.events_recycler_view);
        eventsRecyclerView.setHasFixedSize(true);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new EventAdapter(cluster.getEvents(), this);
        eventsRecyclerView.setAdapter(adapter);

        final TextView titleText = mView.findViewById(R.id.cluster_detail_title);
        final ImageView backgroundImg = mView.findViewById(R.id.fragment_events_bg_img);


        titleText.setText(cluster.getId());
        Glide.with(getContext())
                .load(EventsUtils.getClusterDrawableOrange(cluster.getId(), getResources()))
                .apply(RequestOptions.fitCenterTransform())
                .into(backgroundImg);

        loadingDialog.dismiss();

        return mView;
    }

    @Override
    public void onClickEvent(Event event) {
        EventDetailsFragment fragment = new EventDetailsFragment(event);
        fragmentChangeListener.onFragmentChange(fragment);
    }
}
