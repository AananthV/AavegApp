package com.example.aaveg2020;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.aaveg2020.Scoreboard.ScoreboardFragment;
import com.example.aaveg2020.fragments.AboutUsFragment;
import com.example.aaveg2020.fragments.EventsFragment;
import com.example.aaveg2020.fragments.HomeFragment;
import com.example.aaveg2020.sponsors.SponsorsFragment;

public class ViewPagerAdapterHome extends FragmentStatePagerAdapter {

    public ViewPagerAdapterHome(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment f;
        switch (position) {
            case 0 :
                f= new AboutUsFragment();
                break;
            case 1 :
                f = new EventsFragment();
                break;
            case 2 :
                f = new HomeFragment();
                break;
            case 3 :
                f = new ScoreboardFragment();
                break;
            case 4 :
                f = new SponsorsFragment();
                break;
            default :
                f= new AboutUsFragment();
        }
        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0 :
                return "A";
            case 1 :
                return "E";
            case 2 :
                return "H";
            case 3 :
                return "L";
            case 4 :
                return "S";
            default :
                return "A";
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
