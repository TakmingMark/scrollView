package com.example.markwang.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class CustomLinearLayout extends LinearLayout {
    Context context;
    private Scroller scroller;
    private int lastX;
    CustomOnScrollListener customOnScrollListener;

    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        scroller = new Scroller(context, new LinearInterpolator(context, null));
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        scroller = new Scroller(context, new LinearInterpolator(context, null));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("linearLayout","dispatch");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("linearLayout","onTouch:"+event.getAction());
        int maxLength = dipToPx(context,200);
        int x = (int) event.getX();
        Log.e("linearLayout","x:"+x);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int scrollX = this.getScrollX();
            scroller.startScroll(scrollX, 0, 0 - scrollX, 0);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

            int scrollX = this.getScrollX();
            int newScrollX = scrollX + lastX - x;
            Log.e("linearLayout","newScrollX:"+x);
            newScrollX = newScrollX < 0 ? 0 : newScrollX;
            newScrollX = newScrollX > maxLength ? maxLength : newScrollX;
            this.scrollTo(newScrollX, 0);

        } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            int scrollX = this.getScrollX();

            if (scrollX > maxLength / 2) {
                scroller.startScroll(scrollX, 0, maxLength - scrollX, 0, 500);
                customOnScrollListener.onScroll(this);
            } else
                scroller.startScroll(scrollX, 0, -scrollX, 0, 500);
            invalidate();
        }

        lastX = x;
        return true;
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset() && this != null)
            this.scrollTo(scroller.getCurrX(), scroller.getCurrY());
        invalidate();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean b=super.onInterceptTouchEvent(ev);
        Log.e("linearLayout",b+"");
        return b;
    }

    public void setCustomOnScrollListener(CustomOnScrollListener customOnScrollListener) {
        this.customOnScrollListener = customOnScrollListener;
    }

    public void revertScroll() {
        int scrollX = this.getScrollX();
        scroller.startScroll(scrollX, 0, 0 - scrollX, 0);
    }

    private int dipToPx(Context context, int dip) {
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }

}

