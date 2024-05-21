package com.example.a10052024.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a10052024.R;
import com.example.a10052024.model.Book;

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
        holder.cautruc.setText(item.getCautruc());
        holder.ngay.setText(item.getNgay());
        holder.thegioi.setText("tg: "+item.getThegioi());
        holder.vietnam.setText("vn: "+item.getVietnam());
        if(item.getVacxin()==0){
            holder.vacxin.setText("Chưa có vacxin");
        }
        else{
            holder.vacxin.setText("Có vacxin");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ten,cautruc,ngay,thegioi,vietnam,vacxin;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tvTen);
            cautruc = itemView.findViewById(R.id.tvcautruc);
            ngay = itemView.findViewById(R.id.tvNgay);
            thegioi = itemView.findViewById(R.id.tvtgioi);
            vietnam = itemView.findViewById(R.id.tvvietnam);
            vacxin=itemView.findViewById(R.id.tvvacxin);
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
