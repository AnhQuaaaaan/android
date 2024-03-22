package com.example.recycleviewcrud.Model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleviewcrud.R;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder>{
    private Context context;
    private List<Cat> listBackup;
    private List<Cat> mlist;
    private CatItemListen mCatItem;

    public CatAdapter(Context context) {
        this.context = context;
        mlist=new ArrayList<>();
        listBackup=new ArrayList<>();
    }
    public List<Cat> getBackup(){
        return listBackup;
    }
    public void filterList(List<Cat> filterlist){
        mlist=filterlist;
        notifyDataSetChanged();
    }
    public void setClickListener(CatItemListen mCatItem){
        this.mCatItem=mCatItem;
    }
    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new CatViewHolder(view);
    }
    public Cat getItem(int position){
        return mlist.get(position);
    }
    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat=mlist.get(position);
        if(cat==null){
            return;
        }
        holder.img.setImageResource(cat.getImg());
        holder.tvName.setText(cat.getName());
        holder.tvDescribe.setText(cat.getDescribe());
        holder.tvPrice.setText(cat.getPrice()+"");
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co chac chan muon xoa"+cat.getName()+" nay khong");
                builder.setIcon(R.drawable.cat1);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listBackup.remove(position);
                        mlist.remove(position);
                        notifyDataSetChanged();
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
    }
    public void add(Cat c){
        listBackup.add(c);
        mlist.add(c);
        notifyDataSetChanged();
    }
    public void update(int position,Cat c){
        listBackup.set(position,c);
        mlist.set(position,c);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(mlist!=null){
            return mlist.size();
        }
        return 0;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView tvName,tvDescribe,tvPrice;
        private Button btRemove;
        public CatViewHolder(@NonNull View view) {
            super(view);
            img=view.findViewById(R.id.img);
            tvName=view.findViewById(R.id.txtName);
            tvDescribe=view.findViewById(R.id.txtDescribe);
            tvPrice=view.findViewById(R.id.txtPrice);
            btRemove=view.findViewById(R.id.btRemove);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mCatItem!=null){
                mCatItem.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface CatItemListen{
        void onItemClick(View view, int position);
    }
}
