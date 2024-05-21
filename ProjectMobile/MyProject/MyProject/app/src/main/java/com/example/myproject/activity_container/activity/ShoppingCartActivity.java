package com.example.myproject.activity_container.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.EventBus.TotalMoneyEvent;
import com.example.myproject.R;
import com.example.myproject.adapter.ShoppingCartAdapter;
import com.example.myproject.model.ShoppingCart;
import com.example.myproject.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {
    public TextView cartEmpty, cartTotal;
    private Toolbar cartToolbar;
    private RecyclerView cartRecycleView;
    private Button cartButtonBuy;
    private List<ShoppingCart> list;
    private long total;

    private ShoppingCartAdapter shoppingCartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        initView();
        actionBar();
        processEvent();
        totalMoney();
    }

    private void totalMoney() {
        total = 0;
        for (int i = 0; i < Utils.listShoppingCart.size(); i++) {
            total += Utils.listShoppingCart.get(i).getPrice() * Utils.listShoppingCart.get(i).getQuantity();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        cartTotal.setText("" + decimalFormat.format(total) + " VNĐ");
    }

    private void processEvent() {

        if (Utils.listShoppingCart.size() == 0) {
            cartEmpty.setVisibility(View.VISIBLE);
        } else {
            shoppingCartAdapter = new ShoppingCartAdapter(getApplicationContext(), Utils.listShoppingCart, this);
            cartRecycleView.setAdapter(shoppingCartAdapter);
        }

        cartButtonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.listShoppingCart.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Bạn không có sản phẩm nào trong giỏ", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                intent.putExtra("totalMoney", total);
                startActivity(intent);
            }
        });
    }

    private void actionBar() {
        setSupportActionBar(cartToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cartToolbar.setNavigationIcon(R.drawable.ic_back);
        cartToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initView() {
        cartEmpty = findViewById(R.id. cartEmpty);
        cartToolbar = findViewById(R.id. cartToolbar);

        cartRecycleView = findViewById(R.id. cartRecycleView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        cartRecycleView.setLayoutManager(layoutManager);

        cartTotal = findViewById(R.id. cartTotal);
        cartButtonBuy = findViewById(R.id. cartButtonBuy);

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
    public void eventTotalMoney(TotalMoneyEvent event) {
        if (event != null) {
            totalMoney();
        }
    }
}