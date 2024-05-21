package com.example.myproject.activity_container.login_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.activity_container.activity.MainActivity;
import com.example.myproject.dal.SQLiteHelper;
import com.example.myproject.model.User;
import com.example.myproject.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private TextView loginRegisterText;
    private TextInputEditText loginEmail, loginPassword;
    private Button loginButton;
    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        processEvent();
    }

    private void processEvent() {
        loginRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();
                boolean checkEmpty = true;
                if (TextUtils.isEmpty(email)) {
                    checkEmpty = false;
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập email", Toast.LENGTH_SHORT). show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    checkEmpty = false;
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập password", Toast.LENGTH_SHORT). show();
                    return;
                }


                if (checkEmpty) {
                    User u = db.login(email, password);
                    if (u == null) {
                        Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT). show();
                        return;
                    }

                    Paper.book().write("email", email);
                    Paper.book().write("password", password);
                    Utils.currentUser = u;
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void initView() {
        loginRegisterText = findViewById(R.id.loginRegisterText);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);

        db = new SQLiteHelper(getApplicationContext());
        Paper.init(this);

        // read date when they open this app
        if (Paper.book().read("email") != null && Paper.book().read("password") != null) {
            loginEmail.setText(Paper.book().read("email"));
            loginPassword.setText(Paper.book().read("password"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        User u = (User) getIntent().getSerializableExtra("user");
        if (u != null) {
            loginEmail.setText(u.getEmail());
            loginPassword.setText(u.getPassword());
        }
    }
}