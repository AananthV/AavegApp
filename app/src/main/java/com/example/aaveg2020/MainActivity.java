package com.example.aaveg2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.aaveg2020.Scoreboard.ScoreboardFragment;
import com.example.aaveg2020.aboutus.CurlFragment;
import com.example.aaveg2020.events.Cluster;
import com.example.aaveg2020.events.EventsFragment;
import com.example.aaveg2020.events.EventsMainFragment;
import com.example.aaveg2020.fragments.AboutUsFragment;
import com.example.aaveg2020.login.LoginActivity;
import com.example.aaveg2020.splash.SplashActivity;
import com.example.aaveg2020.sponsors.SponsorsFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import com.example.aaveg2020.events.ClustersFragment;

public class MainActivity extends AppCompatActivity {

    TabLayout mainScreenTabLayout;
    Toolbar toolbar;
    ImageView logOut;
    ConstraintLayout mainActivityCL;
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref= this.getSharedPreferences("Aaveg2020", MODE_PRIVATE);
        editor = pref.edit();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_framelayout,new HomeFragment());
        fragmentTransaction.commit();
        mainScreenTabLayout = (TabLayout) findViewById(R.id.tab_layout_main_screen);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        mainActivityCL = findViewById(R.id.cl_main_activity);
        logOut=findViewById(R.id.logout);
        mainActivityCL.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.agatecard));

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doLogout();

            }
        });
        mainScreenTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment f;
                int currentPosition = tab.getPosition();
                switch (currentPosition) {
                    case 1:
                        f = new EventsMainFragment();
                        toolbar.setTitle("Events");
                        tab.setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.scheduleclicked));
                        mainScreenTabLayout.getTabAt(0).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.aboutus));
                        mainScreenTabLayout.getTabAt(2).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.home));
                        mainScreenTabLayout.getTabAt(3).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.leaderboard));
                        mainScreenTabLayout.getTabAt(4).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.sponsoricon));
                        break;

                    case 2:
                        f = new HomeFragment();
                        toolbar.setTitle("Home");
                        tab.setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.homeclicked));
                        mainScreenTabLayout.getTabAt(0).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.aboutus));
                        mainScreenTabLayout.getTabAt(1).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.schedule));
                        mainScreenTabLayout.getTabAt(3).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.leaderboard));
                        mainScreenTabLayout.getTabAt(4).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.sponsoricon));
                        break;

                    case 3:
                        f = new ScoreboardFragment();
                        toolbar.setTitle("Scoreboard");
                        tab.setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.leaderboardclicked));
                        mainScreenTabLayout.getTabAt(0).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.aboutus));
                        mainScreenTabLayout.getTabAt(2).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.home));
                        mainScreenTabLayout.getTabAt(1).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.schedule));
                        mainScreenTabLayout.getTabAt(4).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.sponsoricon));
                        break;

                    case 4:
                        f = new SponsorsFragment();
                        toolbar.setTitle("Sponsors");
                        tab.setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.sponsorsclicked));
                        mainScreenTabLayout.getTabAt(0).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.aboutus));
                        mainScreenTabLayout.getTabAt(2).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.home));
                        mainScreenTabLayout.getTabAt(3).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.leaderboard));
                        mainScreenTabLayout.getTabAt(1).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.schedule));
                        break;

                    default:
                        f=new CurlFragment();
                        toolbar.setTitle("About Us");
                        tab.setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.aboutusclicked));
                        mainScreenTabLayout.getTabAt(1).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.schedule));
                        mainScreenTabLayout.getTabAt(2).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.home));
                        mainScreenTabLayout.getTabAt(3).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.leaderboard));
                        mainScreenTabLayout.getTabAt(4).setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.sponsoricon));
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
    }
    private void doLogout(){
        editor.putString("user_id",null);
        editor.putString("APIToken",null);
        editor.putString("hostel",null);
        editor.apply();
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
