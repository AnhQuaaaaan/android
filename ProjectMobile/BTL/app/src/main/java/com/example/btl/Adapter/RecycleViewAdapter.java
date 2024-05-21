package com.example.btl.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.example.btl.Model.Clothes;
import com.example.btl.R;
import com.example.btl.View.MainActivity;
import com.example.btl.View.OrderActivity;
import com.example.btl.View.OrderClothesActivity;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ListViewHolder> {
    private Context context;
    private List<Clothes> list;
    private ClothesListener clothesListener;


    public RecycleViewAdapter(Context context) {
        this.context=context;
        list=new ArrayList<>();
    }
    public void setClothesListener(ClothesListener clothesListener) {
        this.clothesListener = clothesListener;
    }

    public void setList(List<Clothes> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Clothes getClothes(int position){
        return list.get(position);
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Clothes clothes=list.get(position);
        holder.name.setText("Tên Sản Phẩm: "+clothes.getName());
        holder.size.setText("Size: "+clothes.getSize());
        holder.color.setText("Màu: "+clothes.getColor());
        holder.price.setText("Giá: "+clothes.getPrice()+"");
        Glide.with(context)
                .load(Uri.parse(clothes.getImg()))
                .into(holder.img);
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co chac chan muon xoa "+ clothes.getName() + " khong");
                builder.setIcon(R.drawable.remove);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clothesListener.onClothesDelete(list.get(holder.getAdapterPosition()));
                        Toast.makeText(context.getApplicationContext(), "Xóa thành công",Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();

            }
        });
        holder.btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clothes clothes1=list.get(holder.getAdapterPosition());
                Intent intent = new Intent(context, OrderClothesActivity.class);
                intent.putExtra("clothes", clothes1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name,size,color,price;
        private ImageView img;
        private Button btndelete,btnorder;
        public ListViewHolder(@NonNull View view) {
            super(view);
            img=view.findViewById(R.id.img);
            name=view.findViewById(R.id.tvName);
            size=view.findViewById(R.id.tvSize);
            color=view.findViewById(R.id.tvColor);
            price=view.findViewById(R.id.tvPrice);
            btndelete=view.findViewById(R.id.btdelete);
            btnorder=view.findViewById(R.id.btnorder);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clothesListener!=null){
                clothesListener.onClothesClick(view,getAdapterPosition());
            }
        }
    }
    public interface ClothesListener{
        void onClothesClick(View view,int position);
        void onClothesDelete(Clothes clothes);

    }
}
