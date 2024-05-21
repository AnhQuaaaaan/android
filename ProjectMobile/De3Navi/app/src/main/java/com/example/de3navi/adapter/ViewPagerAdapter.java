package com.example.de3navi.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.de3navi.fragment.FragmentDanhsach;
import com.example.de3navi.fragment.FragmentThongtin;
import com.example.de3navi.fragment.FragmentTimkiem;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int pageNum;
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        pageNum=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentDanhsach();
            case 1: return new FragmentThongtin();
            case 2: return new FragmentTimkiem();
            default:return new FragmentDanhsach();
        }
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}
