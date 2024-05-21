package com.example.a10052024.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a10052024.R;
import com.example.a10052024.UpdateDeleteActivity;
import com.example.a10052024.adapter.RecycleViewAdapter;
import com.example.a10052024.dal.SQLiteHelper;
import com.example.a10052024.model.Book;

import java.util.List;

public class FragmentDanhsach extends Fragment implements RecycleViewAdapter.ItemListener{
    private RecyclerView recyclerView;
    RecycleViewAdapter adapter;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danhsach,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        adapter = new RecycleViewAdapter();
        db=  new SQLiteHelper(getContext());
        List<Book> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }
    @Override
    public void onItemClick(View view, int position) {
        Book item = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        List<Book> list = db.getAll();
        adapter.setList(list);
    }
}
