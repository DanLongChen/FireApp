package com.chiron.fireapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chiron.fireapp.R;

public class MyListAdapter extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater mLayoutInflater;
    public MyListAdapter(Context context){
        this.mcontext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        //返回列表长度
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView tvTitle;
        public TextView tvTime;
        public TextView tvContent;
    }

    //定义每一行长什么样子
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView = mLayoutInflater.inflate(R.layout.layout_list_item, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.iv);
            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.tvTime = convertView.findViewById(R.id.tv_time);
            holder.tvContent = convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //给布局设置属性
        holder.tvTitle.setText("这是标题");
        holder.tvTime.setText("2020-02-2");
        holder.tvContent.setText("HelloWorld");
        Glide.with(mcontext).load("https://www.diyifanwen.com/images/sichuan/0872403401922790.jpg").into(holder.imageView);
        return convertView;
    }
}
