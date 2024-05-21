package com.example.congviecktra.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.congviecktra.R;
import com.example.congviecktra.adapters.RecyclerViewAdapter;
import com.example.congviecktra.db.SQLiteHelper;
import com.example.congviecktra.models.CongViec;

import java.util.List;

public class FragmentList extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewFragmentList);

//         Lấy list book từ csdl Sqlite
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        List<CongViec> allCongViec = sqLiteHelper.getAllCongViec();

        // Hiển thị list ra
        recyclerViewAdapter = new RecyclerViewAdapter(allCongViec);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));

        recyclerViewAdapter.setItemClickListener(new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(CongViec congviec) {
                Intent intentOpenUpdateDeleteActivity = new Intent(getActivity(),UpdateDeleteActivity.class);
                intentOpenUpdateDeleteActivity.putExtra("congviec", congviec);
                startActivity(intentOpenUpdateDeleteActivity);
            }
        });
    }
//     làm tươi lại list
    @Override
    public void onResume() {
        super.onResume();
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        List<CongViec> allCongViec = sqLiteHelper.getAllCongViec();
        recyclerViewAdapter.setListCongViec(allCongViec);
    }
}
