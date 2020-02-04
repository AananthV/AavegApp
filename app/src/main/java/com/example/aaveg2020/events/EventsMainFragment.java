package com.example.aaveg2020.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.aaveg2020.R;

public class EventsMainFragment extends Fragment implements OnFragmentChangeListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_events_main, container, false);
        ClustersFragment fragment = new ClustersFragment(this);
        onFragmentChange(fragment);
        return mView;
    }

    @Override
    public void onFragmentChange(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.events_main_frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
