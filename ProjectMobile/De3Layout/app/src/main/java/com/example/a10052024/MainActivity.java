package com.example.a10052024;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.a10052024.adapter.FragmentAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button btPre,btNext;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        btNext = findViewById(R.id.btNext);
        btPre = findViewById(R.id.btPre);
        fab = findViewById(R.id.fab);
        btPre.setOnClickListener(this);
        btNext.setOnClickListener(this);
        fab.setOnClickListener(this);
        FragmentManager manager = getSupportFragmentManager();
        FragmentAdapter adapter = new FragmentAdapter(manager,3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setTabLayoutTitleColor();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.colorPink));
                        btNext.setBackgroundColor(getResources().getColor(R.color.colorPink));
                        btPre.setBackgroundColor(getResources().getColor(R.color.colorPink));
                        break;
                    case 1:
                        tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.colorBlue));
                        btNext.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                        btPre.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                        break;
                    case 2:
                        tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.colorGreen));
                        btNext.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        btPre.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==btNext){
            if (viewPager.getCurrentItem() == 2){
                return;
            }
            else{
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                setTabLayoutTitleColor();
            }
        }
        if(v==btPre){
            if (viewPager.getCurrentItem() == 0){
                return;
            }
            else{
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                setTabLayoutTitleColor();
            }
        }
        if(v == fab){
            startActivity(new Intent(MainActivity.this, AddActivity.class));
        }
    }

    private void setTabLayoutTitleColor() {
        switch (viewPager.getCurrentItem()){
            case 0:
                tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.colorPink));
                break;
            case 1:
                tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.colorBlue));
                break;
            case 2:
                tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.colorGreen));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem() == 0){
            super.onBackPressed();
        }
        else{
            viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
        }
    }
}