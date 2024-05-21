package com.example.de3navi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.de3navi.R;
import com.example.de3navi.model.Book;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {
    private List<Book> list;
    private ItemListener itemListener;
    public RecycleViewAdapter(){
        list = new ArrayList<>();
    }
    public  void  setList(List<Book> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public Book getItem(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Book item = list.get(position);
        holder.ten.setText(item.getTen());
        holder.noikhoihanh.setText(item.getNoikhoihanh());
        holder.ngay.setText(item.getNgay());
        holder.dichvu.setText(item.getDichvu());
        if(item.getKigui()==0){
            holder.kygui.setText("Không kí gửi");
        }
        else{
            holder.kygui.setText("Có kí gửi");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ten,noikhoihanh,ngay,dichvu,kygui;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tvTen);
            noikhoihanh = itemView.findViewById(R.id.tvNoikhoihanh);
            ngay = itemView.findViewById(R.id.tvNgay);
            dichvu = itemView.findViewById(R.id.tvDichvu);
            kygui=itemView.findViewById(R.id.kygui);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemListener!=null){
                itemListener.onItemClick(v,getAdapterPosition());
            }
        }
    }

    public interface ItemListener{
        void onItemClick(View view,int position);
    }
}
