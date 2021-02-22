package com.chiron.fireapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.chiron.fireapp.adapter.LinearCourseFinishAdapter;
import com.chiron.fireapp.utils.ToastUtil;

public class PlayActivity extends AppCompatActivity {
    private RecyclerView mRVFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mRVFinish=findViewById(R.id.play_rv_finish);
        //利用adapter显示item
        mRVFinish.setLayoutManager(new LinearLayoutManager(PlayActivity.this));
        //设置adapter
        mRVFinish.setAdapter(new LinearCourseFinishAdapter(PlayActivity.this, new LinearCourseFinishAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                ToastUtil.showToastInCenter(PlayActivity.this,"click: "+pos);
            }
        }));

    }
}