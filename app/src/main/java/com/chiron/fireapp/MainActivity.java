package com.chiron.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chiron.fireapp.utils.ToastUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    //TODO Button继承TextView,TextView也是可以点击的
    private Button mBtnLogin;
    private EditText mEtUserName;
    private EditText mEtUserPass;
    private TextView mTvUserName;
    private TextView mTvUserPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        initBtn();
        initEt();
        initTv();
        initListener();
        onSetTextView();
    }
    private void initBtn(){
        mBtnLogin=findViewById(R.id.btn_login);
    }

    private void initEt(){
        mEtUserName=findViewById(R.id.user_name);
        mEtUserPass=findViewById(R.id.user_pass);
    }

    private void initTv(){
        mTvUserName=findViewById(R.id.tv_user_name);
        mTvUserPass=findViewById(R.id.tv_pass);
    }

    private void initListener(){
        onClickListener listener = new onClickListener();
        mBtnLogin.setOnClickListener(listener);
        mEtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG,"UserName:"+s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private class onClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_login:
                    onLogin();
                    break;
                default:
                    break;
            }
        }
    }

    private void onLogin(){
        String userName = mEtUserName.getText().toString();
        String userPass = mEtUserPass.getText().toString();
        String success = "登录成功";
        String fail = "用户名或者密码错误，请重新输入";
        if (userName.equals("jasper") && userPass.equals("123456")) {
            ToastUtil.showToast(getApplicationContext(), success);
            //定义从哪个activity跳到哪个activity
            Intent intent = new Intent(MainActivity.this, SlideActivity.class);
            //开始跳转
            startActivity(intent);
        } else {
            ToastUtil.showToastInCenter(getApplicationContext(), fail);
        }
    }

    private void onSetTextView(){
        mTvUserName.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
        mTvUserName.getPaint().setAntiAlias(true);//去除锯齿
        mTvUserName.setText(Html.fromHtml("<u>用户名：</u>"));//可以直接输出html效果<u>就是添加下划线

        mTvUserPass.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
    }

    public void showToast(View view){
        ToastUtil.showToast(MainActivity.this,"点击");
    }
}