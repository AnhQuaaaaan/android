package com.example.myproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.Interface.ItemClickListener;
import com.example.myproject.R;
import com.example.myproject.activity_container.activity.ProductDetailActivity;
import com.example.myproject.model.Product;

import java.text.DecimalFormat;
import java.util.List;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.HomeViewHolder> {
    private Context context;
    List<Product> list;

    public NewProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_product, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Product product = list.get(position);
        holder.itemProductName.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.itemProductPrice.setText(decimalFormat.format(Double.parseDouble(product.getPrice())) + " VNĐ");
        Glide.with(context).load(product.getImage()).into(holder.itemProductImg);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if (!isLongClick) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("product", product);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView itemProductName, itemProductPrice;
        private ImageView itemProductImg;
        private ItemClickListener itemClickListener;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            itemProductName = view.findViewById(R.id.itemProductName);
            itemProductPrice = view.findViewById(R.id.itemProductPrice);
            itemProductImg = view.findViewById(R.id.itemProductImg);
            view.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }
}
