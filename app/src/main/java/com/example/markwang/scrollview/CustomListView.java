package com.example.markwang.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class CustomListView extends ListView {
    CustomLinearLayout itemRootLinearLayout;

    int lastX = 0;

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        int position = pointToPosition(x, y);
        if(position!=INVALID_POSITION){
            itemRootLinearLayout = ((DataHolder) getItemAtPosition(position)).itemRootLinearLayout;
            itemRootLinearLayout.dispatchTouchEvent(ev);
        }
        //need to dispatch a task to the child by myself, otherwise the child not sensitive
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

}
