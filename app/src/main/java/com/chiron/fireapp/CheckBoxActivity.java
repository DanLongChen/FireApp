package com.chiron.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chiron.fireapp.utils.ToastUtil;

public class CheckBoxActivity extends AppCompatActivity {
    private CheckBox mCb5, mCb6, mCb7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
        mCb5=findViewById(R.id.cb_5);
        mCb6=findViewById(R.id.cb_6);
        mCb7=findViewById(R.id.cb_7);

        mCb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ToastUtil.showToast(CheckBoxActivity.this,isChecked?buttonView.getText().toString():"未选中");
            }
        });
    }
}