package com.example.aaveg2020.Scoreboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import com.example.aaveg2020.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ScoreboardFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.fragment_scoreboard,container,false);
        TabLayout tabLayout = mView.findViewById(R.id.tablayout);
        TabItem tabOverall = mView.findViewById(R.id.tab_overall);
        TabItem tabCulturals = mView.findViewById(R.id.tab_culturals);
        TabItem tabSpectrum = mView.findViewById(R.id.tab_spectrum);
        TabItem tabSports = mView.findViewById(R.id.tab_sports);
        ViewPager viewPager = mView.findViewById(R.id.viewPager);


        PageAdapter pageAdapter = new PageAdapter(getChildFragmentManager());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        return mView;
    }
}
