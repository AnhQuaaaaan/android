package com.example.myproject.activity_container.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.adapter.CategoryAdapter;
import com.example.myproject.adapter.NewProductAdapter;
import com.example.myproject.activity_container.admin_activity.ManageActivity;
import com.example.myproject.dal.SQLiteHelper;
import com.example.myproject.activity_container.login_activity.LoginActivity;
import com.example.myproject.model.Category;
import com.example.myproject.model.Product;
import com.example.myproject.model.User;
import com.example.myproject.utils.Utils;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerViewHome;
    private NavigationView navigationView;
    private ListView listViewHome;
    private CategoryAdapter categoryAdapter;
    private List<Category> listCategories;
    private DrawerLayout drawerLayout;
    private SQLiteHelper db;
    private List<Product> listNewProducts;
    private NewProductAdapter newProductAdapter;
    private NotificationBadge badge;
    private FrameLayout cartFrameLayout;
    private ImageView searchHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        actionBar();
        actionViewFlipper();
        getData();
        onClickListener();
    }

    private void getData() {
        // get list category
        listCategories = db.getAll();
        if (Utils.currentUser.getRole() == 0) {
            listCategories.remove(6);
        }
        categoryAdapter = new CategoryAdapter(getApplicationContext(), listCategories);
        listViewHome.setAdapter(categoryAdapter);

        // get list new product
        listNewProducts = db.getTop10Product();
        newProductAdapter = new NewProductAdapter(getApplicationContext(), listNewProducts);
        recyclerViewHome.setAdapter(newProductAdapter);

    }


    private void onClickListener() {
        listViewHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent home = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(home);
                } else if (position == 1) {
                    Intent mobile = new Intent(getApplicationContext(), ProductActivity.class);
                    mobile.putExtra("categoryId", 2);
                    startActivity(mobile);
                } else if (position == 2) {
                    Intent laptop = new Intent(getApplicationContext(), ProductActivity.class);
                    laptop.putExtra("categoryId", 3);
                    startActivity(laptop);
                } else if (position == 3) {
                    Intent watch = new Intent(getApplicationContext(), ProductActivity.class);
                    watch.putExtra("categoryId", 4);
                    startActivity(watch);
                } else if (position == 4) {
                    Intent camera = new Intent(getApplicationContext(), ProductActivity.class);
                    camera.putExtra("categoryId", 5);
                    startActivity(camera);
                } else if (position == 5) {
                    Intent order = new Intent(getApplicationContext(), ViewOrderActivity.class);
                    startActivity(order);
                }

                if (Utils.currentUser.getRole() == 0) {
                    if (position == 6) {
                        // dang xuat
                        Utils.currentUser = null;
                        Utils.listShoppingCart = null;
                        Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(login);
                        finish();
                    }
                } else {
                    if (position == 6) {
                        // manage
                        Intent manage = new Intent(getApplicationContext(), ManageActivity.class);
                        startActivity(manage);
                    } else if (position == 7) {
                        // dang xuat
                        Utils.currentUser = null;
                        Utils.listShoppingCart = null;
                        Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(login);
                        finish();
                    }
                }
            }
        });
    }

    private void actionViewFlipper() {
        List<String> adsScreen = new ArrayList<>();
        adsScreen.add("https://img.freepik.com/free-vector/social-media-cover-template-cyber-monday_23-2149707779.jpg?t=st=1713452337~exp=1713452937~hmac=6bd74dc10b9e46e69d44c30cd8645fe79cfe379d2db40f6f6700cd0fe6708f59");
        adsScreen.add("https://as1.ftcdn.net/v2/jpg/03/02/89/10/1000_F_302891035_TL1PUKgMNM8MNe0gVdKnw2zC3LPDDcmX.jpg");
        adsScreen.add("https://img.freepik.com/premium-psd/cyber-monday-super-sale-facebook-cover-banner-template_106176-3276.jpg?w=1380");

        for(int i = 0; i < adsScreen.size(); i++) {
            ImageView imgView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(adsScreen.get(i)).into(imgView);
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imgView);
        }

        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

    private void actionBar() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationIcon(R.drawable.ic_menu);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    public void initView(){
        toolBar = findViewById(R.id.toolBarHome);
        viewFlipper = findViewById(R.id.viewFlipperHome);
        recyclerViewHome = findViewById(R.id.recycleViewHome);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewHome.setLayoutManager(layoutManager);
        searchHome = findViewById(R.id.searchHome);
//        recyclerViewHome.setHasFixedSize(true);
        navigationView = findViewById(R.id.navigationView);
        listViewHome = findViewById(R.id.listViewHome);
        drawerLayout = findViewById(R.id.drawerLayout);
        db = new SQLiteHelper(getApplicationContext());
        badge = findViewById(R.id.cartQuantity);
        cartFrameLayout = findViewById(R.id.cartFrameLayout);

        listCategories = new ArrayList<>();
        listNewProducts = new ArrayList<>();

        if (Utils.listShoppingCart == null) {
            Utils.listShoppingCart = new ArrayList<>();
        }

        badge.setText(String.valueOf(Utils.listShoppingCart.size()));

        cartFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShoppingCartActivity.class);
                startActivity(intent);
            }
        });

        searchHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        badge.setText(String.valueOf(Utils.listShoppingCart.size()));
        getData();
    }
}