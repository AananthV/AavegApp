package com.example.aaveg2020.events;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.aaveg2020.MainActivity;
import com.example.aaveg2020.R;

import java.util.Objects;

public class EventsMainFragment extends Fragment implements OnFragmentChangeListener {
    FragmentTransaction transaction;
    public static Boolean isCluster=true;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_events_main, container, false);
        ClustersFragment fragment = new ClustersFragment(this);
        onFragmentChange(fragment,"ClustersFragment");

        return mView;
    }

    @Override
    public void onFragmentChange(Fragment fragment,String tag) {
       transaction = getFragmentManager().beginTransaction();

        if(!(fragment instanceof ClustersFragment)){
            transaction.replace(R.id.events_main_frame_layout, fragment,tag).addToBackStack(null).commit();
        }

        else{
            transaction.replace(R.id.events_main_frame_layout, fragment,tag).commit();
        }

    }




}
