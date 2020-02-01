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

import com.example.aaveg2020.Scoreboard.ScoreboardFragment;
import com.example.aaveg2020.events.Cluster;
import com.example.aaveg2020.events.EventsFragment;
import com.example.aaveg2020.events.EventsMainFragment;
import com.example.aaveg2020.fragments.AboutUsFragment;
import com.example.aaveg2020.login.LoginActivity;
import com.example.aaveg2020.sponsors.SponsorsFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import com.example.aaveg2020.events.ClustersFragment;

public class MainActivity extends AppCompatActivity {

    TabLayout mainScreenTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        /*FragmentChangeListener fragmentChangeListener = new FragmentChangeListener() {
            @Override
            public void onFragmentChange(Fragment fragment) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_framelayout, fragment);
                fragmentTransaction.commit();
            }
        };*/


        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_framelayout,new HomeFragment());
        fragmentTransaction.commit();
        mainScreenTabLayout = (TabLayout) findViewById(R.id.tab_layout_main_screen);

        mainScreenTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment f;
                switch (tab.getPosition()) {
                    case 0:
                        f = new AboutUsFragment();
                        break;

                    case 1:
                        f = new EventsMainFragment();
                        break;

                    case 2:
                        f = new HomeFragment();
                        break;

                    case 3:
                        f = new ScoreboardFragment();
                        break;

                    case 4:
                        f = new SponsorsFragment();
                        break;

                    default:
                        f=new AboutUsFragment();
                }
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_framelayout,f);
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        Objects.requireNonNull(mainScreenTabLayout.getTabAt(2)).select();


        /*FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_framelayout,new ScoreboardFragment());
        fragmentTransaction.commit();*/

    }
}
