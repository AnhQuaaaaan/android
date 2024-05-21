package com.example.a10052024;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a10052024.dal.SQLiteHelper;
import com.example.a10052024.model.Book;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eTen,eNgay,thegioi,vietnam;
    private CheckBox cbarn,cbproteins,cbproteinn,cbvacxin;
    private Button btUpdate,btBack,btRemove;
    Book item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btBack.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        eNgay.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Book)intent.getSerializableExtra("item");
        eTen.setText(item.getTen());
        eNgay.setText(item.getNgay());
        thegioi.setText(item.getThegioi());
        vietnam.setText(item.getVietnam());
        thegioi.setText(item.getThegioi());
        vietnam.setText(item.getVietnam());
        String cautruc = item.getCautruc();
        if(cautruc.contains("ARN")){
            cbarn.setChecked(true);
        }
        if(cautruc.contains("Protein-S")){
            cbproteins.setChecked(true);
        }
        if(cautruc.contains("Protein-N")){
            cbproteinn.setChecked(true);
        }
        if(item.getVacxin() == 1){
            cbvacxin.setChecked(true);
        }
    }

    public void initView(){
        thegioi = findViewById(R.id.edSoluongtg);
        vietnam = findViewById(R.id.edSoluongvn);
        eTen = findViewById(R.id.tvTen);
        eNgay =findViewById(R.id.edNgay);
        cbarn = findViewById(R.id.cbarn);
        cbproteins = findViewById(R.id.cbproteins);
        cbproteinn = findViewById(R.id.cbproteinn);
        cbvacxin = findViewById(R.id.cbvacxin);
        btUpdate = findViewById(R.id.btUpdate);
        btRemove = findViewById(R.id.btDelete);
        btBack = findViewById(R.id.btBack);
    }

    @Override
    public void onClick(View v) {
        if(v == eNgay){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(year, month, dayOfMonth);

                    // Định dạng ngày tháng thành chuỗi
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String formattedDate = dateFormat.format(selectedDate.getTime());

                    // Gán ngày tháng vào EditText
                    eNgay.setText(formattedDate);
                }
            },year,month,day);
            dialog.show();
        }
        if( v == btBack){
            finish();
        }
        if(v == btUpdate){
            int id=item.getId();
            String ten = eTen.getText().toString();
            String ngay = eNgay.getText().toString();
            String tg=thegioi.getText().toString();
            String vn=vietnam.getText().toString();
            String cautruc = "";
            if(cbarn.isChecked()){
                cautruc = cautruc+" ARN,";
            }
            if ((cbproteins.isChecked())){
                cautruc = cautruc + " Protein-S,";
            }
            if(cbproteinn.isChecked()){
                cautruc = cautruc + " Protein-N,";
            }
            int yt ;
            if (cbvacxin.isChecked()){
                yt = 1;
            }
            else{
                yt = 0;
            }
            if(!ten.isEmpty() && !ngay.isEmpty() && !cautruc.isEmpty() &&!tg.equals("")&&!vn.equals("")){
                Book i = new Book(id,ten,cautruc,ngay,tg,vn,yt);
                SQLiteHelper db = new SQLiteHelper(this);
                db.update(i);
                finish();
            }
            else{
                Toast.makeText(this, "Nhập dữ liệu", Toast.LENGTH_SHORT).show();
            }
        }
        if(v == btRemove){
            int id = item.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thong bao xoa!");
            builder.setMessage("Ban co chac muon xoa "+item.getTen()+" khong?");
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                    db.delete(id);
                    finish();
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}