package com.chiron.fireapp.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.chiron.fireapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerViewActivity extends AppCompatActivity {
    @BindView(R.id.btn_linear)
    Button btnLinear;
    @BindView(R.id.btn_hor)
    Button btnHor;
    @BindView(R.id.btn_pu)
    Button btnPu;

    /**
     * 资源绑定可以使用BindBool、BindDimen、BindDrawable、BindInt、BindString等
     * 例如：@BindString(R.string.title) String title;
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_linear,R.id.btn_hor,R.id.btn_pu})
    protected void bind(View view){
        Intent intent = null;
        switch(view.getId()){
            case R.id.btn_linear:
                intent = new Intent(RecyclerViewActivity.this, LinearRecyclerViewActivity.class);
                break;
            case R.id.btn_hor:
                intent = new Intent(RecyclerViewActivity.this, HorRecyclerViewActivity.class);
                break;
            case R.id.btn_pu:
                intent = new Intent(RecyclerViewActivity.this, PuRecyclerViewActivity.class);
                break;
            default:
                break;
        }
        if(intent!=null){
            startActivity(intent);
        }
    }
}