package com.example.aaveg2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.aaveg2020.Home.HomeFragment;
import androidx.viewpager.widget.ViewPager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.view.View;

import com.example.aaveg2020.Scoreboard.ScoreboardFragment;
import com.example.aaveg2020.login.LoginActivity;
import com.google.android.material.tabs.TabLayout;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

import com.example.aaveg2020.events.ClustersFragment;

public class MainActivity extends AppCompatActivity implements FragmentChangeListener {

    TabLayout mainScreenTabLayout;
    ViewPager mainScreenViewPager;
    ViewPagerAdapterHome adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_framelayout,new HomeFragment());
        fragmentTransaction.commit();
        mainScreenTabLayout = (TabLayout) findViewById(R.id.tab_layout_main_screen);
        mainScreenViewPager = (ViewPager) findViewById(R.id.view_pager_main_screen);
        adapter = new ViewPagerAdapterHome(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mainScreenViewPager.setAdapter(adapter);
        mainScreenTabLayout.setupWithViewPager(mainScreenViewPager);

        mainScreenTabLayout.getTabAt(2).select();

    }

    @Override
    public void onFragmentChange(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_framelayout, fragment);
        fragmentTransaction.commit();
    }
}
