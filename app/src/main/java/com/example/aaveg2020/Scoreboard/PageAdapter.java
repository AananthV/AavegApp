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

    private int NUMBER_OF_TABS=4;
    OverallFragment overallFragment;
    CulturalsFragment culturalsFragment;
    SpectrumFragment spectrumFragment;
    SportsFragment sportsFragment;

    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);
        overallFragment=new OverallFragment();
        culturalsFragment=new CulturalsFragment();
        spectrumFragment=new SpectrumFragment();
        sportsFragment=new SportsFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return overallFragment;
            case 1:
                return culturalsFragment;
            case 2:
                return spectrumFragment;
            case 3:
                return sportsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUMBER_OF_TABS;
    }
}
