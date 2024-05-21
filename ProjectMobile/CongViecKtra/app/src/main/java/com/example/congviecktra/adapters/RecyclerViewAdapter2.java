package com.example.congviecktra.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.congviecktra.R;
import com.example.congviecktra.models.CongViec;

import java.util.List;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.CongViecViewHolder2> {
    private List<CongViec> listCongViec;

    public RecyclerViewAdapter2(List<CongViec> listCongViec) {
        this.listCongViec = listCongViec;
    }

    public List<CongViec> getListCongViec() {
        return listCongViec;
    }

    public void setListCongViec(List<CongViec> listCongViec) {
        this.listCongViec = listCongViec;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CongViecViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_adapter2,parent,false);
        return new RecyclerViewAdapter2.CongViecViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CongViecViewHolder2 holder, int position) {
        CongViec congviec = listCongViec.get(position);
        holder.txtChuaht.setText(congviec.getTinhtrang());
        holder.textsoluong.setText(congviec.getSoluong() + "");
    }

    @Override
    public int getItemCount() {
         return listCongViec!=null ? listCongViec.size() : 0;
    }

    public class CongViecViewHolder2 extends RecyclerView.ViewHolder {
        private TextView txtChuaht, textsoluong;
        public CongViecViewHolder2(@NonNull View itemView) {
            super(itemView);
            txtChuaht = itemView.findViewById(R.id.textchuaht);
            textsoluong = itemView.findViewById(R.id.textsoluong);
        }
    }
}