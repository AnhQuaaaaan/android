package com.example.test1.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.R;

import java.util.ArrayList;
import java.util.List;

public class CauThuAdapter extends RecyclerView.Adapter<CauThuAdapter.CauThuViewHolder>{
    private Context context;
    private List<CauThu> mList;
    private List<CauThu> listBackup;
    private CauThuItemListener mCauThuItem;

    public CauThuAdapter(Context context) {
        this.context = context;
        mList=new ArrayList<>();
        listBackup=new ArrayList<>();
    }
    public List<CauThu> getBackup(){
        return listBackup;
    }
    public void filterList(List<CauThu>filterlist){
        mList=filterlist;
        notifyDataSetChanged();

    }
    public CauThu getItem(int position){
        return mList.get(position);
    }
    public void setClickListener(CauThuItemListener mCauThuItem){
        this.mCauThuItem=mCauThuItem;
    }

    @NonNull
    @Override
    public CauThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new CauThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CauThuViewHolder holder, int position) {
        CauThu cauThu=mList.get(position);
        if(cauThu==null){
            return;
        }
        if (cauThu.getGioitinh().equals("Nam")){
            holder.img.setImageResource(R.drawable.male);
        }
        else{
            holder.img.setImageResource(R.drawable.female);
        }
        holder.tvTen.setText(cauThu.getTen());
        if(cauThu.isHauve()){
            holder.cb1.setChecked(true);
        }
        else{
            holder.cb1.setChecked(false);
        }
        if(cauThu.isTienve()){
            holder.cb2.setChecked(true);
        }
        else{
            holder.cb2.setChecked(false);
        }
        if(cauThu.isTiendao()){
            holder.cb3.setChecked(true);
        }
        else{
            holder.cb3.setChecked(false);
        }
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co chac chan muon xoa "+ cauThu.getTen() + " khong");
                builder.setIcon(R.drawable.delete);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listBackup.remove(position);
                        mList.remove(position);
                        notifyDataSetChanged();
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
    }
    public void add(CauThu c){
        listBackup.add(c);
        mList.add(c);
        notifyDataSetChanged();
    }
    public void update(int position,CauThu c){
        listBackup.set(position,c);
        mList.set(position,c);
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
        private ImageView img;
        private TextView tvTen;
        private CheckBox cb1,cb2,cb3;
        private Button btRemove;
        public CauThuViewHolder(@NonNull View view) {
            super(view);
            img=view.findViewById(R.id.img);
            tvTen=view.findViewById(R.id.tvTen);
            cb1=view.findViewById(R.id.cbhauve);
            cb2=view.findViewById(R.id.cbtienve);
            cb3=view.findViewById(R.id.cbtiendao);
            btRemove=view.findViewById(R.id.btremove);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if(mCauThuItem!=null){
                mCauThuItem.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface CauThuItemListener{
        void onItemClick(View view, int position);
    }
}
