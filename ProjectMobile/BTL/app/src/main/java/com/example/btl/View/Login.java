package com.example.btl.View;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl.Db.SQLiteHelper;
import com.example.btl.Model.User;
import com.example.btl.R;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText username,password;
    private Button btlogin,btregister;

    private final static int REQUEST_CODE=2509;
    private SQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        initView();
        db = new SQLiteHelper(this);
        btlogin.setOnClickListener(this);
        btregister.setOnClickListener(this);
    }
    public void initView(){
        username=findViewById(R.id.edt1);
        password=findViewById(R.id.edt2);
        btlogin=findViewById(R.id.btlogin);
        btregister=findViewById(R.id.btregister);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btlogin:
                Intent loginIntent= new Intent(Login.this, MainActivity.class);
                User user = db.findUsersByUsernameAndPassword(username.getText().toString(), password.getText().toString());
                if(user==null){
                    Toast.makeText(this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
                else{
                    loginIntent.putExtra("user",user);
                    startActivity(loginIntent);
                }
                break;

            case R.id.btregister:
                Intent reIntent=new Intent(Login.this, Register.class);
                startActivityForResult(reIntent,REQUEST_CODE);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE&&resultCode==RESULT_OK){
            User user=(User) data.getSerializableExtra("user");
            username.setText(user.getUsername());
            password.setText(user.getPassword());
        }
    }
}