package com.dityish.apratim2k16;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

/**
 * A simple {@link Fragment} subclass.
 */
public class Schedule extends android.support.v4.app.Fragment {
    ViewPager Tab;
    TabPagerAdapter TabAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_schedule, container, false);

        TabAdapter = new TabPagerAdapter(getChildFragmentManager());
        Tab = (ViewPager)view.findViewById(R.id.pager);
        Tab.setOffscreenPageLimit(2);
        Tab.setAdapter(new TabPagerAdapter(getChildFragmentManager()));
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip)view.findViewById(R.id.tabs);

        tabs.setViewPager(Tab);
        return view;
    }

}
