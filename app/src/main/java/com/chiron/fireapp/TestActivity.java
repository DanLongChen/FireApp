package com.chiron.fireapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chiron.fireapp.recyclerview.RecyclerViewActivity;

public class TestActivity extends AppCompatActivity {
    private Button btnRadio;
    private Button btnCheckBox;
    private Button btnImageView;
    private Button btnListView;
    private Button btnGridView;
    private Button btnRecyclerView;
    private Button btnDatabase;
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
        btnListView = findViewById(R.id.btn_list_view);
        btnGridView = findViewById(R.id.btn_grid_view);
        btnRecyclerView = findViewById(R.id.btn_recycler_view);
        btnDatabase = findViewById(R.id.btn_database_test);
    }

    private void setListener(){
        OnClick listener=new OnClick();
        btnRadio.setOnClickListener(listener);
        btnCheckBox.setOnClickListener(listener);
        btnImageView.setOnClickListener(listener);
        btnListView.setOnClickListener(listener);
        btnGridView.setOnClickListener(listener);
        btnRecyclerView.setOnClickListener(listener);
        btnDatabase.setOnClickListener(listener);
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
                case R.id.btn_list_view:
                    intent = new Intent(TestActivity.this, ListViewActivity.class);
                    break;
                case R.id.btn_grid_view:
                    intent = new Intent(TestActivity.this, GridViewActivity.class);
                    break;
                case R.id.btn_recycler_view:
                    intent = new Intent(TestActivity.this, RecyclerViewActivity.class);
                    break;
                case R.id.btn_database_test:
                    intent = new Intent(TestActivity.this, UserActivity.class);
                    break;
                default:
                    intent = new Intent(TestActivity.this, TestActivity.class);
            }
            startActivity(intent);
        }
    }
}