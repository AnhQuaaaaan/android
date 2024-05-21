package com.example.de3navi;

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
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.de3navi.dal.SQLiteHelper;
import com.example.de3navi.model.Book;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spChuyenNganh;
    private EditText eTen,eNgay,eHocphi;
    private CheckBox cbkichhoat;
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
        int p = 0;
        for(int i = 0 ; i<spChuyenNganh.getCount();i++){
            if(spChuyenNganh.getItemAtPosition(i).toString().equals(item.getChuyennganh())){
                p = i;
                break;
            }
        }
        eNgay.setText(item.getNgaybatdau());
        if(item.getKichhoat() == 1){
            cbkichhoat.setChecked(true);
        }
        else{
            cbkichhoat.setChecked(false);
        }
        spChuyenNganh.setSelection(p);
        eHocphi.setText(item.getHocphi());
    }

    public void initView(){
        spChuyenNganh = findViewById(R.id.spinnerChuyenNganhud);
        eTen = findViewById(R.id.tvTenud);
        cbkichhoat = findViewById(R.id.cbKichhoatud);
        eNgay = findViewById(R.id.edNgayud);
        eHocphi = findViewById(R.id.edhocphiud);
        spChuyenNganh.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.chuyennganh)));
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
            int id = item.getId();
            String ten = eTen.getText().toString();
            String chuyennganh = spChuyenNganh.getSelectedItem().toString();
            String ngay = eNgay.getText().toString();
            String hocphi = eHocphi.getText().toString();
            int yt ;
            if (cbkichhoat.isChecked()){
                yt = 1;
            }
            else{
                yt = 0;
            }
            if(!ten.isEmpty() && !ngay.isEmpty() && !chuyennganh.isEmpty()&&!hocphi.isEmpty()){
                Book i = new Book(id,ten,ngay,chuyennganh,hocphi,yt);
                SQLiteHelper db = new SQLiteHelper(this);
                db.update(i);
                finish();
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