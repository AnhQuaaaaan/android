package com.example.btl.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.btl.Fragment.FragmentInfor;
import com.example.btl.Fragment.FragmentList;
import com.example.btl.Fragment.FragmentDesc;
import com.example.btl.Fragment.FragmentSearch;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int pageNum;
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        pageNum = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentList();
            case 1: return new FragmentInfor();
            case 2: return new FragmentDesc();
            case 3: return new FragmentSearch();
            default:return new FragmentList();
        }
    }

    @Override
    public int getCount() {
        return pageNum ;
    }
}
