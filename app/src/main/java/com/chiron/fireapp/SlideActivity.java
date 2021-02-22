package com.chiron.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chiron.fireapp.utils.SlideMenu;

public class SlideActivity extends AppCompatActivity {
    private ImageView mIvHead;
    private SlideMenu slideMenu;
    private Button mBtnStudy;
    private Button mBtnPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        init();
        setListener();
    }

    private void init(){
        mIvHead=findViewById(R.id.iv_head);
        slideMenu=findViewById(R.id.slidemenu);
        mBtnStudy=findViewById(R.id.btn_study);
        mBtnPlay=findViewById(R.id.btn_play);
    }

    private void setListener(){
        onClick onClick=new onClick();
        mIvHead.setOnClickListener(onClick);
        mBtnStudy.setOnClickListener(onClick);
        mBtnPlay.setOnClickListener(onClick);
    }

    private class onClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent=null;
            switch(v.getId()){
                case R.id.btn_study:
                    intent=new Intent(SlideActivity.this,StudyActivity.class);
                    break;
                case R.id.btn_play:
                    intent = new Intent(SlideActivity.this,PlayActivity.class);
                    break;
                case R.id.iv_head:
                    //TODO 实现侧滑功能
                    slideMenu.switchMenu();
                    return;
                default:
                    break;
            }
            startActivity(intent);
        }
    }
}