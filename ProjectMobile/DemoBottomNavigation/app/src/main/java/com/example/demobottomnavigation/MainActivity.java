package com.example.demobottomnavigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.demobottomnavigation.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewPager=findViewById(R.id.viewPager);
        floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_LONG).show();
            }
        });
        navigationView=findViewById(R.id.navigation);
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager(),4);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: navigationView.getMenu().findItem(R.id.mHome).setChecked(true);
                        break;
                    case 1: navigationView.getMenu().findItem(R.id.mNoti).setChecked(true);
                        break;
                    case 2: navigationView.getMenu().findItem(R.id.mSearch).setChecked(true);
                        break;
                    case 3: navigationView.getMenu().findItem(R.id.mCafe).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int a = item.getItemId();
                switch (a){
                    case R.id.mHome:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.mNoti:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.mSearch:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.mCafe:
                        viewPager.setCurrentItem(3);
                        break;
                }
//                if(a == R.id.mHome){
//                    viewPager.setCurrentItem(0);
//                }
//                if(a == R.id.mSearch){
//                    viewPager.setCurrentItem(1);
//                }
//                if(a == R.id.mNoti){
//                    viewPager.setCurrentItem(2);
//                }
//                if(a == R.id.mCafe){
//                    viewPager.setCurrentItem(3);
//                }
                return true;
            }
        });
    }
}