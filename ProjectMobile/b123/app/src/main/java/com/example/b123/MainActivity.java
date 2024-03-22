package com.example.b123;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private CheckBox ck1,ck2,ck3;
    private RadioButton g1,g2;
    private RatingBar rt;
    private Spinner sp2,sp1;
    private TextView kq;
    private Button btht;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);
        sp2=findViewById(R.id.sp2);
        sp1=findViewById(R.id.sp1);
        String[]list={"PTIT","HUST","NEU","FTU"};
        ArrayAdapter<String> adapter2=new ArrayAdapter<>(this, R.layout.item,list);
        sp2.setAdapter(adapter2);
        String[]list1=getResources().getStringArray(R.array.country);
        ArrayAdapter<String>adapter1=new ArrayAdapter<>(this,R.layout.item,list1);
        sp1.setAdapter(adapter1);
        initView();
        btht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ss="Check box:";
                if(ck1.isChecked()){
                    ss+=ck1.getText();
                }
                if(ck2.isChecked()){
                    ss+=ck2.getText();
                }
                if(ck3.isChecked()){
                    ss+=ck3.getText();
                }
                if (ss.endsWith(",")){
                    ss=ss.substring(0,ss.length());
                }
                ss+="\nGioi tinh:";
                if(g1.isChecked()){
                    ss+=g1.getText();
                }
                if(g2.isChecked()){
                    ss+=g2.getText();
                }
                ss+="\nRating"+rt.getRating();
                ss+="\n"+sp1.getSelectedItem().toString();
                ss+="\n"+sp2.getSelectedItem().toString();
                kq.setText(ss);
            }
        });
    }
    private void initView(){
        ck1=findViewById(R.id.ck1);
        ck2=findViewById(R.id.ck2);
        ck3=findViewById(R.id.ck3);
        g1=findViewById(R.id.gnam);
        g2=findViewById(R.id.gnu);
        rt=findViewById(R.id.rating);
        btht=findViewById(R.id.btht);
        kq=findViewById(R.id.kq);
        sp1=findViewById(R.id.sp1);
        sp2=findViewById(R.id.sp2);
    }
}