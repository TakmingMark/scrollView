package com.example.markwang.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


public class CustomTextView  extends android.support.v7.widget.AppCompatTextView {
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("textView",event.getAction()+"");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       boolean b=super.onTouchEvent(event);
        Log.e("TextView","TouchEvent:"+b);
        return true;
    }


}
