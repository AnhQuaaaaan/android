package com.example.myproject.activity_container.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.model.Product;
import com.example.myproject.model.ShoppingCart;
import com.example.myproject.utils.Utils;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView productDetailName, productDetailPrice, productDetailDescription;
    private Button productDetailBtnCart;
    private ImageView productDetailImage;
    private Spinner productDetailSpinner;
    private Toolbar productDetailToolbar;
    private NotificationBadge badge;
    private FrameLayout cartFrameLayout;

    private Product p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        initView();
        actionBar();
        getData();
        processEvent();
    }

    private void processEvent() {
        productDetailBtnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShoppingCart();
            }
        });
    }

    private void addShoppingCart() {
        if (Utils.listShoppingCart.size() > 0) {
            boolean check = false;
            int quantity = Integer.parseInt(productDetailSpinner.getSelectedItem().toString());

            for (int i = 0; i < Utils.listShoppingCart.size(); i++) {
                if (Utils.listShoppingCart.get(i).getId() == p.getId()) {
                    int newQuantity = Utils.listShoppingCart.get(i).getQuantity() + quantity;
                    Utils.listShoppingCart.get(i).setQuantity(newQuantity);
//                    long price = Long.parseLong(p.getPrice()) * newQuantity;
                    long price = Long.parseLong(p.getPrice());
                    Utils.listShoppingCart.get(i).setPrice(price);
                    check = true;
                }
            }

            if (!check) {
//                long price = Long.parseLong(p.getPrice()) * quantity;
                long price = Long.parseLong(p.getPrice());
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.setId(p.getId());
                shoppingCart.setName(p.getName());
                shoppingCart.setPrice(price);
                shoppingCart.setQuantity(quantity);
                shoppingCart.setImage(p.getImage());
                Utils.listShoppingCart.add(shoppingCart);
            }

        } else {
            int quantity = Integer.parseInt(productDetailSpinner.getSelectedItem().toString());
//            long price = Long.parseLong(p.getPrice()) * quantity;
            long price = Long.parseLong(p.getPrice());
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setId(p.getId());
            shoppingCart.setName(p.getName());
            shoppingCart.setPrice(price);
            shoppingCart.setQuantity(quantity);
            shoppingCart.setImage(p.getImage());
            Utils.listShoppingCart.add(shoppingCart);
        }

        Toast.makeText(this, "Bạn vừa thêm sản phẩm này vào giỏ hàng", Toast.LENGTH_SHORT).show();
        badge.setText(String.valueOf(Utils.listShoppingCart.size()));
    }

    private void getData() {
        p = (Product) getIntent().getSerializableExtra("product");
        productDetailName.setText(p.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        productDetailPrice.setText("Giá: " + decimalFormat.format(Double.parseDouble(p.getPrice())) + " VNĐ");
        // xu ly mo ta
        productDetailDescription.setText(processingDescription(p.getDescription()));

        Glide.with(getApplicationContext()).load(p.getImage()).into(productDetailImage);
        Integer[] quantity = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapterSpinner = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                quantity);
        productDetailSpinner.setAdapter(adapterSpinner);
    }

    private String processingDescription(String description) {
        String[] tmp = description.split("\n");
        String res = "";
        for (String x : tmp) {
            res += x.trim() + "\n\n";
        }
        return res;
    }

    private void actionBar() {
        setSupportActionBar(productDetailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        productDetailToolbar.setNavigationIcon(R.drawable.ic_back);
        productDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        productDetailName = findViewById(R.id.productDetailName);
        productDetailPrice = findViewById(R.id.productDetailPrice);
        productDetailDescription = findViewById(R.id.productDetailDescription);
        productDetailImage = findViewById(R.id.productDetailImage);
        productDetailBtnCart = findViewById(R.id.productDetailBtnCart);
        productDetailSpinner = findViewById(R.id.productDetailSpinner);
        productDetailToolbar = findViewById(R.id.productDetailToolbar);

        cartFrameLayout = findViewById(R.id.cartFrameLayout);
        cartFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShoppingCartActivity.class);
                startActivity(intent);
            }
        });

        p = new Product();
        badge = findViewById(R.id.cartQuantity);

        if (Utils.listShoppingCart != null) {
            badge.setText(String.valueOf(Utils.listShoppingCart.size()));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        badge.setText(String.valueOf(Utils.listShoppingCart.size()));
    }
}