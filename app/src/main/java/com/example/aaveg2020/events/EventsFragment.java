package com.example.aaveg2020.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaveg2020.FragmentChangeListener;
import com.example.aaveg2020.R;

import java.util.List;

public class EventsFragment extends Fragment implements OnEventClickListener {
    private Cluster cluster;
    private List<Event> events;
    private FragmentChangeListener fragmentChangeListener;
    private RecyclerView eventsRecyclerView;
    private EventAdapter adapter;

    public EventsFragment(Cluster cluster, FragmentChangeListener fragmentChangeListener) {
        this.cluster = cluster;
        this.fragmentChangeListener = fragmentChangeListener;
    }

    public EventsFragment(int contentLayoutId, Cluster cluster, FragmentChangeListener fragmentChangeListener) {
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
        return mView;
    }

    @Override
    public void onClickEvent(Event event) {
        EventDetailsFragment fragment = new EventDetailsFragment(event);
        fragmentChangeListener.onFragmentChange(fragment);
    }
}
