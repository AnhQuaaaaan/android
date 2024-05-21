package com.example.de3navi;

import android.app.DatePickerDialog;
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

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spChuyenNganh;
    private EditText eTen,eNgay,ehocphi;
    private CheckBox cbkichhoat;

    private Button btAdd,btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btCancel.setOnClickListener(this);
        btAdd.setOnClickListener(this);
        eNgay.setOnClickListener(this);
    }
    public void initView(){
        spChuyenNganh = findViewById(R.id.spinnerChuyenNganh);
        eTen = findViewById(R.id.tvTen);
        eNgay =findViewById(R.id.edNgay);
        ehocphi=findViewById(R.id.edhocphi);
        cbkichhoat = findViewById(R.id.cbKichhoat);
        btAdd = findViewById(R.id.btAdd);
        btCancel = findViewById(R.id.btCancel);
        spChuyenNganh.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.chuyennganh)));
    }

    @Override
    public void onClick(View v) {
        if(v == eNgay){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        if(v == btCancel){
            finish();
        }
        if(v == btAdd){
            String ten = eTen.getText().toString();
            String chuyennganh = spChuyenNganh.getSelectedItem().toString();
            String ngay = eNgay.getText().toString();
            String hocphi = ehocphi.getText().toString();
            int yt ;
            if (cbkichhoat.isChecked()){
                yt = 1;
            }
            else{
                yt = 0;
            }

            if(!ten.isEmpty() && !ngay.isEmpty() && !chuyennganh.isEmpty()&&!hocphi.isEmpty()){
                Book i = new Book(ten,ngay,chuyennganh,hocphi,yt);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(i);
                finish();
            }
        }
}
}
