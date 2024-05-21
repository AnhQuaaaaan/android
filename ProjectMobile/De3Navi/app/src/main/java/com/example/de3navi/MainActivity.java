package com.example.de3navi;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.de3navi.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView=findViewById(R.id.navigation);
        viewPager=findViewById(R.id.viewPager);
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(this);
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:navigationView.getMenu().findItem(R.id.mList).setChecked(true);
                        break;
                    case 1:navigationView.getMenu().findItem(R.id.mInfor).setChecked(true);
                        break;
                    case 2:navigationView.getMenu().findItem(R.id.mSearch).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mList:viewPager.setCurrentItem(0);
                        break;
                    case R.id.mInfor:viewPager.setCurrentItem(1);
                        break;
                    case R.id.mSearch:viewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == fab){
            startActivity(new Intent(MainActivity.this, AddActivity.class));
        }
    }
}