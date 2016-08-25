package com.dityish.apratim2k16;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public CharSequence getPageTitle(int position) {

        if (position == 0)
        {
            return "14th Oct";
        }
        if (position == 1)
        {
            return "15th Oct";
        }
        return null;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new Tab1();
            case 1:
                return new Tab2();
        }
        return null;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 2;
    }

}
