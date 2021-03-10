package com.chiron.fireapp.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.chiron.fireapp.R;
import com.chiron.fireapp.recyclerview.adapter.PuAdapter;
import com.chiron.fireapp.utils.ToastUtil;

public class PuRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRvPu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pu_recycler_view);

        mRvPu = findViewById(R.id.rv_pu);
        //如果是垂直的布局则表示有多少列，否则表示有多少行
        mRvPu.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRvPu.setAdapter(new PuAdapter(PuRecyclerViewActivity.this, new PuAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                ToastUtil.showToast(PuRecyclerViewActivity.this,"click:"+position);
            }

            @Override
            public void onLongClick(int position) {
                ToastUtil.showToast(PuRecyclerViewActivity.this,"long click:"+position);
            }
        }));
        mRvPu.addItemDecoration(new MyDecoration());
    }

    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int gap = getResources().getDimensionPixelSize(R.dimen.dividerHeight);
            outRect.set(gap,gap,gap,gap);
        }
    }
}