package com.example.btl.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl.Db.SQLiteHelper;
import com.example.btl.Model.Clothes;
import com.example.btl.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AddActivity extends AppCompatActivity {
    private EditText edName, edColor, edPrice;
    private Spinner spSize;
    private Button btnAdd, btnCancel,btnAddImg;
    private ImageView imgadd;
    private SQLiteHelper db;
    private Uri imageUri;
    ActivityResultLauncher<Intent>resultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addclothes);
        initView();
        RegisterResult();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                resultLauncher.launch(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clothes clothes=new Clothes(imageUri.toString(),edName.getText().toString(),spSize.getSelectedItem().toString(),edColor.getText().toString(),Float.parseFloat(edPrice.getText().toString()));
                db=new SQLiteHelper(AddActivity.this);
                db.addClothes(clothes);
                finish();
            }
        });
    }
    private void RegisterResult(){
        resultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        try {
                            imageUri=o.getData().getData();
                            imgadd.setImageURI(imageUri);
                        }catch (Exception e){

                        }
                    }
                });
    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edColor = findViewById(R.id.edColor);
        edPrice = findViewById(R.id.edPrice);
        spSize = findViewById(R.id.spinnerSize);
        btnAddImg=findViewById(R.id.btnaddimg);
        imgadd=findViewById(R.id.imgadd);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        spSize.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.size)));
    }
}