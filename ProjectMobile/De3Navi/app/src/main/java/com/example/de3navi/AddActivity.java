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
    private Spinner spNoikhoihanh;
    private EditText eTen,eNgay;
    private CheckBox cbHutthuoc,cbAnsang,cbCaphe;
    private RadioButton rdKigui,rdKhongkigui;

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
        spNoikhoihanh = findViewById(R.id.spinnerNoikhoihanh);
        eTen = findViewById(R.id.tvTen);
        eNgay =findViewById(R.id.edNgay);
        cbHutthuoc = findViewById(R.id.cbHuthuoc);
        cbAnsang = findViewById(R.id.cbAnsang);
        cbCaphe = findViewById(R.id.cbCaphe);
        rdKigui = findViewById(R.id.rdKigui);
        rdKhongkigui = findViewById(R.id.rdKhongkigui);
        btAdd = findViewById(R.id.btAdd);
        btCancel = findViewById(R.id.btCancel);
        spNoikhoihanh.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.noikhoihanh)));
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
            String noikhoihanh = spNoikhoihanh.getSelectedItem().toString();
            String ngay = eNgay.getText().toString();
            String dichvu = "";
            if(cbHutthuoc.isChecked()){
                dichvu = dichvu+" Hut Thuoc,";
            }
            if ((cbCaphe.isChecked())){
                dichvu = dichvu + " Ca Phe,";
            }
            if(cbAnsang.isChecked()){
                dichvu = dichvu + " An Sang";
            }
            int yt ;
            if (rdKigui.isChecked()){
                yt = 1;
            }
            else{
                yt = 0;
            }

            if(!ten.isEmpty() && !ngay.isEmpty() && !noikhoihanh.isEmpty()){
                Book i = new Book(ten,noikhoihanh,dichvu,ngay,yt);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(i);
                finish();
            }
        }
}
}
