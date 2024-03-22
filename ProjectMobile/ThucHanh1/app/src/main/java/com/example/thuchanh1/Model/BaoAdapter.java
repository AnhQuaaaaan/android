package com.example.thuchanh1.Model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh1.R;

import java.util.ArrayList;
import java.util.List;

public class BaoAdapter extends RecyclerView.Adapter<BaoAdapter.BaoViewHolder> {
    private Context context;
    private BaoItemListener mBaoItem;

    private List<com.example.thuchanh1.Model.Bao> mList;
    private List<com.example.thuchanh1.Model.Bao> listBackup;

    public BaoAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
        listBackup = new ArrayList<>();
    }

    public List<com.example.thuchanh1.Model.Bao> getBackup(){
        return listBackup;
    }

    public void setmList(List<com.example.thuchanh1.Model.Bao> mList){
        this.mList = mList;
    }

    public void filterList(List<com.example.thuchanh1.Model.Bao> filterList){
        mList=filterList;
        notifyDataSetChanged();
    }
    public void setClickListener(BaoItemListener mBaoItem){
        this.mBaoItem = mBaoItem;
    }

    public void update(int position, com.example.thuchanh1.Model.Bao bao){
        listBackup.set(position,bao);
        mList.set(position,bao);
        notifyDataSetChanged();
    }
    public com.example.thuchanh1.Model.Bao getItem(int position){
        return mList.get(position);
    }

    @NonNull
    @Override
    public BaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new BaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaoViewHolder holder, int position) {
        com.example.thuchanh1.Model.Bao ct = mList.get(position);
        if(ct == null){
            return;
        }
        holder.tvTen.setText(ct.getTen());
        holder.tvTime.setText(ct.getTime());
        if(ct.isCb1()){
            holder.cb1.setChecked(true);
        }
        if(ct.isCb2()){
            holder.cb2.setChecked(true);
        }
        if(ct.isCb3()){
            holder.cb3.setChecked(true);
        }
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa!!!");
                builder.setMessage("Ban co muon xoa "+ ct.getTen());
                builder.setIcon(R.drawable.ic_launcher_background);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listBackup.remove(position);
                        mList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    public void add(com.example.thuchanh1.Model.Bao d){
        listBackup.add(d);
        mList.add(d);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(mList!=null){
            return mList.size();
        }
        return 0;
    }

    public class BaoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvTen,tvTime;
        private CheckBox cb1,cb2,cb3;
        private Button remove;

        public BaoViewHolder(@NonNull View itemView){
            super(itemView);
            tvTen=itemView.findViewById(R.id.txtten);
            tvTime = itemView.findViewById(R.id.txttime);
            cb1=itemView.findViewById(R.id.cbchambiem);
            cb2=itemView.findViewById(R.id.cbsuthat);
            cb3=itemView.findViewById(R.id.cbphephan);
            remove=itemView.findViewById(R.id.bremove);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(mBaoItem!=null){
                mBaoItem.onItemClick(itemView,getAdapterPosition());
            }
        }
    }
    public interface BaoItemListener{
        void onItemClick(View view, int position);
    }
}

