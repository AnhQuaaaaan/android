package com.example.btl.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl.Model.Cart;
import com.example.btl.Model.Clothes;
import com.example.btl.R;
import com.example.btl.View.InvoiceActivity;

import java.util.ArrayList;
import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder>{
    private Context context;
    private List<Cart> list;

    public InvoiceAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }
    public void setList(List<Cart> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice, parent, false);
        return new InvoiceViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {
        Cart cart = list.get(position);
        holder.tvName.setText("Tên sản phẩm: "+cart.getName());
        holder.tvQuantity.setText("x"+cart.getQuantity() + "");
        holder.tvSize.setText("Size: "+cart.getSize());
        holder.tvColor.setText("Màu: "+cart.getColor());
        holder.tvPrice.setText("Giá: " + cart.getPrice()+ " VNĐ");
        Glide.with(context).load(Uri.parse(cart.getImage())).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InvoiceViewHolder extends RecyclerView.ViewHolder  {
        private ImageView img;
        private TextView tvName, tvSize, tvColor,tvPrice,tvQuantity;
        public InvoiceViewHolder(@NonNull View view) {
            super(view);
            img=view.findViewById(R.id.img);
            tvName=view.findViewById(R.id.tvName);
            tvQuantity=view.findViewById(R.id.tvQuantity);
            tvSize=view.findViewById(R.id.tvSize);
            tvColor=view.findViewById(R.id.tvColor);
            tvPrice=view.findViewById(R.id.tvPrice);
        }

    }
}
