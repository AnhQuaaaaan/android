package com.example.btl.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.Adapter.RecycleViewAdapter;
import com.example.btl.Db.SQLiteHelper;
import com.example.btl.Model.Clothes;
import com.example.btl.R;
import com.example.btl.View.UpdateActivity;

import java.util.List;

public class FragmentDesc extends Fragment{

    private EditText edFrom, edTo;
    private Button btnTk;
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    private RecycleViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_desc,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edFrom = view.findViewById(R.id.edFrom);
        edTo = view.findViewById(R.id.edTo);
        btnTk=view.findViewById(R.id.btnTk);
        recyclerView = view.findViewById(R.id.recyclerViewDesc);
        btnTk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter=new RecycleViewAdapter(getContext());
                db=new SQLiteHelper(getContext());
                List<Clothes> list=db.findClothesByPrice(Float.parseFloat(edFrom.getText().toString()),Float.parseFloat(edTo.getText().toString()));
                adapter.setList(list);
                LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        });
    }


}
