package com.example.btl.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.Adapter.RecycleViewAdapter;
import com.example.btl.Db.SQLiteHelper;
import com.example.btl.Model.Clothes;
import com.example.btl.R;
import com.example.btl.View.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    private RecycleViewAdapter adapter;
    private SearchView search;
    private List<Clothes> mList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search=view.findViewById(R.id.search);
        recyclerView=view.findViewById(R.id.reViewSearch);
        adapter=new RecycleViewAdapter(getContext());
        db=new SQLiteHelper(getContext());
        mList=db.getAll();
        adapter.setList(mList);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return false;
            }
            private void filter(String s) {
                List<Clothes> filterlist=new ArrayList<>();
                for (Clothes i:mList){
                    if(i.getName().toLowerCase().contains(s.toLowerCase())){
                        filterlist.add(i);
                    }
                }
                if (filterlist.isEmpty()){
                    Toast.makeText(getContext(),"Khong co du lieu khop",Toast.LENGTH_SHORT).show();
                }else{
                    adapter.setList(filterlist);
                }
            }
        });
    }
}
