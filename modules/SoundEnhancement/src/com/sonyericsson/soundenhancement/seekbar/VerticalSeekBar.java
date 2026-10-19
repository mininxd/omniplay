package com.sonyericsson.soundenhancement.seekbar;

import android.R;
import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes2.dex */
public class VerticalSeekBar extends AbsVerticalSeekBar {
    private static final long HAPTICS_THRESHOLD = 100;
    private static final int SLIDER = 100;
    private long mElapsedTime;
    private boolean mIsTracking;
    private OnSeekBarChangeListener mOnSeekBarChangeListener;

    public interface OnSeekBarChangeListener {
        void onProgressChanged(VerticalSeekBar verticalSeekBar, int i, boolean z);

        void onStartTrackingTouch(VerticalSeekBar verticalSeekBar);

        void onStopTrackingTouch(VerticalSeekBar verticalSeekBar);
    }

    public VerticalSeekBar(Context context) {
        this(context, null);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.seekBarStyle);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mIsTracking = false;
        this.mElapsedTime = 0L;
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.AbsVerticalSeekBar, com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar
    void onProgressRefresh(float scale, boolean fromUser) {
        super.onProgressRefresh(scale, fromUser);
        OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onProgressChanged(this, getProgress(), fromUser);
        }
        if (this.mIsTracking) {
            if (SystemClock.uptimeMillis() - this.mElapsedTime > HAPTICS_THRESHOLD) {
                performHapticFeedback(100);
            }
            this.mElapsedTime = SystemClock.uptimeMillis();
        }
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener l) {
        this.mOnSeekBarChangeListener = l;
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.AbsVerticalSeekBar
    void onStartTrackingTouch() {
        super.onStartTrackingTouch();
        this.mIsTracking = true;
        OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStartTrackingTouch(this);
        }
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.AbsVerticalSeekBar
    void onStopTrackingTouch() {
        super.onStopTrackingTouch();
        this.mIsTracking = false;
        OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStopTrackingTouch(this);
        }
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.AbsVerticalSeekBar, com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar, android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(VerticalSeekBar.class.getName());
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.AbsVerticalSeekBar, com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(VerticalSeekBar.class.getName());
    }
}
