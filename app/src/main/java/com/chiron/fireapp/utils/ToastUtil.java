package com.chiron.fireapp.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {
    private static Toast mToast;
    public static void showToast(Context context, String msg){
        if(mToast==null){
            mToast=Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
        }
        //普通版toast，注意，没有show是弹不出来的
        mToast.show();
    }
    public static void showToastInCenter(Context context,String msg){
        if(mToast==null){
            mToast=Toast.makeText(context,msg,Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER,0,0);
        }else{
            mToast.setText(msg);
            mToast.setGravity(Gravity.CENTER,0,0);
        }
        mToast.show();
    }
}
