package com.chiron.fireapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chiron.fireapp.R;
import com.chiron.fireapp.database.entity.User;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private List<User> data;
    private LayoutInflater layoutInflater;

    public UserAdapter(Context context, List<User> data) {
        this.data = data;
//        layoutInflater = LayoutInflater.from(context);//其底部就是调用下面这一句
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private class ViewHolder{
        TextView tvId;
        TextView tvName;
        TextView tvPass;
    }

    @Override
    public int getCount() {
        if (data == null) {
            return 0;
        } else {
            return data.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_user,null);
            viewHolder = new ViewHolder();
            viewHolder.tvId= convertView.findViewById(R.id.tvId);
            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            viewHolder.tvPass = convertView.findViewById(R.id.tvPass);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvId.setText(String.valueOf(data.get(position).id));
        viewHolder.tvName.setText(String.valueOf(data.get(position).name));
        viewHolder.tvPass.setText(String.valueOf(data.get(position).pass));
        return convertView;
    }
}
