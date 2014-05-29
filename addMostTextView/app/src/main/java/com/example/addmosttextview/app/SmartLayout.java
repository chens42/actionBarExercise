package com.example.addmosttextview.app;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

public class SmartLayout extends ViewGroup {
    public SmartLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartLayout(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left=l;
        int top=t;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int measuredWidth = child.getMeasuredWidth();
            int measuredHeight = child.getMeasuredHeight();
            child.layout(50,50,200,200);
            Log.e("measureWidth ",measuredWidth+":"+measuredHeight);

            if(left+measuredWidth<r){
                int right = left+measuredWidth;
                int bottom= top+measuredHeight;
                child.layout(left,top,right,bottom);
                left=right;
            }else{
                int right=l+measuredWidth;
                child.layout(l,top+measuredHeight,l+measuredWidth,t+measuredHeight+measuredHeight);
                i=i-1;
                left=l;
                top=top+measuredHeight;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      int childCount= getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int measureWidth = child.getMeasuredWidth();
            int measureHeight = child.getMeasuredHeight();
            setMeasuredDimension(measureWidth, measureHeight);
        }

    }
}
