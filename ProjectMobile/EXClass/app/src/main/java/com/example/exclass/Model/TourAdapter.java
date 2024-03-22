package com.example.exclass.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exclass.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private Context context;
    private List<Tour> mlist;
    private TourItemListen mTourItem;
    public TourAdapter(Context context) {
        this.context = context;
        mlist=new ArrayList<>();
    }
    public void setClickListener(TourItemListen mTourItem){
        this.mTourItem=mTourItem;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new TourViewHolder(view);
    }
    public Tour getItem(int position){
        return mlist.get(position);
    }
    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        Tour tour=mlist.get(position);
        if(tour==null){
            return;
        }
        holder.img.setImageResource(tour.getImg());
        holder.tvlichtrinh.setText(tour.getLichtrinh());
        holder.tvthoigian.setText(tour.getTime());
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlist.remove(position);
                notifyDataSetChanged();
            }
        });
    }
    public void add(Tour t){
        mlist.add(t);
        notifyDataSetChanged();
    }
    public void update(int position,Tour t){
        mlist.set(position,t);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(mlist!=null){
            return mlist.size();
        }
        return 0;
    }

    public class TourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView tvlichtrinh,tvthoigian;
        private Button btRemove;
        public TourViewHolder(@NonNull View view) {
            super(view);
            img=view.findViewById(R.id.img);
            tvlichtrinh=view.findViewById(R.id.txtlichtrinh);
            tvthoigian=view.findViewById(R.id.txtthoigian);
            btRemove=view.findViewById(R.id.btRemove);
            view.setOnClickListener(this);
        }
          @Override
        public void onClick(View view) {
            if(mTourItem!=null){
                mTourItem.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface TourItemListen{
        void onItemClick(View view, int position);
    }
}
