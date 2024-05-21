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

import com.example.btl.Db.SQLiteHelper;
import com.example.btl.Model.User;
import com.example.btl.R;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private EditText username,password;
    private Button btLogin,btRegister;
    private SQLiteHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        initView();
        btLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);
    }
    public void initView(){
        username=findViewById(R.id.edt1);
        password=findViewById(R.id.edt2);
        btLogin=findViewById(R.id.btLogin);
        btRegister=findViewById(R.id.btRegister);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btLogin:
                finish();
                break;

            case R.id.btRegister:
                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                db=new SQLiteHelper(this);
                User user= db.findUsersByUsernameAndPassword(username.getText().toString(),password.getText().toString());
                if (user!=null){
                    Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED,null);
                    break;
                }
                else{
                    db.addUser(username.getText().toString(),password.getText().toString());
                    User user1= db.findUsersByUsernameAndPassword(username.getText().toString(),password.getText().toString());
                    Intent intent =new Intent();
                    intent.putExtra("user",user1);
                    setResult(RESULT_OK,intent);
                    finish();
                    break;
                }
        }
    }
}