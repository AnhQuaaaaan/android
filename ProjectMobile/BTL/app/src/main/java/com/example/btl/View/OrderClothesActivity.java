package com.example.btl.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.btl.Model.Cart;
import com.example.btl.Model.Clothes;
import com.example.btl.OrderCart.ListCart;
import com.example.btl.R;

import java.util.ArrayList;
import java.util.List;

public class OrderClothesActivity extends AppCompatActivity {
    private TextView tvName, tvSize, tvColor, tvPrice;
    private ImageView imgOrder;
    private Spinner spSoluong;
    private Button btnOrder;
    private Clothes clothes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_clothes);
        tvName = findViewById(R.id.tvNameOrder);
        tvSize = findViewById(R.id.tvSizeOrder);
        tvColor = findViewById(R.id.tvColorOrder);
        tvPrice = findViewById(R.id.tvPriceOrder);
        spSoluong = findViewById(R.id.spSoluongOrder);
        btnOrder = findViewById(R.id.btnorderOrder);
        imgOrder=findViewById(R.id.imgOrder);
        if (ListCart.listClothesCart == null) {
            ListCart.listClothesCart = new ArrayList<>();
        }
        Intent intent = getIntent();
        if (intent != null) {
            clothes = (Clothes) intent.getSerializableExtra("clothes");
            if (clothes != null) {
                tvName.setText("Tên Sản Phẩm: "+clothes.getName());
                tvSize.setText("Size: "+clothes.getSize());
                tvColor.setText("Màu: "+clothes.getColor());
                tvPrice.setText("Giá: "+String.valueOf(clothes.getPrice()));
                Glide.with(this)
                        .load(Uri.parse(clothes.getImg()))
                        .into(imgOrder);
                Integer[] quantity = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                ArrayAdapter<Integer> adapterSpinner = new ArrayAdapter<>(this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        quantity);
                spSoluong.setAdapter(adapterSpinner);
            }
        }
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtocart();
            }
        });

    }
    private void addtocart(){
        if (ListCart.listClothesCart.size() > 0) {
            boolean check = false;
            int quantity = Integer.parseInt(spSoluong.getSelectedItem().toString());

            for (int i = 0; i < ListCart.listClothesCart.size(); i++) {
                if (ListCart.listClothesCart.get(i).getId() == clothes.getId()) {
                    int newQuantity = ListCart.listClothesCart.get(i).getQuantity() + quantity;
                    ListCart.listClothesCart.get(i).setQuantity(newQuantity);
                    check = true;
                }
            }

            if (!check) {
                Cart cart = new Cart();
                cart.setId(clothes.getId());
                cart.setName(clothes.getName());
                cart.setColor(clothes.getColor());
                cart.setSize(clothes.getSize());
                cart.setPrice(clothes.getPrice());
                cart.setQuantity(quantity);
                cart.setImage(clothes.getImg().toString());
                ListCart.listClothesCart.add(cart);
            }

        } else {
            int quantity = Integer.parseInt(spSoluong.getSelectedItem().toString());
            Cart cart = new Cart();
            cart.setId(clothes.getId());
            cart.setName(clothes.getName());
            cart.setColor(clothes.getColor());
            cart.setSize(clothes.getSize());
            cart.setPrice(clothes.getPrice());
            cart.setQuantity(quantity);
            cart.setImage(clothes.getImg().toString());
            ListCart.listClothesCart.add(cart);
        }

        Toast.makeText(this, "Bạn vừa thêm sản phẩm này vào giỏ hàng", Toast.LENGTH_SHORT).show();
        Intent addtocart = new Intent(OrderClothesActivity.this, CartActivity.class);
        startActivity(addtocart);
    }
}