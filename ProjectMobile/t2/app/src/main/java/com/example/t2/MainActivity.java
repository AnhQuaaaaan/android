package com.example.t2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.appcompat.widget.SearchView;

import android.widget.Spinner;
import android.widget.Toast;

import com.example.t2.model.CauThuAdapter;
import com.example.t2.model.CauThu;
import com.example.t2.model.CauThuAdapter;
import com.example.t2.model.SpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,CauThuAdapter.CauThuItemListener,SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private CauThuAdapter adapter;
    private EditText edTen,edNgay;
    private CheckBox cb1,cb2,cb3;
    private RadioButton rg1,rg2;
    private Button add,update;
    private SearchView searchView;
    private int pcur;
    private  Spinner sp;
    private int[]imgs={R.drawable.car,R.drawable.xemay,R.drawable.maybay};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        adapter=new CauThuAdapter(this);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        edNgay.setOnClickListener(this);
        adapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CauThu cauThu = new CauThu();
                String i=sp.getSelectedItem().toString();
                int img=R.drawable.car;
                String ten=edTen.getText().toString();
                String ngay=edNgay.getText().toString();
                String gioitinh="";
                boolean rg11 = rg1.isChecked();
                boolean rg21 = rg2.isChecked();
                boolean cb11 = cb1.isChecked();
                boolean cb21 = cb2.isChecked();
                boolean cb31 = cb3.isChecked();
                if(!ten.isEmpty() && !ngay.isEmpty()){
                    if(rg11){
                        gioitinh="Nam";
                    }
                    if(rg21){
                        gioitinh="Nu";
                    }
                    img=imgs[Integer.parseInt(i)];
                    cauThu.setImg(img);
                    cauThu.setTen(ten);
                    cauThu.setGioitinh(gioitinh);
                    cauThu.setNgaysinh(ngay);
                    cauThu.setHauve(cb11);
                    cauThu.setTienve(cb21);
                    cauThu.setTiendao(cb31);
                    adapter.add(cauThu);
                    edTen.setText("");
                    edNgay.setText("");
                    rg1.setChecked(false);
                    rg2.setChecked(false);
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Nhap data",Toast.LENGTH_LONG).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CauThu cauThu = new CauThu();
                String ten=edTen.getText().toString();
                String ngay=edNgay.getText().toString();
                String gioitinh="";
                String i=sp.getSelectedItem().toString();
                int img=R.drawable.maybay;
                boolean rg11 = rg1.isChecked();
                boolean rg21 = rg2.isChecked();
                boolean cb11 = cb1.isChecked();
                boolean cb21 = cb2.isChecked();
                boolean cb31 = cb3.isChecked();
                if(!ten.isEmpty() && !ngay.isEmpty()){
                    if(rg11){
                        gioitinh="Nam";
                    }
                    if(rg21){
                        gioitinh="Nu";
                    }
                    img=imgs[Integer.parseInt(i)];
                    cauThu.setImg(img);
                    cauThu.setTen(ten);
                    cauThu.setGioitinh(gioitinh);
                    cauThu.setNgaysinh(ngay);
                    cauThu.setHauve(cb11);
                    cauThu.setTienve(cb21);
                    cauThu.setTiendao(cb31);
                    adapter.update(pcur,cauThu);
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
        sp=findViewById(R.id.img);
        SpinnerAdapter spinnerAdapter=new SpinnerAdapter(this);
        sp.setAdapter(spinnerAdapter);
        edTen = findViewById(R.id.edTen);
        edNgay = findViewById(R.id.edNgay);
        rg1 = findViewById(R.id.rg1);
        rg2 = findViewById(R.id.rg2);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        add = findViewById(R.id.btadd);
        update = findViewById(R.id.btupdate);
        recyclerView = findViewById(R.id.recyclerView);
        update.setEnabled(false);
        searchView=findViewById(R.id.search);
    }

    @Override
    public void onClick(View view) {
        if(view==edNgay){
            Calendar c= Calendar.getInstance();
            int y=c.get(Calendar.YEAR);
            int m=c.get(Calendar.MONTH);
            int d=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                    edNgay.setText(yy+"/"+(mm+1)+"/"+dd);

                }
            },y,m,d);
            dialog.show();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        add.setEnabled(false);
        update.setEnabled(true);
        pcur=position;
        CauThu cauThu=adapter.getItem(position);
        edTen.setText(cauThu.getTen());
        int img=cauThu.getImg();
        int p=0;
        for (int i=0;i<imgs.length;i++){
            if (img==imgs[i]){
                p=i;
                break;
            }
        }
        sp.setSelection(p);
        edNgay.setText(cauThu.getNgaysinh());
        String gioitinh=cauThu.getGioitinh();
        if (gioitinh.contains("Nam")){
            rg1.setChecked(true);
        }
        else{
            rg2.setChecked(true);
        }
        if(cauThu.isHauve()){
            cb1.setChecked(true);
        }
        else{
            cb1.setChecked(false);
        }
        if(cauThu.isTienve()){
            cb2.setChecked(true);
        }
        else{
            cb2.setChecked(false);
        }
        if(cauThu.isTiendao()){
            cb3.setChecked(true);
        }
        else{
            cb3.setChecked(false);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filter(s);
        return false;
    }
    private void filter(String s){
        List<CauThu> filterlist=new ArrayList<>();
        for (CauThu c:adapter.getBackup()){
            if(c.getTen().toLowerCase().contains(s.toLowerCase())){
                filterlist.add(c);
            }
        }
        if(filterlist.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            adapter.filterList(filterlist);
        }
    }
}