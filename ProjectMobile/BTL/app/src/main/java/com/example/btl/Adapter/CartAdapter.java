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
import com.example.btl.View.CartActivity;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private Context context;
    private List<Cart> list;
    private CartActivity cartActivity;

    public CartAdapter(Context context,List<Cart> list,CartActivity cartActivity) {
        this.context = context;
        this.list = list;
        this.cartActivity=cartActivity;
    }
    public void setList(List<Cart> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = list.get(position);
        holder.cartProductName.setText(cart.getName());
        holder.cartProductQuantity.setText(cart.getQuantity() + "");
        holder.cartProductSize.setText(cart.getSize());
        holder.cartProductColor.setText(cart.getColor());
        holder.cartProductPrice.setText("Giá: " + cart.getPrice()+ " VNĐ");
        Glide.with(context).load(Uri.parse(cart.getImage())).into(holder.cartProductImage);
        holder.btngiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cart.getQuantity()<=1){
                    list.remove(position);
                    double total = calculateTotal();
                    cartActivity.updateTotal(total);
                    notifyDataSetChanged();
                }else{
                    int newQuantity = cart.getQuantity() - 1;
                    list.get(position).setQuantity(newQuantity);
                    holder.cartProductQuantity.setText(cart.getQuantity() + "");
                    double total = calculateTotal();
                    cartActivity.updateTotal(total);
                }
            }
        });
        holder.btntang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newQuantity = cart.getQuantity() + 1;
                list.get(position).setQuantity(newQuantity);
                holder.cartProductQuantity.setText(cart.getQuantity() + "");
                double total = calculateTotal();
                cartActivity.updateTotal(total);
            }
        });

    }
    private double calculateTotal() {
        double total = 0;
        for (Cart cart : list) {
            total += cart.getQuantity() * cart.getPrice();
        }
        return total;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder  {
        private ImageView cartProductImage;
        private Button btngiam,btntang;
        private TextView cartProductName, cartProductQuantity, cartProductPrice,cartProductSize,cartProductColor;
        public CartViewHolder(@NonNull View view) {
            super(view);
            cartProductImage=view.findViewById(R.id.cartProductImage);
            cartProductName=view.findViewById(R.id.cartProductName);
            cartProductQuantity=view.findViewById(R.id.cartProductQuantity);
            cartProductPrice=view.findViewById(R.id.cartProductPrice);
            cartProductSize=view.findViewById(R.id.cartProductSize);
            cartProductColor=view.findViewById(R.id.cartProductColor);
            btngiam=view.findViewById(R.id.btngiam);
            btntang=view.findViewById(R.id.btntang);
        }

    }
}
