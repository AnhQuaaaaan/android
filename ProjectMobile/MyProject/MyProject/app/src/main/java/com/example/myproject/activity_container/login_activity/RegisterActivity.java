package com.example.myproject.activity_container.login_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.dal.SQLiteHelper;
import com.example.myproject.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText registerEmail, registerPassword, registerConfirmPass, registerPhoneNumber;
    private Button registerButton;
    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        processEvent();
    }

    private void processEvent() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String email = registerEmail.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();
        String confirmPass = registerConfirmPass.getText().toString().trim();
        String phoneNumber = registerPhoneNumber.getText().toString().trim();

        boolean checkEmpty = true, checkConfirm = true, checkPhoneNumber = true, checkEmail = true;

        if (TextUtils.isEmpty(email)) {
            checkEmpty = false;
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập email", Toast.LENGTH_SHORT). show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            checkEmpty = false;
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập password", Toast.LENGTH_SHORT). show();
            return;
        } else if (TextUtils.isEmpty(confirmPass)) {
            checkEmpty = false;
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập confirm password", Toast.LENGTH_SHORT). show();
            return;
        } else if (TextUtils.isEmpty(phoneNumber)) {
            checkEmpty = false;
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập phoneNumber", Toast.LENGTH_SHORT). show();
            return;
        }

        if (!password.equals(confirmPass)) {
            checkConfirm = false;
            Toast.makeText(getApplicationContext(), "Mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT). show();
            return;
        }

        String phoneNumberRegex = "^[0-9]{10}$";
        if (!phoneNumber.matches(phoneNumberRegex)) {
            checkPhoneNumber = false;
            Toast.makeText(getApplicationContext(), "Số điện thoại phải có 10 chữ số", Toast.LENGTH_SHORT). show();
            return;
        }

        if (db.getUserByEmail(email) != null) {
            checkEmail = false;
            Toast.makeText(getApplicationContext(), "Email đã tồn tại", Toast.LENGTH_SHORT). show();
            return;
        }

        if (checkEmpty && checkConfirm && checkPhoneNumber && checkEmail) {
            // role = 0
             User u = new User(email, password, phoneNumber, 0);
            db.addUser(u);
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.putExtra("user", u);
            startActivity(intent);
            finish();
        }
    }

    private void initView() {
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        registerConfirmPass = findViewById(R.id.registerConfirmPass);
        registerPhoneNumber = findViewById(R.id.registerPhoneNumber);
        registerButton = findViewById(R.id.registerButton);

        db = new SQLiteHelper(getApplicationContext());
    }
}