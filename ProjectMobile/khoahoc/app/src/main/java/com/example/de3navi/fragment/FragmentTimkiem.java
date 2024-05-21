package com.example.de3navi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.de3navi.R;
import com.example.de3navi.UpdateDeleteActivity;
import com.example.de3navi.adapter.RecycleViewAdapter;
import com.example.de3navi.dal.SQLiteHelper;
import com.example.de3navi.model.Book;

import java.util.ArrayList;
import java.util.List;

public class FragmentTimkiem extends Fragment implements RecycleViewAdapter.ItemListener{
    private RadioButton rddk,rdcdk;
    private Button btSearch;
    private TextView tvtong;
    private RecyclerView recyclerView;
    private TextView thongke;

    RecycleViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timkiem,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview(view);
        adapter = new RecycleViewAdapter();
        SQLiteHelper db = new SQLiteHelper(getContext());
        adapter.setList1(db.getAll());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    adapter.setList(db.getAll());
                    String res=  "";
                    int t1 = 0 ;
                    int t3 = 0 ;
                    int t2 = 0 ;
                    int t4 = 0 ;
                    int t5 = 0 ;
                    int t6 = 0 ;
                    int t7 = 0 ;
                    int t8 = 0 ;
                    int t9 = 0 ;
                    int t10 = 0 ;
                    int t11 = 0 ;
                    int t12 = 0 ;
                    for(Book i : db.getAll()){

                        if(i.getNgaybatdau().substring(3,5).equals("01")&&i.getNgaybatdau().substring(6,10).equals("2024")){
                            t1+=Integer.parseInt(i.getHocphi());
                        }
                        if(i.getNgaybatdau().substring(3,5).equals("02")&&i.getNgaybatdau().substring(6,10).equals("2024")){
                            t2+=Integer.parseInt(i.getHocphi());;
                        }
                        if(i.getNgaybatdau().substring(3,5).equals("03")&&i.getNgaybatdau().substring(6,10).equals("2024")){
                            t3+=Integer.parseInt(i.getHocphi());;
                        }
                        if(i.getNgaybatdau().substring(3,5).equals("04")&&i.getNgaybatdau().substring(6,10).equals("2024")){
                            t4+=Integer.parseInt(i.getHocphi());;
                        }
                        if(i.getNgaybatdau().substring(3,5).equals("05")&&i.getNgaybatdau().substring(6,10).equals("2024")){
                            t5+=Integer.parseInt(i.getHocphi());;
                        }
                        if(i.getNgaybatdau().substring(3,5).equals("06")&&i.getNgaybatdau().substring(6,10).equals("2024")){
                            t6+=Integer.parseInt(i.getHocphi());;
                        }
                        if(i.getNgaybatdau().substring(3,5).equals("07")&&i.getNgaybatdau().substring(6,10).equals("2024")){
                            t7+=Integer.parseInt(i.getHocphi());;
                        }
                        if(i.getNgaybatdau().substring(3,5).equals("08")&&i.getNgaybatdau().substring(6,10).equals("2024")){
                            t8+=Integer.parseInt(i.getHocphi());;
                        }
                        if(i.getNgaybatdau().substring(3,5).equals("09")&&i.getNgaybatdau().substring(6,10).equals("2024")){
                            t9+=Integer.parseInt(i.getHocphi());;
                        }
                        if(i.getNgaybatdau().substring(3,5).equals("10")&&i.getNgaybatdau().substring(6,10).equals("2024")){
                            t10+=Integer.parseInt(i.getHocphi());;
                        }
                        if(i.getNgaybatdau().substring(3,5).equals("11")&&i.getNgaybatdau().substring(6,10).equals("2024")){
                            t11+=Integer.parseInt(i.getHocphi());;
                        }
                        if(i.getNgaybatdau().substring(3,5).equals("12")&&i.getNgaybatdau().substring(6,10).equals("2024")){
                            t12+=Integer.parseInt(i.getHocphi());;
                        }
                    }
                    res = "Thang 1 co:"+Integer.toString(t1)+"\n Thang 2 co:"+Integer.toString(t2)+"\nThang 3 co: "+ Integer.toString(t3)+"\nThang 4 co:"+Integer.toString(t4)+"\n Thang 5 co:"+Integer.toString(t5)+"\nThang 6 co: "+Integer.toString(t6)+
                            "\nThang 7 co:"+Integer.toString(t7)+"\n Thang 8 co:"+Integer.toString(t8)+"\nThang 9 co: " + Integer.toString(t9)+"\nThang 10 co:"+Integer.toString(t10)+"\nThang 11 co"+Integer.toString(t11)+"\nThang 12 co: "+ Integer.toString(t12);
                    thongke.setText(res);
                    tvtong.setText("");
                }
        });
        rddk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    List<Book> tmp = new ArrayList<>();
                    int s=0;
                    for(Book i : db.getAll()){
                        if(i.getKichhoat() == 1){
                            tmp.add(i);
                            s+=Integer.parseInt(i.getHocphi());
                        }
                    }
                    adapter.setList1(tmp);
                    tvtong.setText(Integer.toString(s)+"VND");
                }
                else{
                    List<Book> tmp = new ArrayList<>();
                    int s=0;
                    for(Book i : db.getAll()){
                        if(i.getKichhoat() == 0){
                            tmp.add(i);
                            s+=Integer.parseInt(i.getHocphi());
                        }
                    }
                    adapter.setList1(tmp);
                    tvtong.setText(Integer.toString(s)+"VND");
                }
            }
        });
    }

    public void initview(View v){
        rddk = v.findViewById(R.id.rdok);
        rdcdk = v.findViewById(R.id.rdkok);
        btSearch = v.findViewById(R.id.btSearch);
        tvtong = v.findViewById(R.id.tongtien);
        recyclerView = v.findViewById(R.id.recycleView);
        thongke = v.findViewById(R.id.thongke);
    }
    @Override
    public void onItemClick(View view, int position) {
        Book item = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);
    }
}
