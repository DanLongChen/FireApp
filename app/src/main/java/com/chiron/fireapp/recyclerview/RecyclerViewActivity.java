package com.chiron.fireapp.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chiron.fireapp.R;

public class RecyclerViewActivity extends AppCompatActivity {
    private Button btnLinear, btnHor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        btnLinear = findViewById(R.id.btn_linear);
        btnHor = findViewById(R.id.btn_hor);
        btnLinear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerViewActivity.this, LinearRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
        btnHor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerViewActivity.this, HorRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
    }
}