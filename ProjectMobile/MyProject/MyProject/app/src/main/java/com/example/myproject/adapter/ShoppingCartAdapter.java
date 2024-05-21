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
import com.example.myproject.EventBus.TotalMoneyEvent;
import com.example.myproject.Interface.ImageClickListener;
import com.example.myproject.R;
import com.example.myproject.activity_container.activity.ShoppingCartActivity;
import com.example.myproject.model.ShoppingCart;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.CartViewHolder> {
    private Context context;
    private List<ShoppingCart> list;
    private ShoppingCartActivity shoppingCartActivity;

    public ShoppingCartAdapter(Context context, List<ShoppingCart> list, ShoppingCartActivity activity) {
        this.context = context;
        this.list = list;
        this.shoppingCartActivity = activity;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ShoppingCart cart = list.get(position);
        holder.cartProductName.setText(cart.getName());
        holder.cartProductQuantity.setText(cart.getQuantity() + "");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.cartProductPrice.setText("Giá: " + decimalFormat.format(cart.getPrice() * cart.getQuantity()) + " VNĐ");
        Glide.with(context).load(cart.getImage()).into(holder.itemCartImage);

        holder.setImageClickListener(new ImageClickListener() {
            @Override
            public void onImageClick(View view, int position, int value) {
                if(list.size() < 0) return;

                if (value == 1) {
                    if (list.get(position).getQuantity() > 1) {
                        int newQuantity = list.get(position).getQuantity() - 1;
                        list.get(position).setQuantity(newQuantity);
                    } else {
                        list.remove(position);
                        notifyDataSetChanged();

                        if (list.size() == 0) {
                            shoppingCartActivity.cartEmpty.setVisibility(View.VISIBLE);
                            shoppingCartActivity.cartTotal.setText("Giá: 0 VNĐ");
                        } else {
                            // tinh tong tien lai
                            EventBus.getDefault().postSticky(new TotalMoneyEvent());
                        }
                        return;
                    }
                } else if (value == 2) {
                    int newQuantity = list.get(position).getQuantity() + 1;
                    list.get(position).setQuantity(newQuantity);
                }

                holder.cartProductQuantity.setText(list.get(position).getQuantity() + "");
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.cartProductPrice.setText(
                        "Giá: " + decimalFormat.format(list.get(position).getPrice() * list.get(position).getQuantity()) + " VNĐ");

                // goi ham tinh tong lai bang thu vien event bus
                EventBus.getDefault().postSticky(new TotalMoneyEvent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView itemCartImage, cartSub, cartAdd;
        private TextView cartProductName, cartProductQuantity, cartProductPrice;
        private ImageClickListener imageClickListener;

        public CartViewHolder(@NonNull View view) {
            super(view);
            itemCartImage = view.findViewById(R.id.itemCartImage);
            cartProductName = view.findViewById(R.id.cartProductName);
            cartProductQuantity = view.findViewById(R.id.cartProductQuantity);
            cartProductPrice = view.findViewById(R.id.cartProductPrice);
            cartSub = view.findViewById(R.id.cartSub);
            cartAdd = view.findViewById(R.id.cartAdd);

            // image on click
            cartSub.setOnClickListener(this);
            cartAdd.setOnClickListener(this);
        }

        public void setImageClickListener(ImageClickListener imageClickListener) {
            this.imageClickListener = imageClickListener;
        }

        @Override
        public void onClick(View v) {
            if (v == cartSub) {
                imageClickListener.onImageClick(v, getAdapterPosition(), 1);
            } else if (v == cartAdd) {
                imageClickListener.onImageClick(v, getAdapterPosition(), 2);
            }
        }
    }
}
