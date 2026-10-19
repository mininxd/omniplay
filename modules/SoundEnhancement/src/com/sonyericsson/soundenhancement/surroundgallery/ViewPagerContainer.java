package com.sonyericsson.soundenhancement.surroundgallery;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes2.dex */
public class ViewPagerContainer extends FrameLayout {
    private Point mCenter;
    private Point mInitialTouch;
    private ViewPager mPager;

    public ViewPagerContainer(Context context) {
        super(context);
        this.mCenter = new Point();
        this.mInitialTouch = new Point();
        init();
    }

    public ViewPagerContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCenter = new Point();
        this.mInitialTouch = new Point();
        init();
    }

    public ViewPagerContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCenter = new Point();
        this.mInitialTouch = new Point();
        init();
    }

    private void init() {
        setClipChildren(false);
        setLayerType(1, null);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        this.mPager = (ViewPager) getChildAt(0);
        super.onFinishInflate();
    }

    public ViewPager getViewPager() {
        return this.mPager;
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Point point = this.mCenter;
        point.x = w / 2;
        point.y = h / 2;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            this.mInitialTouch.x = (int) ev.getX();
            this.mInitialTouch.y = (int) ev.getY();
        }
        ev.offsetLocation(this.mCenter.x - this.mInitialTouch.x, this.mCenter.y - this.mInitialTouch.y);
        return this.mPager.dispatchTouchEvent(ev);
    }
}
