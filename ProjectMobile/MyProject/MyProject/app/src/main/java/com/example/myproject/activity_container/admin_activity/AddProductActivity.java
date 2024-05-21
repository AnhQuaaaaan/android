package com.example.myproject.activity_container.admin_activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.activity_container.activity.MainActivity;
import com.example.myproject.dal.SQLiteHelper;
import com.example.myproject.model.Product;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    private TextInputEditText addProductName, addProductPrice, addProductImage, addProductDescription;
    private Button addProductBtn;
    private ImageView addProductUpload;
    private Spinner addProductSpinnerCate;
    private Toolbar toolbarMange;
    private SQLiteHelper db;
    private String mediaPath;
    int categoryId = 0;
    private boolean action = false; // 0 - add | 1 - edit

    private Product productEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initView();
        actionBar();
        setDataToSpinner();
        processEvent();
        getProductEdit();
    }

    private void getProductEdit() {
        Intent intent = getIntent();
        productEdit = (Product) intent.getSerializableExtra("productEdit");

        if (productEdit != null) {
            action = true;
            addProductName.setText(productEdit.getName());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            addProductPrice.setText("" + productEdit.getPrice());
            addProductImage.setText(productEdit.getImage());
            addProductDescription.setText(productEdit.getDescription());
            addProductBtn.setText("Sửa sản phẩm");
            addProductSpinnerCate.setSelection(productEdit.getCategoryId() - 1);

        }
    }

    private void processEvent() {
        addProductSpinnerCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryId = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!action) addProduct();
                else editProduct();
            }
        });

        addProductUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(AddProductActivity.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
            }
        });

    }


    // sau khi chon image -> tra ve trong ham
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mediaPath = data.getDataString();
        addProductImage.setText(mediaPath);
        Toast.makeText(getApplicationContext(), mediaPath, Toast.LENGTH_SHORT).show();
    }
    private void editProduct() {
        String name = addProductName.getText().toString().trim();
        String price = addProductPrice.getText().toString().trim();
        String des = addProductDescription.getText().toString().trim();
        String image = mediaPath == null ? productEdit.getImage() : mediaPath;

        if (TextUtils.isEmpty(name)
                || TextUtils.isEmpty(price)
                || TextUtils.isEmpty(des)
                || TextUtils.isEmpty(image)
                || categoryId == 0) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            try {
                productEdit.setName(name);
                productEdit.setPrice(price);
                productEdit.setDescription(des);
                productEdit.setImage(image);
                db.updateProduct(productEdit);
                Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addProduct() {
        String name = addProductName.getText().toString().trim();
        String price = addProductPrice.getText().toString().trim();
        String des = addProductDescription.getText().toString().trim();
        String image = mediaPath;

        if (TextUtils.isEmpty(name)
            || TextUtils.isEmpty(price)
            || TextUtils.isEmpty(des)
            || TextUtils.isEmpty(image)
            || categoryId == 0) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            try {
                db.addProduct(new Product(name, image, price, des, categoryId));
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setDataToSpinner() {
        List<String> arrays = new ArrayList<>();
        arrays.add("Chọn loại sản phẩm");
        arrays.add("Điện thoại");
        arrays.add("Laptop");
        arrays.add("Đồng hồ");
        arrays.add("Camera");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                arrays);
        addProductSpinnerCate.setAdapter(adapter);
    }


    private void actionBar() {
        setSupportActionBar(toolbarMange);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarMange.setNavigationIcon(R.drawable.ic_back);
        toolbarMange.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        toolbarMange = findViewById(R.id.toolbarMange);
        addProductSpinnerCate = findViewById(R.id.addProductSpinnerCate);
        addProductName = findViewById(R.id.addProductName);
        addProductPrice = findViewById(R.id.addProductPrice);
        addProductImage = findViewById(R.id.addProductImage);
        addProductDescription = findViewById(R.id.addProductDescription);
        addProductBtn = findViewById(R.id.addProductBtn);
        addProductUpload = findViewById(R.id.addProductUpload);

        db = new SQLiteHelper(getApplicationContext());
    }
}