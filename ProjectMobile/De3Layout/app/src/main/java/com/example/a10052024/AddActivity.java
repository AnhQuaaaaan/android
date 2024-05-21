package com.example.a10052024;

import android.app.DatePickerDialog;
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

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eTen,eNgay,thegioi,vietnam;
    private CheckBox cbarn,cbproteins,cbproteinn,cbvacxin;

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
        thegioi = findViewById(R.id.edSoluongtg);
        vietnam = findViewById(R.id.edSoluongvn);
        eTen = findViewById(R.id.tvTen);
        eNgay =findViewById(R.id.edNgay);
        cbarn = findViewById(R.id.cbarn);
        cbproteins = findViewById(R.id.cbproteins);
        cbproteinn = findViewById(R.id.cbproteinn);
        cbvacxin = findViewById(R.id.cbvacxin);
        btAdd = findViewById(R.id.btAdd);
        btCancel = findViewById(R.id.btCancel);
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
                Book i = new Book(ten,cautruc,ngay,tg,vn,yt);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(i);
                finish();
            }
            else{
                Toast.makeText(this, "Nhập dữ liệu", Toast.LENGTH_SHORT).show();
            }
        }
}
}
