package com.example.markwang.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class CustomLinearLayout extends LinearLayout {
    private Scroller scroller;
    private int lastX;
    CustomOnScrollListener customOnScrollListener;

    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context, new LinearInterpolator(context, null));
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context, new LinearInterpolator(context, null));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int maxLength = 200;// dp=px=160
        int x = (int) event.getX();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int scrollX = this.getScrollX();
            scroller.startScroll(scrollX, 0, 0 - scrollX, 0);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

            int scrollX = this.getScrollX();
            int newScrollX = scrollX + lastX - x;
            newScrollX = newScrollX < 0 ? 0 : newScrollX;
            newScrollX = newScrollX > maxLength ? maxLength : newScrollX;
            this.scrollTo(newScrollX, 0);

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            int scrollX = this.getScrollX();

            if (scrollX > maxLength / 2) {
                scroller.startScroll(scrollX, 0, maxLength - scrollX, 0, 500);
                customOnScrollListener.onScroll(this);
            } else
                scroller.startScroll(scrollX, 0, -scrollX, 0, 500);
            invalidate();
        }

        lastX = x;
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset() && this != null)
            this.scrollTo(scroller.getCurrX(), scroller.getCurrY());
        invalidate();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }

    public void setCustomOnScrollListener(CustomOnScrollListener customOnScrollListener) {
        this.customOnScrollListener = customOnScrollListener;
    }

    public void revertScroll() {
        int scrollX = this.getScrollX();
        scroller.startScroll(scrollX, 0, 0 - scrollX, 0);
    }
}

