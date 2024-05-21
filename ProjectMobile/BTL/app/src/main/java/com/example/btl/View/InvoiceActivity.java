package com.example.btl.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.Adapter.CartAdapter;
import com.example.btl.Adapter.InvoiceAdapter;
import com.example.btl.Db.SQLiteHelper;
import com.example.btl.Dto.OrderDto;
import com.example.btl.Model.Cart;
import com.example.btl.OrderCart.ListCart;
import com.example.btl.R;

import java.util.ArrayList;
import java.util.List;

public class InvoiceActivity extends AppCompatActivity {
    private TextView textViewName,textViewEmail,textViewPhone,textViewAddress,textViewTotalPrice;
    private RecyclerView recyclerViewItems;
    private Button btoke;
    private InvoiceAdapter adapter;
    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_invoice);
        textViewName=findViewById(R.id.textViewName);
        textViewEmail=findViewById(R.id.textViewEmail);
        textViewPhone=findViewById(R.id.textViewPhone);
        btoke=findViewById(R.id.btoke);
        textViewAddress=findViewById(R.id.textViewAddress);
        textViewTotalPrice=findViewById(R.id.textViewTotalPrice);
        recyclerViewItems=findViewById(R.id.recyclerViewItems);
        Intent intent = getIntent();
        ArrayList<Cart> carts= (ArrayList<Cart>) intent.getSerializableExtra("listcart");
        OrderDto orderDto= (OrderDto) intent.getSerializableExtra("orderDto");
        textViewName.setText("Người đặt hàng: "+orderDto.getTen());
        textViewEmail.setText("Email: "+orderDto.getEmail());
        textViewPhone.setText("Số điện thoại: "+orderDto.getDienthoai());
        textViewAddress.setText("Địa chỉ: "+orderDto.getDiachi());
        double tong=0;
        for (Cart x:carts){
            tong+=x.getQuantity()*x.getPrice();
        }
        textViewTotalPrice.setText("Tổng tiền: "+String.valueOf(tong) +"VNĐ");
        adapter=new InvoiceAdapter(this);
        adapter.setList(carts);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerViewItems.setLayoutManager(manager);
        recyclerViewItems.setAdapter(adapter);
        orderDto.setTongtien(String.valueOf(tong));
        btoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Cart x:carts){
                    orderDto.setId(x.getId());
                    orderDto.setQuantity(x.getQuantity());
                    db = new SQLiteHelper(InvoiceActivity.this);
                    db.addOrder(orderDto);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    Toast.makeText(InvoiceActivity.this, "OKKK", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
    }
}