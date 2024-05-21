package com.example.myproject.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myproject.EventBus.UpdateDeleteEvent;
import com.example.myproject.Interface.ItemClickListener;
import com.example.myproject.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.activity_container.activity.ProductDetailActivity;
import com.example.myproject.model.Product;
import com.example.myproject.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Product> list;
    private static final int VIEW_TYPE_DATA = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_DATA) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(view);
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading_more, parent, false);
        return new LoadingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ProductViewHolder) {
            ProductViewHolder productViewHolder = (ProductViewHolder) holder;
            Product p = list.get(position);
            productViewHolder.itemMobileName.setText(p.getName());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            productViewHolder.itemMobilePrice.setText("Giá: " + decimalFormat.format(Double.parseDouble(p.getPrice())) + " VNĐ");
            productViewHolder.itemMobileDescription.setText(p.getDescription());
            Glide.with(context).load(p.getImage()).into(productViewHolder.itemMobileImg);

            productViewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int pos, boolean isLongClick) {
                    if (!isLongClick) {

                    }

                    if (isLongClick && Utils.currentUser.getRole() == 1) {
                        // gui san pham qua manage activity de xu ly
                        EventBus.getDefault().postSticky(new UpdateDeleteEvent(p));
                    }
                }
            });
        } else {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_DATA;
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
    private ProgressBar progressBar;

    public LoadingViewHolder(@NonNull View view) {
        super(view);
        progressBar = view.findViewById(R.id.progressBar);
    }
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, View.OnLongClickListener {
        private TextView itemMobileName, itemMobilePrice, itemMobileDescription;
        private ImageView itemMobileImg;
        private ItemClickListener itemClickListener;
        public ProductViewHolder(@NonNull View view) {
            super(view);
            itemMobileName = view.findViewById(R.id.itemMobileName);
            itemMobilePrice = view.findViewById(R.id.itemMobilePrice);
            itemMobileDescription = view.findViewById(R.id.itemMobileDescription);
            itemMobileImg = view.findViewById(R.id.itemMobileImg);
            view.setOnClickListener(this);

            // xu ly sua xoa san pham cho admin
            if (Utils.currentUser.getRole() == 1) {
                view.setOnCreateContextMenuListener(this);
                view.setOnLongClickListener(this);
            }
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0, 0, getAdapterPosition(), "Sửa");
            menu.add(0, 1, getAdapterPosition(), "Xóa");
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return false;
        }

    }
}
