package com.chiron.fireapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chiron.fireapp.adapter.MyListAdapter;
import com.chiron.fireapp.utils.ToastUtil;

public class ListViewActivity extends AppCompatActivity {
    private ListView mLv1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        init();
        mLv1.setAdapter(new MyListAdapter(ListViewActivity.this));
        setListener();
    }

    private void init(){
        mLv1 = findViewById(R.id.lv_1);
    }

    private void setListener(){
        mLv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showToast(ListViewActivity.this,"点击:"+position);
            }
        });
        mLv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showToast(ListViewActivity.this,"点击长按:"+position);
//                return false;//return false表示事件到我这里还没结束，继续处理，这时候OnItemClickListener会起作用
                return true;
            }
        });
    }
}
