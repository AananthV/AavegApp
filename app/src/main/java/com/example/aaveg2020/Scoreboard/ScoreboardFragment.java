package com.example.aaveg2020.Scoreboard;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.aaveg2020.R;
import com.example.aaveg2020.UserUtils;
import com.example.aaveg2020.events.EventsUtils;
import com.google.android.material.tabs.TabLayout;

public class ScoreboardFragment extends Fragment {

    ViewPager viewPager;
    static TabLayout tabLayout;
    int i=0;

    public ScoreboardFragment(int i) {
        this.i = i;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_scoreboard, container, false);
        tabLayout = mView.findViewById(R.id.tablayout);
        viewPager = mView.findViewById(R.id.viewPager);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PageAdapter pageAdapter = new PageAdapter(getChildFragmentManager());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setBackground(EventsUtils.getHostelColor(view.getResources()));
        TabLayout.Tab tab = tabLayout.getTabAt(i);
        tab.select();

    }
}
