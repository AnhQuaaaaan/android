package com.example.myproject.activity_container.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.EventBus.UpdateDeleteEvent;
import com.example.myproject.R;
import com.example.myproject.activity_container.admin_activity.AddProductActivity;
import com.example.myproject.adapter.ProductAdapter;
import com.example.myproject.dal.SQLiteHelper;
import com.example.myproject.model.Product;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbarSearch;
    private EditText etSearch;
    private RecyclerView recycleViewSearch;
    private ProductAdapter productAdapter;
    List<Product> list;
    private SQLiteHelper db;

    private Product productToUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        actionBar();
        processEvent();
    }
    private void processEvent() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    list = db.getAllProducts();
                    productAdapter = new ProductAdapter(getApplicationContext(), list);
                    recycleViewSearch.setAdapter(productAdapter);
                } else {
                    resultSearch();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void resultSearch() {
        String search = etSearch.getText().toString().trim();
        list = db.getProductsByKey(search);
        productAdapter = new ProductAdapter(getApplicationContext(), list);
        recycleViewSearch.setAdapter(productAdapter);
    }
    private void actionBar() {
        setSupportActionBar(toolbarSearch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSearch.setNavigationIcon(R.drawable.ic_back);
        toolbarSearch.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initView() {
        toolbarSearch = findViewById(R.id.toolbarSearch);
        etSearch = findViewById(R.id.etSearch);
        recycleViewSearch = findViewById(R.id.recycleViewSearch);
        db = new SQLiteHelper(getApplicationContext());

        list = db.getAllProducts();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleViewSearch.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(getApplicationContext(), list);
        recycleViewSearch.setAdapter(productAdapter);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Sửa")) {
            editProduct();
        } else if (item.getTitle().equals("Xóa")) {
            deleteProduct();
        }

        return super.onContextItemSelected(item);
    }

    private void deleteProduct() {
        db.deleteProduct(productToUpdate.getId());
        list = db.getAllProducts();
        productAdapter = new ProductAdapter(getApplicationContext(), list);
        recycleViewSearch.setAdapter(productAdapter);
        Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
    }

    private void editProduct() {
        Intent intent = new Intent(getApplicationContext(), AddProductActivity.class);
        intent.putExtra("productEdit", productToUpdate);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void updateEvent(UpdateDeleteEvent event) {
        if (event != null) {
            productToUpdate = event.getProduct();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = db.getAllProducts();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleViewSearch.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(getApplicationContext(), list);
        recycleViewSearch.setAdapter(productAdapter);
    }
}