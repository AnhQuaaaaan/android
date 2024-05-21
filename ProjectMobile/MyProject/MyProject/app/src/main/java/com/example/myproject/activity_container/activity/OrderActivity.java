package com.example.myproject.activity_container.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.dal.SQLiteHelper;
import com.example.myproject.model.Order;
import com.example.myproject.model.ShoppingCart;
import com.example.myproject.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    private Toolbar orderToolbar;
    private TextView orderTotalMoney, orderPhoneNumber, orderEmail;
    private TextInputEditText orderAddress;
    private Button orderButton;
    private long total;

    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        actionBar();
        processEvent();
    }

    private void processEvent() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        total = getIntent().getLongExtra("totalMoney", 0);
        orderTotalMoney.setText("" + decimalFormat.format(total) + " VNĐ");
        orderEmail.setText(Utils.currentUser.getEmail());
        orderPhoneNumber.setText(Utils.currentUser.getPhoneNumber());

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = orderAddress.getText().toString().trim();
                if (TextUtils.isEmpty(address)) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập địa chỉ giao hàng", Toast.LENGTH_SHORT).show();
                } else {
                    String totalMoney = total + "";
                    int totalQuantity = 0;
                    for (ShoppingCart cart : Utils.listShoppingCart) {
                        totalQuantity += cart.getQuantity();
                    }
                    String email = Utils.currentUser.getEmail();
                    String phoneNumber = Utils.currentUser.getPhoneNumber();
                    Order order = new Order(totalMoney, totalQuantity, email, phoneNumber, address, Utils.currentUser);
                    db.addOrder(order, Utils.listShoppingCart);
                    Toast.makeText(getApplicationContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();

                    // xoa shopping cart di
                    Utils.listShoppingCart = new ArrayList<>();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void actionBar() {
        setSupportActionBar(orderToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        orderToolbar.setNavigationIcon(R.drawable.ic_back);
        orderToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        orderToolbar = findViewById(R.id. orderToolbar);
        orderTotalMoney = findViewById(R.id. orderTotalMoney);
        orderPhoneNumber = findViewById(R.id. orderPhoneNumber);
        orderEmail = findViewById(R.id. orderEmail);
        orderAddress = findViewById(R.id. orderAddress);
        orderButton = findViewById(R.id. orderButton);

        db = new SQLiteHelper(getApplicationContext());
    }
}