package com.example.myproject.activity_container.admin_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myproject.EventBus.TotalMoneyEvent;
import com.example.myproject.EventBus.UpdateDeleteEvent;
import com.example.myproject.R;
import com.example.myproject.activity_container.activity.SearchActivity;
import com.example.myproject.dal.SQLiteHelper;
import com.example.myproject.model.Product;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ManageActivity extends AppCompatActivity {
    private CardView manageAdd, manageEdit, manageDelete;
    private Toolbar toolbarManage;

    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        initView();
        actionBar();
        processEvent();
    }

    private void processEvent() {
        manageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddProductActivity.class);
                startActivity(intent);
            }
        });

        manageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        manageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }
    private void actionBar() {
        setSupportActionBar(toolbarManage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarManage.setNavigationIcon(R.drawable.ic_back);
        toolbarManage.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        manageAdd = findViewById(R.id.manageAdd);
        manageEdit = findViewById(R.id.manageEdit);
        manageDelete = findViewById(R.id.manageDelete);
        toolbarManage = findViewById(R.id.toolbarManage);

        db = new SQLiteHelper(getApplicationContext());
    }


}