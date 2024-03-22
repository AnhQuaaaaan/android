package com.example.myapplication1.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder>{
//    private Context context;
    private List<Cat> mlist ;
    private CatItemListener catItemListener;

    public CatAdapter( List<Cat> mlist) {
//        this.context = context;
        this.mlist = mlist;
    }

    public void setCatItemListener(CatItemListener catItemListener) {
        this.catItemListener = catItemListener;
    }


    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat=mlist.get(position);
        if (cat==null){
            return;
        }
        holder.img.setImageResource(cat.getImg());
        holder.tv.setText(cat.getName());
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context.getApplicationContext(), cat.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(mlist!=null){
            return mlist.size();
        }
        return 0;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView tv;
//        private CardView cardView;
        public CatViewHolder(@NonNull View view) {
            super(view);
            img=view.findViewById(R.id.img);
            tv=view.findViewById(R.id.tname);
            view.setOnClickListener(this);
//            cardView=view.findViewById(R.id.cview);
        }

        @Override
        public void onClick(View view) {
            if (catItemListener!=null){
                catItemListener.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface CatItemListener{
        public void onItemClick(View view,int position);

    }
}
