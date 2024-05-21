package com.example.b9p1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b9p1.MainActivity;
import com.example.b9p1.R;
import com.example.b9p1.adapter.CatAdapter;
import com.example.b9p1.adapter.SpinnerAdapter;
import com.example.b9p1.model.Cat;

public class FragmentAdd extends Fragment implements CatAdapter.CatItemListener{
    private CatAdapter adapter;
    private Spinner spinner;
    private EditText editName,editPrice,editInfor;
    private Button btAdd,btUpdate;
    private int pcurr;
    private RecyclerView recyclerView;
    private int []imgs={R.drawable.cat1,R.drawable.cat2,R.drawable.cat3,
            R.drawable.cat4,R.drawable.cat5,R.drawable.cat6};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter=new CatAdapter((MainActivity) getActivity());
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i=spinner.getSelectedItem().toString();
                int img;
                try {
                    img=imgs[Integer.parseInt(i)];
                    double price=Double.parseDouble(editPrice.getText().toString());
                    Cat cat=new Cat(img,editName.getText().toString(),price,editInfor.getText().toString());
                    adapter.add(cat);
                }catch (NumberFormatException e){

                }
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i=spinner.getSelectedItem().toString();
                int img;
                try {
                    img=imgs[Integer.parseInt(i)];
                    double price=Double.parseDouble(editPrice.getText().toString());
                    Cat cat=new Cat(img,editName.getText().toString(),price,editInfor.getText().toString());
                    adapter.update(pcurr,cat);
                    btUpdate.setVisibility(View.INVISIBLE);
                    btAdd.setVisibility(View.VISIBLE);
                }catch (NumberFormatException e){

                }
            }
        });
    }
    private void initView(View view){
        spinner=view.findViewById(R.id.spinner);
        SpinnerAdapter adapter=new SpinnerAdapter(getContext(),imgs);
        spinner.setAdapter(adapter);
        editName=view.findViewById(R.id.editName);
        editPrice=view.findViewById(R.id.editPrice);
        editInfor=view.findViewById(R.id.editDecribe);
        btAdd=view.findViewById(R.id.btAdd);
        btUpdate=view.findViewById(R.id. btUpdate);
        recyclerView=view.findViewById(R.id.reView);
        btUpdate.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClick(View view, int position) {
        btAdd.setVisibility(View.INVISIBLE);
        btUpdate.setVisibility(View.VISIBLE);
        pcurr=position;
        Cat cat=adapter.getItem(position);
        int img=cat.getImg();
        int p=0;
        for(int i=0;i<imgs.length;i++){
            if (img==imgs[i]){
                p=i;
                break;
            }
        }
        spinner.setSelection(p);
        editName.setText(cat.getName());
        editPrice.setText(cat.getPrice()+"");
        editInfor.setText(cat.getInfor());
    }
}
