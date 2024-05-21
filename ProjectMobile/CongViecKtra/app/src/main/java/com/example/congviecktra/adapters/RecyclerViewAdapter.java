package com.example.congviecktra.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.congviecktra.R;
import com.example.congviecktra.models.CongViec;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CongViecViewHolder>{
    public interface ItemClickListener{
        void onItemClick(CongViec congviec);
    }

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setListCongViec(List<CongViec> listCongViec) {
        this.listCongViec = listCongViec;
        notifyDataSetChanged();
    }

    public List<CongViec> getListCognViec() {
        return listCongViec;
    }

    private List<CongViec> listCongViec;

    public RecyclerViewAdapter(List<CongViec> listCongViec) {
        this.listCongViec = listCongViec;
    }

    public class CongViecViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvAuthor, tvPublishDate, tvPublisher, tvPrice;
        public CongViecViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvPublishDate = itemView.findViewById(R.id.tvPublishDate);
            tvPublisher = itemView.findViewById(R.id.tvPublisher);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
    @NonNull
    @Override
    public CongViecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new CongViecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CongViecViewHolder CongViecViewHolder, int position) {
//        CongViecViewHolder CongViecViewHolder = (CongViecViewHolder) holder;
        CongViec congviec = listCongViec.get(position);
        CongViecViewHolder.tvName.setText(congviec.getTencongviec());
        CongViecViewHolder.tvAuthor.setText(congviec.getNoidungcongviec());
        CongViecViewHolder.tvPublishDate.setText(congviec.getNgayhoanthanh());
        CongViecViewHolder.tvPublisher.setText(congviec.getTinhtrang());
        CongViecViewHolder.tvPrice.setText(congviec.getCongtac());

        CongViecViewHolder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(congviec);
        });
    }

    @Override
    public int getItemCount() {
        return listCongViec!=null ? listCongViec.size() : 0;
    }

}
