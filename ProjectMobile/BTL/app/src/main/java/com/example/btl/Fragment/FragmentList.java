package com.example.btl.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.Adapter.RecycleViewAdapter;
import com.example.btl.Db.SQLiteHelper;
import com.example.btl.Model.Cart;
import com.example.btl.Model.Clothes;
import com.example.btl.R;
import com.example.btl.View.UpdateActivity;

import java.util.List;

public class FragmentList extends Fragment implements RecycleViewAdapter.ClothesListener {
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerViewFragmentList);
        adapter=new RecycleViewAdapter(getContext());
        db=new SQLiteHelper(getContext());
        List<Clothes> list=db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClothesListener(this);

    }

    @Override
    public void onClothesClick(View view, int position) {
        Clothes clothes=adapter.getClothes(position);
        Intent intent=new Intent(getActivity(), UpdateActivity.class);
        intent.putExtra("clothes",clothes);
        startActivity(intent);
    }


    @Override
    public void onClothesDelete(Clothes clothes) {
        db.deleteClothes(clothes.getId());
        updateClothesList();
    }
    private void updateClothesList() {
        List<Clothes> list = db.getAll();
        adapter.setList(list);
    }
    @Override
    public void onResume() {
        super.onResume();
        List<Clothes>list=db.getAll();
        adapter.setList(list);
    }
}
