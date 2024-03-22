package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt;
    private EditText ed1,ed2;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=findViewById(R.id.bt1);
        ed1=findViewById(R.id.ed1);
        ed2=findViewById(R.id.ed2);
        tv=findViewById(R.id.kq);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        double k=Double.parseDouble(ed2.getText().toString())/Math.pow(Double.parseDouble(ed1.getText().toString()),2);
        String kq= Double.toString(k);
        Toast.makeText(this, kq, Toast.LENGTH_SHORT).show();
        tv.setText(kq);
    }
}