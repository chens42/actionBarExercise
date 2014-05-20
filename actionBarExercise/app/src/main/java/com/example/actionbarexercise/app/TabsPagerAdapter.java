package com.example.actionbarexercise.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new tab1Fragment();
            case 1:
                return new tab2Fragment();
            case 2:
                return new tab3Fragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
