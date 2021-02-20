package com.chiron.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mBtnLogin;
    private EditText mEtUserName;
    private EditText mEtUserPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        initBtn();
        initEt();
        initListener();
    }
    private void initBtn(){
        mBtnLogin=findViewById(R.id.btn_login);
    }

    private void initEt(){
        mEtUserName=findViewById(R.id.user_name);
        mEtUserPass=findViewById(R.id.user_pass);
    }

    private void initListener(){
        mBtnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Intent代表意图
                MainActivity.this.onClick(v);
            }
        });
    }

    private void onClick(View v){
        String userName=mEtUserName.getText().toString();
        String userPass=mEtUserPass.getText().toString();
        if(userName.equals("jasper") && userPass.equals("123456")){
            Intent intent=new Intent(MainActivity.this,FuncActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this,"账号或密码错误",Toast.LENGTH_LONG);
        }
    }
}