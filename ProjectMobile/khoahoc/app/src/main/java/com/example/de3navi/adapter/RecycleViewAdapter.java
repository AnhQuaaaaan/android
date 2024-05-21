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
        List<Book> list1=new ArrayList<>();
        for(Book s:list){
            if(s.getNgaybatdau().substring(6,10).equals("2024")){
                list1.add(s);
            }
        }
        this.list = list1;
        notifyDataSetChanged();
    }
    public  void  setList1(List<Book> list){
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
        holder.chuyennganh.setText(item.getChuyennganh());
        holder.ngay.setText(item.getNgaybatdau());
        holder.hocphi.setText(item.getHocphi());
        if(item.getKichhoat()==0){
            holder.kichhoat.setText("Chưa Kích hoạt");
        }
        else{
            holder.kichhoat.setText("Đã kích hoạt");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ten,chuyennganh,ngay,kichhoat,hocphi;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tvTen);
            chuyennganh = itemView.findViewById(R.id.tvChuyenNganh);
            ngay = itemView.findViewById(R.id.tvNgay);
            kichhoat = itemView.findViewById(R.id.kichhoat);
            hocphi=itemView.findViewById(R.id.tvHocphi);
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
