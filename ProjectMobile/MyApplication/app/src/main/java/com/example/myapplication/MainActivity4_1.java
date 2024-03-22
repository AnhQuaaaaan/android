package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity4_1 extends AppCompatActivity {
    private EditText tn1,tn2;
    private TextView txtKQ;
    private Button btTong;
    private Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai41);
        initGUI();
        String[]list={"+","-","*","/"};
        ArrayAdapter<String> adapter2=new ArrayAdapter<>(this, R.layout.item,list);
        sp.setAdapter(adapter2);
        btTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sn1=tn1.getText().toString();
                String sn2=tn2.getText().toString();
                double n1,n2;
                try{
                    n1=Double.parseDouble(sn1);
                    n2=Double.parseDouble(sn2);
                    String kk=tinhtoan(n1,n2,"+");
                    txtKQ.setText(kk);
                }catch (NumberFormatException e){
                    System.out.println(e);
                    Toast.makeText(getApplicationContext(),"De nghi nhap so",Toast.LENGTH_LONG).show();
                }
            }
        });
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sn1=tn1.getText().toString();
                String sn2=tn2.getText().toString();
                double n1,n2;
                try{
                    n1=Double.parseDouble(sn1);
                    n2=Double.parseDouble(sn2);
                    String p=sp.getSelectedItem().toString();
                    String kk=tinhtoan(n1,n2,p);
                    txtKQ.setText(kk);
                }catch (NumberFormatException e){
                    System.out.println(e);
                    Toast.makeText(getApplicationContext(),"De nghi nhap so",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initGUI() {
        tn1=findViewById(R.id.txt1);
        tn2=findViewById(R.id.txt2);
        txtKQ=findViewById(R.id.txtKQ);
        btTong=findViewById(R.id.btTong);
        sp=findViewById(R.id.sp);

    }
//    public void tong(View v){
//        String sn1=tn1.getText().toString();
//        String sn2=tn2.getText().toString();
//        double n1,n2;
//        try{
//            n1=Double.parseDouble(sn1);
//            n2=Double.parseDouble(sn2);
//            txtKQ.setText("Tong:"+(n1+n2));
//        }catch (NumberFormatException e){
//            System.out.println(e);
//            Toast.makeText(getApplicationContext(),"De nghi nhap so",Toast.LENGTH_LONG).show();
//        }
private String tinhtoan(double x, double y, String p){
    String s = "";
    switch (p){
        case "+":s="Tong"+(x+y);
            break;
        case "-":s="Hieu"+(x-y);
            break;
        case "*":s="Tich"+(x*y);
            break;
        case "/":
            if(y==0)
                s="Khong chia cho 0";
            else
                s="Thuong"+(x/y);
            break;
    }
    return s;
}
    }
