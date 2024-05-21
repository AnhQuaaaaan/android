package com.example.btl.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl.Dto.OrderDto;
import com.example.btl.Model.Cart;
import com.example.btl.R;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    private EditText orderName,orderPhone,orderEmail,orderAddress;
    private Button btnDatHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);
        orderName=findViewById(R.id.orderName);
        orderPhone=findViewById(R.id.orderPhone);
        orderEmail=findViewById(R.id.orderEmail);
        orderAddress=findViewById(R.id.orderAddress);
        btnDatHang=findViewById(R.id.btnDatHang);
        Intent intent = getIntent();
        ArrayList<Cart> carts= (ArrayList<Cart>) intent.getSerializableExtra("listcart");
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InvoiceActivity.class);
                OrderDto orderDto=new OrderDto();
                orderDto.setTen(orderName.getText().toString());
                orderDto.setDiachi(orderAddress.getText().toString());
                orderDto.setEmail(orderEmail.getText().toString());
                orderDto.setDienthoai(orderPhone.getText().toString());
                intent.putExtra("orderDto", orderDto);
                intent.putExtra("listcart", carts);
                startActivity(intent);
            }
        });
    }
}