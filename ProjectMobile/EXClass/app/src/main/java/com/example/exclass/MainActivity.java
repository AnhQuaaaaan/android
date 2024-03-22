package com.example.exclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.exclass.Model.SpinnerAdapter;
import com.example.exclass.Model.Tour;
import com.example.exclass.Model.TourAdapter;

public class MainActivity extends AppCompatActivity implements TourAdapter.TourItemListen {
    private Spinner sp;
    private RecyclerView recyclerView;
    private TourAdapter tourAdapter;
    private EditText eLT,eTime;
    private Button btAdd,btUpdate;
    private int pcurr;
    private int[]imgs={R.drawable.car,R.drawable.xemay,R.drawable.maybay};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        tourAdapter=new TourAdapter(this);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(tourAdapter);
        tourAdapter.setClickListener(this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour tour=new Tour  ();
                String i=sp.getSelectedItem().toString();
                String lt=eLT.getText().toString();
                String time=eTime.getText().toString();
                int img=R.drawable.maybay;
                try {
                    img=imgs[Integer.parseInt(i)];
                    tour.setImg(img);
                    tour.setLichtrinh(lt);
                    tour.setTime(time);
                    tourAdapter.add(tour);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour tour=new Tour();
                String i=sp.getSelectedItem().toString();
                String lt=eLT.getText().toString();
                String time=eTime.getText().toString();
                int img=R.drawable.maybay;
                try {
                    img=imgs[Integer.parseInt(i)];
                    tour.setImg(img);
                    tour.setTime(time);
                    tour.setLichtrinh(lt);
                    tourAdapter.update(pcurr,tour);
                    btAdd.setEnabled(true);
                    btUpdate.setEnabled(false);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        sp=findViewById(R.id.img);
        SpinnerAdapter adapter=new SpinnerAdapter(this);
        sp.setAdapter(adapter);
        recyclerView=findViewById(R.id.recycleView);
        eLT=findViewById(R.id.lichtrinh);
        eTime=findViewById(R.id.thoigian);
        btAdd=findViewById(R.id.btAdd);
        btUpdate=findViewById(R.id.btUpdate);
        btUpdate.setEnabled(false);
    }

    @Override
    public void onItemClick(View view, int position) {
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
        pcurr=position;
        Tour tour=tourAdapter.getItem(position);
        int img=tour.getImg();
        int p=0;
        for (int i=0;i<imgs.length;i++){
            if (img==imgs[i]){
                p=i;
                break;
            }
        }
        sp.setSelection(p);
        eLT.setText(tour.getLichtrinh());
        eTime.setText(tour.getTime());
    }
}