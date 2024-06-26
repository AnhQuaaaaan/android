package com.example.congviecktra.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.congviecktra.R;
import com.example.congviecktra.adapters.RecyclerViewAdapter;
import com.example.congviecktra.adapters.RecyclerViewAdapter2;
import com.example.congviecktra.db.SQLiteHelper;
import com.example.congviecktra.models.CongViec;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {
    private EditText edTencv;
    private Button btnSearch, btnStatistic;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerViewAdapter2 recyclerViewAdapter2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edTencv = view.findViewById(R.id.edTencv);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnStatistic = view.findViewById(R.id.btnGetStatistic);
        recyclerView = view.findViewById(R.id.recyclerViewFragmentSearch);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>());
//        recyclerView.setAdapter(recyclerViewAdapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recyclerViewAdapter2 = new RecyclerViewAdapter2(new ArrayList<>());

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
                SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
                List<CongViec> congViecListByName = sqLiteHelper.findCongViecByNgayHoanThanh(edTencv.getText().toString().trim());
                recyclerViewAdapter.setListCongViec(congViecListByName);
            }
        });

        btnStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(recyclerViewAdapter2);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
                SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
                List<CongViec> statistic = sqLiteHelper.getStatistic();
                Log.v("kiemtrahihi",String.valueOf(statistic.size()));
                recyclerViewAdapter2.setListCongViec(statistic);
            }
        });

        recyclerViewAdapter.setItemClickListener(new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(CongViec congviec) {
                Intent intentOpenUpdateDeleteActivity = new Intent(getActivity(),UpdateDeleteActivity.class);
                intentOpenUpdateDeleteActivity.putExtra("congviec", congviec);
                startActivity(intentOpenUpdateDeleteActivity);
            }
        });

    }
}
