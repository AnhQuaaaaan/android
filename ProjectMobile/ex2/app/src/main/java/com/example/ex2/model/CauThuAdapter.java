package com.example.ex2.model;

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

import com.example.ex2.R;

import java.util.ArrayList;
import java.util.List;

public class CauThuAdapter  extends RecyclerView.Adapter<CauThuAdapter.CauThuViewHolder>  {
    private Context context;
    private CauThuItemListener mCauThuItem;

    private List<CauThu> mList;
    private List<CauThu> listBackup;

    public CauThuAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
        listBackup = new ArrayList<>();
    }
    public List<CauThu> getBackup(){
        return listBackup;
    }

    public void setmList(List<CauThu> mList){
        this.mList = mList;
    }

    public void filterList(List<CauThu> filterList){
        mList=filterList;
        notifyDataSetChanged();
    }
    public void setClickListener(CauThuItemListener mCauThuItem){
        this.mCauThuItem = mCauThuItem;
    }

    public void update(int position, CauThu cauThu){
        listBackup.set(position,cauThu);
        mList.set(position,cauThu);
        notifyDataSetChanged();
    }
    public CauThu getItem(int position){
        return mList.get(position);
    }
    @NonNull
    @Override
    public CauThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new CauThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CauThuViewHolder holder, int position) {
        CauThu cauThu = mList.get(position);
        if(cauThu == null){
            return;
        }
        holder.tvTen.setText(cauThu.getTen());
        if(cauThu.isHauve()){
            holder.cb1.setChecked(true);
        }
        if(cauThu.isTienve()){
            holder.cb2.setChecked(true);
        }
        if(cauThu.isTiendao()){
            holder.cb3.setChecked(true);
        }
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa!!!");
                builder.setMessage("Ban co muon xoa "+ cauThu.getTen());
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

    public void add(CauThu d){
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
    public class CauThuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvTen;
        private CheckBox cb1,cb2,cb3;
        private Button remove;
        public CauThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen=itemView.findViewById(R.id.tvTen);
            cb1=itemView.findViewById(R.id.cbhauve);
            cb2=itemView.findViewById(R.id.cbtienve);
            cb3=itemView.findViewById(R.id.cbtiendao);
            remove=itemView.findViewById(R.id.btremove);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mCauThuItem!=null){
                mCauThuItem.onItemClick(itemView,getAdapterPosition());
            }
        }
    }
    public interface CauThuItemListener{
        void onItemClick(View view, int position);
    }
}
