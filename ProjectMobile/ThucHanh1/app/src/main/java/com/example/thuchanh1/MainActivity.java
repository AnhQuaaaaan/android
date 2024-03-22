package com.example.thuchanh1;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh1.Model.Bao;
import com.example.thuchanh1.Model.BaoAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaoAdapter.BaoItemListener,SearchView.OnQueryTextListener {
    private EditText edTen,edTime;
    private CheckBox cb1,cb2,cb3;
    private Button add,update;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private BaoAdapter baoAdapter;
    private int pcur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        baoAdapter = new BaoAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(baoAdapter);
        baoAdapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);

        edTime = findViewById(R.id.edTime);
        edTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bao ct = new Bao();
                String ten = edTen.getText().toString();
                String time = edTime.getText().toString();
                boolean cb11 = cb1.isChecked();
                boolean cb21 = cb2.isChecked();
                boolean cb31 = cb3.isChecked();

                if(!ten.isEmpty()&&!time.isEmpty() ){
                    ct.setTen(ten);
                    ct.setTime(time);
                    ct.setCb1(cb11);
                    ct.setCb2(cb21);
                    ct.setCb3(cb31);
                    baoAdapter.add(ct);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Nhập lại",Toast.LENGTH_LONG).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bao ct = new Bao();
                String ten = edTen.getText().toString();
                String time = edTime.getText().toString();
                boolean cb11 = cb1.isChecked();
                boolean cb21 = cb2.isChecked();
                boolean cb31 = cb3.isChecked();
                if(!ten.isEmpty()&&!time.isEmpty() ){
                    ct.setTen(ten);
                    ct.setTime(time);
                    ct.setCb1(cb11);
                    ct.setCb2(cb21);
                    ct.setCb3(cb31);
                    baoAdapter.update(pcur,ct);
                    add.setEnabled(true);
                    update.setEnabled(false);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Nhập lại",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void showTimePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        edTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);

        timePickerDialog.show();
    }


    @SuppressLint("WrongViewCast")
    public void initView(){
        edTen = findViewById(R.id.edTen);
        edTime = findViewById(R.id.edTime);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        add = findViewById(R.id.btAdd);
        update = findViewById(R.id.btUpdate);
        recyclerView = findViewById(R.id.recycleView);
        searchView = findViewById(R.id.btSearch);
        update.setEnabled(false);
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
        List<Bao> filterList = new ArrayList<>();
        for(Bao i : baoAdapter.getBackup()){
            if(i.getTen().toLowerCase().contains(s.toLowerCase())){
                filterList.add(i);
            }
        }
        if(filterList.isEmpty()){
            Toast.makeText(this,"No data ",Toast.LENGTH_LONG).show();
        }
        else{
            baoAdapter.filterList(filterList);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        add.setEnabled(false);
        update.setEnabled(true);
        pcur = position;
        Bao ct = baoAdapter.getItem(position);

        if(ct.isCb1()){
            cb1.setChecked(true);
        }
        else {
            cb1.setChecked(false);
        }
        if(ct.isCb2()){
            cb2.setChecked(true);
        }
        else {
            cb2.setChecked(false);
        }
        if(ct.isCb3()){
            cb3.setChecked(true);
        }
        else {
            cb3.setChecked(false);
        }
        edTen.setText(ct.getTen());
        edTime.setText(ct.getTime());
    }
}