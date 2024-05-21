package com.example.a10052024.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a10052024.AddActivity;
import com.example.a10052024.R;
import com.example.a10052024.UpdateDeleteActivity;
import com.example.a10052024.adapter.RecycleViewAdapter;
import com.example.a10052024.dal.SQLiteHelper;
import com.example.a10052024.model.Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FragmentTimkiem extends Fragment implements RecycleViewAdapter.ItemListener{
    private CheckBox tke;
    private Button btSearch,bttk;
    private TextView tkarn,tkpts,tkptn;
    private RecyclerView recyclerView;
    private EditText edtu,edden;

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
        adapter.setItemListener(this);
        List<Book> l=db.getAll();
        int arn=0;
        int pts=0;
        int ptn=0;
        for (Book m:l){
            if (m.getCautruc().contains("ARN")){
                arn+=Integer.parseInt(m.getVietnam());
            }
            if(m.getCautruc().contains("Protein-S")){
                pts+=Integer.parseInt(m.getVietnam());
            }
            if(m.getCautruc().contains("Protein-N")){
                ptn+=Integer.parseInt(m.getVietnam());
            }
        }
        tkarn.setText(Integer.toString(arn));
        tkpts.setText(Integer.toString(pts));
        tkptn.setText(Integer.toString(ptn));
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tgtu = edtu.getText().toString();
                String tgden=edden.getText().toString();
                List <Book> s=new ArrayList<>();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy", Locale.getDefault());
                try {
                    Date dateFrom = dateFormat.parse(tgtu);
                    Date dateTo = dateFormat.parse(tgden);

                    if (dateFrom != null && dateTo != null) {
                        for (Book b:db.getAll()){
                            String check=b.getNgay().substring(3,10);
                            Date dateToCheck = dateFormat.parse(check);
                            if(dateToCheck != null && !dateToCheck.before(dateFrom) && !dateToCheck.after(dateTo)){
                                s.add(b);
                            }
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                adapter.setList(s);
            }
        });
        tke.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateTextViews(compoundButton.isChecked());
            }
        });
    }
    private void updateTextViews(boolean data) {
        SQLiteHelper db = new SQLiteHelper(getContext());
        List<Book> l = db.getAll();
        int arn = 0;
        int pts = 0;
        int ptn = 0;

        for (Book m : l) {
            if (m.getCautruc().contains("ARN")) {
                arn += Integer.parseInt(data ? m.getThegioi() : m.getVietnam());
            }
            if (m.getCautruc().contains("Protein-S")) {
                pts += Integer.parseInt(data ? m.getThegioi() : m.getVietnam());
            }
            if (m.getCautruc().contains("Protein-N")) {
                ptn += Integer.parseInt(data ? m.getThegioi() : m.getVietnam());
            }
        }

        tkarn.setText(Integer.toString(arn));
        tkpts.setText(Integer.toString(pts));
        tkptn.setText(Integer.toString(ptn));
    }
    public void initview(View v){
        btSearch = v.findViewById(R.id.btSearch);
        edtu = v.findViewById(R.id.edtu);
        edden = v.findViewById(R.id.editden);
        recyclerView = v.findViewById(R.id.recycleView);
        tke = v.findViewById(R.id.tke);
        tkarn = v.findViewById(R.id.tkarn);
        tkpts = v.findViewById(R.id.tkpts);
        tkptn = v.findViewById(R.id.tkptn);

    }
    @Override
    public void onItemClick(View view, int position) {
        Book item = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);
    }

}
