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
    private CheckBox kigui;
    private Button btSearch;
private TextView tvtong;
    private RecyclerView recyclerView;
private TextView thongke;
    private EditText spThang;

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
        adapter.setList(db.getAll());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        tvtong.setText(db.getAll().size()+"ve");
        adapter.setItemListener(this);
        spThang.setText("All");

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thoigian = spThang.getText().toString();
                if(thoigian.equals("All")){
                    adapter.setList(db.getAll());
                }
                else {
                    List<Book> tmp = new ArrayList<>();
                    for(Book i : db.getAll()){
                        String nami = i.getNgay().substring(6,10);
                        if(thoigian.equals(nami)){{
                                tmp.add(i);
                            }
                        }
                    }
                    adapter.setList(tmp);
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
                    for(Book i : tmp){

                        if(i.getNgay().substring(3,5).equals("01")){
                            t1++;
                        }
                        if(i.getNgay().substring(3,5).equals("02")){
                            t2++;
                        }
                        if(i.getNgay().substring(3,5).equals("03")){
                            t3++;
                        }
                        if(i.getNgay().substring(3,5).equals("04")){
                            t4++;
                        }
                        if(i.getNgay().substring(3,5).equals("05")){
                            t5++;
                        }
                        if(i.getNgay().substring(3,5).equals("06")){
                            t6++;
                        }
                        if(i.getNgay().substring(3,5).equals("07")){
                            t7++;
                        }
                        if(i.getNgay().substring(3,5).equals("08")){
                            t8++;
                        }
                        if(i.getNgay().substring(3,5).equals("09")){
                            t9++;
                        }
                        if(i.getNgay().substring(3,5).equals("10")){
                            t10++;
                        }
                        if(i.getNgay().substring(3,5).equals("11")){
                            t11++;
                        }
                        if(i.getNgay().substring(3,5).equals("12")){
                            t12++;
                        }
                    }
                    res = "Thang 1 co:"+t1+"\n Thang 2 co:"+t2+"\nThang 3 co: "+ t3+"\nThang 4 co:"+t4+"\n Thang 5 co:"+t5+"\nThang 6 co: "+t6+
                            "\nThang 7 co:"+t7+"\n Thang 8 co:"+t8+"\nThang 9 co: " + t9+"\nThang 10 co:"+t10+"\nThang 11 co"+t11+"\nThang 12 co: "+ t12;
                    thongke.setText(res);
                    tvtong.setText(tmp.size()+"ve");
                }
            }
        });
        kigui.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    List<Book> tmp = new ArrayList<>();
                    for(Book i : db.getAll()){
                        if(i.getKigui() == 1){
                            tmp.add(i);
                        }
                    }
                    adapter.setList(tmp);
                    tvtong.setText(tmp.size()+"ve");
                }
                else{
                    List<Book> tmp = new ArrayList<>();
                    for(Book i : db.getAll()){
                        if(i.getKigui() == 0){
                            tmp.add(i);
                        }
                    }
                    adapter.setList(tmp);
                    tvtong.setText(tmp.size()+"ve");
                }
            }
        });
    }

    public void initview(View v){
        kigui = v.findViewById(R.id.cbKigui);
        btSearch = v.findViewById(R.id.btSearch);
        tvtong = v.findViewById(R.id.tongve);
        spThang = v.findViewById(R.id.spinnerThang);
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
