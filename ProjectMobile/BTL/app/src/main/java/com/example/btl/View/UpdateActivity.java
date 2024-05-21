package com.example.btl.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.btl.Adapter.RecycleViewAdapter;
import com.example.btl.Db.SQLiteHelper;
import com.example.btl.Model.Clothes;
import com.example.btl.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edName, edColor, edPrice;
    private Spinner spSize;
    private RecycleViewAdapter recycleViewAdapter;
    private Button btnUpdate, btnCancel,btnupdateimg;
    private SQLiteHelper db;
    private Uri imageUri;
    ActivityResultLauncher<Intent> resultLauncher;
    private ImageView imgupdate;
    private Clothes clothes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_updateclothes);
        initView();
        RegisterResult();
        btnUpdate.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        Intent intent=getIntent();
        clothes=(Clothes) intent.getSerializableExtra("clothes");
        edName.setText(clothes.getName());
        edColor.setText(clothes.getColor());
        Glide.with(this)
                .load(Uri.parse(clothes.getImg()))
                .into(imgupdate);
        int p=0;
        for(int i=0;i<spSize.getCount();i++){
            if(spSize.getItemAtPosition(i).toString().equalsIgnoreCase(clothes.getSize())){
                p=i;
            }
        }
        spSize.setSelection(p);
        edPrice.setText(clothes.getPrice()+"");
        btnupdateimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                resultLauncher.launch(intent);
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
                            imgupdate.setImageURI(imageUri);
                        }catch (Exception e){

                        }
                    }
                });
    }
    private void initView() {
        edName = findViewById(R.id.edNameupdate);
        edColor = findViewById(R.id.edColorupdate);
        edPrice = findViewById(R.id.edPriceupdate);
        spSize = findViewById(R.id.spinnerSizeupdate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        btnupdateimg=findViewById(R.id.btnupdateimg);
        imgupdate=findViewById(R.id.imgupdate);
        spSize.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.size)));
    }


    @Override
    public void onClick(View view) {
        db=new SQLiteHelper(this);
        if(view.getId()==R.id.btnUpdate){
            if(imageUri!=null){
                Clothes clothes1=new Clothes(clothes.getId(),imageUri.toString(),edName.getText().toString(),spSize.getSelectedItem().toString(),edColor.getText().toString(),Float.parseFloat(edPrice.getText().toString()));
                db.updateClothes(clothes1);
                Toast.makeText(getApplicationContext(),"Update Thanh Cong",Toast.LENGTH_LONG).show();
                finish();
            }
            else{
                Clothes clothes1=new Clothes(clothes.getId(),clothes.getImg(),edName.getText().toString(),spSize.getSelectedItem().toString(),edColor.getText().toString(),Float.parseFloat(edPrice.getText().toString()));
                db.updateClothes(clothes1);
                Toast.makeText(getApplicationContext(),"Update Thanh Cong",Toast.LENGTH_LONG).show();
                finish();
            }
        }
        if(view.getId()==R.id.btnCancel){
            finish();
        }
    }
}