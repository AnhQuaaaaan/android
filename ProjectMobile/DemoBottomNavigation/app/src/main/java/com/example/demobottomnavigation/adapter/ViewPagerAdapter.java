package com.example.demobottomnavigation.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.demobottomnavigation.fragment.FragmentCafe;
import com.example.demobottomnavigation.fragment.FragmentHome;
import com.example.demobottomnavigation.fragment.FragmentNotification;
import com.example.demobottomnavigation.fragment.FragmentSearch;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int pageNum;
    public ViewPagerAdapter(@NonNull FragmentManager fm,int behavior) {

        super(fm,behavior);
        pageNum = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentHome();
            case 1: return new FragmentNotification();
            case 2: return new FragmentSearch();
            case 3: return new FragmentCafe();
            default:return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}
