package com.example.myproject.activity_container.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.myproject.DTO.OrderDTO;
import com.example.myproject.R;
import com.example.myproject.adapter.OrderAdapter;
import com.example.myproject.dal.SQLiteHelper;
import com.example.myproject.utils.Utils;

import java.util.List;

public class ViewOrderActivity extends AppCompatActivity {
    private Toolbar toolBarViewOrder;
    private RecyclerView viewOrderRecycleView;
    private SQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        initView();
        actionBar();
        getOrderDTO();
    }

    private void getOrderDTO() {
        List<OrderDTO> listOrderDTO = db.getListOrderDTO(Utils.currentUser.getId());
        OrderAdapter orderAdapter = new OrderAdapter(getApplicationContext(), listOrderDTO);
        viewOrderRecycleView.setAdapter(orderAdapter);
    }

    private void actionBar() {
        setSupportActionBar(toolBarViewOrder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarViewOrder.setNavigationIcon(R.drawable.ic_back);
        toolBarViewOrder.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initView() {
        toolBarViewOrder = findViewById(R.id.toolBarViewOrder);
        viewOrderRecycleView = findViewById(R.id.viewOrderRecycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        viewOrderRecycleView.setLayoutManager(layoutManager);

        db = new SQLiteHelper(getApplicationContext());
    }
}