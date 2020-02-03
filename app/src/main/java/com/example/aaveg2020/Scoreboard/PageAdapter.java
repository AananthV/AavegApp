package com.example.aaveg2020.Scoreboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.aaveg2020.Scoreboard.ui.Cultural.CulturalsFragment;
import com.example.aaveg2020.Scoreboard.ui.Overall.OverallFragment;
import com.example.aaveg2020.Scoreboard.ui.Spectrum.SpectrumFragment;
import com.example.aaveg2020.Scoreboard.ui.sports.SportsFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private int NUMBER_OF_TABS = 4;
    OverallFragment overallFragment;
    CulturalsFragment culturalsFragment;
    SpectrumFragment spectrumFragment;
    SportsFragment sportsFragment;


    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        overallFragment = new OverallFragment();
        culturalsFragment = new CulturalsFragment();
        spectrumFragment = new SpectrumFragment();
        sportsFragment = new SportsFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OverallFragment();
            case 1:
                return new CulturalsFragment();
            case 2:
                return new SpectrumFragment();
            case 3:
                return new SportsFragment();
            default:
                return new OverallFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "OVERALL";
            case 1:
                return "CULTURALS";
            case 2:
                return "SPECTRUM";
            default:
                return "SPORTS";
        }

    }

    @Override
    public int getCount() {
        return NUMBER_OF_TABS;
    }
}
