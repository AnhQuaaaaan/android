package com.example.myproject.activity_container.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.adapter.ProductAdapter;
import com.example.myproject.dal.SQLiteHelper;
import com.example.myproject.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private Toolbar toolBarMobile;
    private RecyclerView recycleViewMobile;

    private ProductAdapter productAdapter;
    List<Product> list;

    private SQLiteHelper db;
    private int page = 1;
    private int categoryId;
    private LinearLayoutManager layoutManager;
    Handler handler = new Handler();
    boolean isLoading = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        categoryId = getIntent().getIntExtra("categoryId", 2);

        initView();
        actionBar();

        if (categoryId == 2) {
            toolBarMobile.setTitle("Điện thoại");
        } else if (categoryId == 3) {
            toolBarMobile.setTitle("Laptop");
        } else if (categoryId == 4) {
            toolBarMobile.setTitle("Đồng hồ");
        } else if (categoryId == 5) {
            toolBarMobile.setTitle("Camera");
        }

       if (productAdapter == null) {
           list = db.getProductByCategoryId(page, categoryId);
           productAdapter = new ProductAdapter(getApplicationContext(), list);
           recycleViewMobile.setAdapter(productAdapter);
       } else {
           int pos = list.size() - 1;
           int quantityAdd = db.getProductByCategoryId(page, categoryId).size();
           for(int i = 0; i < quantityAdd; i++) {
               list.add(db.getProductByCategoryId(page, categoryId).get(i));
           }
           productAdapter.notifyItemRangeInserted(pos, quantityAdd);
       }

        addEventLoading();
    }

    private void addEventLoading() {
        recycleViewMobile.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoading == false) {
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == (list.size() - 1 )) {
                        if (db.getProductByCategoryId(page, categoryId).size() ==  0) {
                            Toast.makeText(ProductActivity.this, "Hết dữ liệu", Toast.LENGTH_SHORT).show();
                        }
                        isLoading = true;
                        loadingMore();
                    }
                }
            }
        });
    }

    private void loadingMore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                list.add(null);
                productAdapter.notifyItemInserted(list.size() - 1);
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.remove(list.size() - 1);
                productAdapter.notifyItemRemoved(list.size());
                page += 1;

                if (productAdapter == null) {
                    list = db.getProductByCategoryId(page, categoryId);
                    productAdapter = new ProductAdapter(getApplicationContext(), list);
                    recycleViewMobile.setAdapter(productAdapter);
                } else {
                    int pos = list.size() - 1;
                    int quantityAdd = db.getProductByCategoryId(page, categoryId).size();
                    for(int i = 0; i < quantityAdd; i++) {
                        list.add(db.getProductByCategoryId(page, categoryId).get(i));
                    }
                    productAdapter.notifyItemRangeInserted(pos, quantityAdd);
                }

                productAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

    private void actionBar() {
        setSupportActionBar(toolBarMobile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarMobile.setNavigationIcon(R.drawable.ic_back);
        toolBarMobile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        toolBarMobile = findViewById(R.id.toolBarMobile);
        recycleViewMobile = findViewById(R.id.recycleViewMobile);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycleViewMobile.setLayoutManager(layoutManager);
        db = new SQLiteHelper(getApplicationContext());
        list  = new ArrayList<>();
    }
}