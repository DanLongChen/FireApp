package com.chiron.fireapp.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.chiron.fireapp.R;
import com.chiron.fireapp.recyclerview.adapter.HorAdapter;
import com.chiron.fireapp.utils.ToastUtil;

public class HorRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hor_recycler_view);
        recyclerView=findViewById(R.id.rc_view);
        LinearLayoutManager manager = new LinearLayoutManager(HorRecyclerViewActivity.this);
        /**
         * 使用Grid布局（网格布局，第二个参数是一行展示几个item）
         */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(HorRecyclerViewActivity.this,3);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new HorAdapter(HorRecyclerViewActivity.this, new HorAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                ToastUtil.showToast(HorRecyclerViewActivity.this,"click:"+position);
            }

            @Override
            public void onLongClick(int position) {
                ToastUtil.showToast(HorRecyclerViewActivity.this,"long click:"+position);
            }
        }));
        recyclerView.addItemDecoration(new MyDecoration());
    }

    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            //添加分割线，其背景颜色其实是RecyclerView中的背景颜色
            outRect.set(0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight),0);
        }
    }
}