package com.example.b9p1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b9p1.R;
import com.example.b9p1.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<Cat> mSearch;

    public SearchAdapter() {
        mSearch=new ArrayList<>();
    }

    public void setListCat(List<Cat> mSearch) {
        this.mSearch = mSearch;
        notifyDataSetChanged();
    }

    public List<Cat> getList() {
        return mSearch;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Cat cat=mSearch.get(position);
        holder.img.setImageResource(cat.getImg());
        holder.name.setText(cat.getName());
        holder.price.setText(cat.getPrice()+"");
        holder.infor.setText(cat.getInfor());
    }

    @Override
    public int getItemCount() {
        return mSearch.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name,price,infor;
        public SearchViewHolder(@NonNull View view) {
            super(view);
            img=view.findViewById(R.id.item_img);
            name=view.findViewById(R.id.item_name);
            price=view.findViewById(R.id.item_price);
            infor=view.findViewById(R.id.item_describe);
        }
    }
}
