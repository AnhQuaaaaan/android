package com.example.myproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.DTO.OrderDTO;
import com.example.myproject.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewOrderViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private Context context;
    private List<OrderDTO> list;

    public OrderAdapter(Context context, List<OrderDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_dto, parent, false);
        return new ViewOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewOrderViewHolder holder, int position) {
        OrderDTO orderDTO = list.get(position);
        holder.orderId.setText("Đơn hàng: " + orderDTO.getOrderId());
//        LinearLayoutManager layoutManager =
//                new LinearLayoutManager(holder.orderRecycleView.getContext(), LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        // so luong muc du lieu muon tai truoc -> giam do tre
        layoutManager.setInitialPrefetchItemCount(orderDTO.getListOrderDetailDTO().size());

        // adapter cho order detail dto
        OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(context, orderDTO.getListOrderDetailDTO());
        holder.orderRecycleView.setLayoutManager(layoutManager);
        holder.orderRecycleView.setAdapter(orderDetailAdapter);
        holder.orderRecycleView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewOrderViewHolder extends RecyclerView.ViewHolder {
        private TextView orderId;
        private RecyclerView orderRecycleView;
        public ViewOrderViewHolder(@NonNull View view) {
            super(view);
            orderId = view.findViewById(R.id.orderId);
            orderRecycleView = view.findViewById(R.id.orderRecycleView);
        }
    }
}
