package com.chiron.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chiron.fireapp.utils.ToastUtil;

public class StudyActivity extends AppCompatActivity {
    private RadioGroup mRG;
    private CheckBox mCB1;
    private CheckBox mCB2;
    private CheckBox mCB3;
    private Button btnTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        init();
    }

    private void init(){
        mRG=findViewById(R.id.study_rg);
        mCB1=findViewById(R.id.study_cb_1);
        mCB2=findViewById(R.id.study_cb_2);
        mCB3=findViewById(R.id.study_cb_3);
        btnTest=findViewById(R.id.btn_test);
        initListener();
    }

    private void initListener(){
        mRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=group.findViewById(checkedId);
                ToastUtil.showToastInCenter(getApplicationContext(),rb.getText().toString());
            }
        });

        mCB1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ToastUtil.showToastInCenter(getApplicationContext(),isChecked?buttonView.getText().toString():"未选择");
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudyActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });
    }
}