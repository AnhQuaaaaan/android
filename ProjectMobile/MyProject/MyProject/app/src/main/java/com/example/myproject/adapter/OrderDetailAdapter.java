package com.example.myproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.DTO.OrderDetailDTO;
import com.example.myproject.R;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder> {
    private Context context;
    private List<OrderDetailDTO> list;

    public OrderDetailAdapter(Context context, List<OrderDetailDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail_dto, parent, false);
        return new OrderDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        OrderDetailDTO orderDetailDTO = list.get(position);
        holder.orderDetailName.setText(orderDetailDTO.getName());
        holder.orderDetailQuantity.setText("Số lượng: " + orderDetailDTO.getQuantity());
        Glide.with(context).load(orderDetailDTO.getImage()).into(holder.orderDetailImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder {
        private ImageView orderDetailImage;
        private TextView orderDetailName, orderDetailQuantity;
        public OrderDetailViewHolder(@NonNull View view) {
            super(view);
            orderDetailImage = view.findViewById(R.id.orderDetailImage);
            orderDetailName = view.findViewById(R.id.orderDetailName);
            orderDetailQuantity = view.findViewById(R.id.orderDetailQuantity);
        }
    }
}
