package com.chiron.fireapp.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chiron.fireapp.R;

public class PuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private OnItemClickListener listener;

    public PuAdapter(Context context, OnItemClickListener listener){
        this.mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //构造传入的view表示每一个item长什么样子
        if(viewType==0){
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_pu_item,parent,false));
        }else{
            return new LinearViewHolder2(LayoutInflater.from(mContext).inflate(R.layout.layout_linear_item,parent,false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            if (position % 3 == 0) {
                ((LinearViewHolder) holder).imageView.setImageResource(R.drawable.tx);
            } else {
                ((LinearViewHolder) holder).imageView.setImageResource(R.drawable.yuwen);
            }
            ((LinearViewHolder) holder).imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(position);
                }
            });

            ((LinearViewHolder) holder).imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongClick(position);
                    return true;
                }
            });
        } else {
            ((LinearViewHolder2) holder).textView.setText("hello world");
            ((LinearViewHolder2) holder).textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(position);
                }
            });

            ((LinearViewHolder2) holder).textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongClick(position);
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
        }
    }

    class LinearViewHolder2 extends RecyclerView.ViewHolder {
        private TextView textView;//textView在布局文件itemView中

        public LinearViewHolder2(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_title);
        }
    }

    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }
}
