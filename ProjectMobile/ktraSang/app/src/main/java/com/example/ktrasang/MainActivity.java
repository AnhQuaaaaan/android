package com.example.ktrasang;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktrasang.model.CauThu;
import com.example.ktrasang.model.CauThuAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener, CauThuAdapter.CauThuItemListener, SearchView.OnQueryTextListener {
    private EditText edTen,edNgay;
    private CheckBox cb1,cb2,cb3;
    private RadioButton rg1,rg2;
    private Button date,add,update;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private CauThuAdapter cauThuAdapter;
    private int pcur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        cauThuAdapter = new CauThuAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(cauThuAdapter);
        cauThuAdapter.setClickListener(this);
        date.setOnClickListener(this);
        searchView.setOnQueryTextListener(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CauThu cauThu = new CauThu();
                String ten = edTen.getText().toString();
                String ngay = edNgay.getText().toString();
                boolean rg11 = rg1.isChecked();
                boolean rg21 = rg2.isChecked();
                boolean cb11 = cb1.isChecked();
                boolean cb21 = cb2.isChecked();
                boolean cb31 = cb3.isChecked();
                if(!ten.isEmpty()  && !ngay.isEmpty() ){
                    cauThu.setTen(ten);
                    if(rg11 == true){
                        cauThu.setGioitinh(true);
                    }
                    else {
                        cauThu.setGioitinh(false);
                    }
                    cauThu.setNgaysinh(ngay);
                    cauThu.setHauve(cb11);
                    cauThu.setTienve(cb21);
                    cauThu.setTiendao(cb31);
                    cauThuAdapter.add(cauThu);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Nhap data",Toast.LENGTH_LONG).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CauThu cauThu = new CauThu();
                String ten = edTen.getText().toString();
                String ngay = edNgay.getText().toString();
                boolean rg11 = rg1.isChecked();
                boolean rg21 = rg2.isChecked();
                boolean cb11 = cb1.isChecked();
                boolean cb21 = cb2.isChecked();
                boolean cb31 = cb3.isChecked();
                if(!ten.isEmpty()  && !ngay.isEmpty() ){
                    cauThu.setTen(ten);
                    if(rg11 == true){
                        cauThu.setGioitinh(true);
                    }
                    else {
                        cauThu.setGioitinh(false);
                    }
                    cauThu.setNgaysinh(ngay);
                    cauThu.setHauve(cb11);
                    cauThu.setTienve(cb21);
                    cauThu.setTiendao(cb31);
                    cauThuAdapter.update(pcur,cauThu);
                    add.setEnabled(true);
                    update.setEnabled(false);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Nhap data",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initView() {
        edTen = findViewById(R.id.edTen);
        edNgay = findViewById(R.id.edNgay);
        rg1 = findViewById(R.id.rg1);
        rg2 = findViewById(R.id.rg2);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        date = findViewById(R.id.date);
        add = findViewById(R.id.btadd);
        update = findViewById(R.id.btupdate);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        update.setEnabled(false);
    }
    @Override
    public void onClick(View v) {
        if(v==date){
            Calendar c = Calendar.getInstance();
            int dd = c.get(Calendar.DAY_OF_MONTH);
            int mm = c.get(Calendar.MONTH);
            int yy = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    edNgay.setText(i+"/"+i1+1+"/"+i2);
                }},yy,mm,dd);
            datePickerDialog.show();
        }

    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filter(newText);
        return false;
    }
    public void filter(String s){
        List<CauThu> filterList = new ArrayList<>();
        for(CauThu i : cauThuAdapter.getBackup()){
            if(i.getTen().toLowerCase().contains(s.toLowerCase())){
                filterList.add(i);
            }
        }
        if(filterList.isEmpty()){
            Toast.makeText(this,"No data ",Toast.LENGTH_LONG).show();
        }
        else{
            cauThuAdapter.filterList(filterList);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        add.setEnabled(false);
        update.setEnabled(true);
        pcur = position;
        CauThu cauThu = cauThuAdapter.getItem(position);
        if(cauThu.isHauve()){
            cb1.setChecked(true);
        }
        else {
            cb1.setChecked(false);
        }
        if(cauThu.isTienve()){
            cb2.setChecked(true);
        }
        else {
            cb2.setChecked(false);
        }
        if(cauThu.isTiendao()){
            cb3.setChecked(true);
        }
        else {
            cb3.setChecked(false);
        }
        edTen.setText(cauThu.getTen());
        if(cauThu.isGioitinh()){
            rg1.setChecked(true);
        }
        else{
            rg2.setChecked(true);
        }
        edNgay.setText(cauThu.getNgaysinh());
    }
}