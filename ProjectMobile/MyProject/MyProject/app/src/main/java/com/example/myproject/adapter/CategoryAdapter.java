package com.example.myproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.model.Category;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private List<Category> list;
    private Context context;

    public CategoryAdapter(Context context, List<Category> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (v == null) {
            viewHolder = new ViewHolder();
            v = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.item_category, null);
            viewHolder.txtItemName = v.findViewById(R.id.itemName);
            viewHolder.imgItemImage = v.findViewById(R.id.itemImage);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.txtItemName.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getImg()).into(viewHolder.imgItemImage);
        return v;
    }

    public class ViewHolder{
        private TextView txtItemName;
        private ImageView imgItemImage;

    }

}
