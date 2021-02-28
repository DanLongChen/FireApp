package com.chiron.fireapp.view;

import android.content.Context;
import android.util.AttributeSet;

public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {
    public MyTextView(Context context){
        super(context);
    }

    public MyTextView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //重写方法，强制返回true，获取到焦点
    @Override
    public boolean isFocused() {
        return true;
    }
}
