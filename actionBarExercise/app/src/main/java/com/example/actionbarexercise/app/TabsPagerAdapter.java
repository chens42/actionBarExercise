package com.example.actionbarexercise.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.actionbarexercise.app.fragment.TabFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private TabFragment tab1Fragment;
    private TabFragment tab2Fragment;
    private TabFragment tab3Fragment;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
               if(tab1Fragment == null) {
                   tab1Fragment = new TabFragment();
               }
                return tab1Fragment;
            case 1:
                if(tab2Fragment == null) {
                    tab2Fragment = new TabFragment();
                }
                return tab2Fragment;
            case 2:
                if(tab3Fragment == null) {
                    tab3Fragment = new TabFragment();
                }
                return tab3Fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
