package com.example.btl.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.Adapter.CartAdapter;
import com.example.btl.Adapter.RecycleViewAdapter;
import com.example.btl.Db.SQLiteHelper;
import com.example.btl.Model.Cart;
import com.example.btl.OrderCart.ListCart;
import com.example.btl.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private TextView cartTotal;
    private CartAdapter adapter;
    private RecyclerView recyclerView;
    private Button cartButtonBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        recyclerView=findViewById(R.id.cartRecycleView);
        cartButtonBuy=findViewById(R.id.cartButtonBuy);
        cartTotal=findViewById(R.id.cartTotal);
        List<Cart> list= ListCart.listClothesCart;
        double tong=0;
        for (Cart x:list){
            tong+=x.getQuantity()*x.getPrice();
        }
        cartTotal.setText(String.valueOf(tong));
        adapter=new CartAdapter(getApplicationContext(),list,this);
        adapter.setList(list);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        cartButtonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                intent.putExtra("listcart", new ArrayList<>(list));
                startActivity(intent);
            }
        });
    }
    public void updateTotal(double total) {
        cartTotal.setText(String.valueOf(total));
    }
}