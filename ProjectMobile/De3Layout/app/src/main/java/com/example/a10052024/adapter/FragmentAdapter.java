package com.example.a10052024.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.a10052024.fragment.FragmentDanhsach;
import com.example.a10052024.fragment.FragmentThongtin;
import com.example.a10052024.fragment.FragmentTimkiem;

public class FragmentAdapter extends FragmentPagerAdapter {
    private int pageNumber;
    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNumber= behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentDanhsach danhsach = new FragmentDanhsach();
                return danhsach;
            case 1:
                FragmentThongtin thongtin = new FragmentThongtin();
                return thongtin;
            case 2:
                FragmentTimkiem timkiem = new FragmentTimkiem();
                return timkiem;
        }
        return null;
    }

    @Override
    public int getCount() {
        return pageNumber;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Danh sach";
            case 1: return "Thong tin";
            case 2: return "Tim kiem";
        }
        return null;
    }
}
