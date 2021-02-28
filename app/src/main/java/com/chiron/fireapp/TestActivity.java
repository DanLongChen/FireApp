package com.chiron.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestActivity extends AppCompatActivity {
    private Button btnRadio;
    private Button btnCheckBox;
    private Button btnImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
        setListener();
    }

    private void init() {
        btnRadio = findViewById(R.id.btn_radio);
        btnCheckBox = findViewById(R.id.btn_check_box);
        btnImageView = findViewById(R.id.btn_image_view);
    }

    private void setListener(){
        OnClick listener=new OnClick();
        btnRadio.setOnClickListener(listener);
        btnCheckBox.setOnClickListener(listener);
        btnImageView.setOnClickListener(listener);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.btn_radio:
                    intent = new Intent(TestActivity.this, RadioButtonActivity.class);
                    break;
                case R.id.btn_check_box:
                    intent = new Intent(TestActivity.this, CheckBoxActivity.class);
                    break;
                case R.id.btn_image_view:
                    intent = new Intent(TestActivity.this, ImageViewActivity.class);
                    break;
                default:
                    intent = new Intent(TestActivity.this, TestActivity.class);
            }
            startActivity(intent);
        }
    }
}