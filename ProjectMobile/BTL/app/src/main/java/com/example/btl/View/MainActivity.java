package com.example.btl.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.btl.Adapter.ViewPagerAdapter;
import com.example.btl.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private FloatingActionButton fab,showcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        navigationView=findViewById(R.id.navigation);
        viewPager=findViewById(R.id.viewPager);
        fab=findViewById(R.id.fab);
        showcart=findViewById(R.id.showcart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOpenAddActivity = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intentOpenAddActivity);
            }
        });
        showcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent opencart = new Intent(MainActivity.this, CartActivity.class);
                startActivity(opencart);
            }
        });
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
                    case 3:navigationView.getMenu().findItem(R.id.mSearch1).setChecked(true);
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
                    case R.id.mSearch1:viewPager.setCurrentItem(3);
                        break;
                }
                return false;
            }
        });
    }
}