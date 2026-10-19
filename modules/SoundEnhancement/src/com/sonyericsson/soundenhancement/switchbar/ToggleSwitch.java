package com.sonyericsson.soundenhancement.switchbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Switch;

/* JADX INFO: loaded from: classes2.dex */
public class ToggleSwitch extends Switch {
    private OnBeforeCheckedChangeListener mOnBeforeListener;

    public interface OnBeforeCheckedChangeListener {
        boolean onBeforeCheckedChanged(ToggleSwitch toggleSwitch, boolean z);
    }

    public ToggleSwitch(Context context) {
        super(context);
    }

    public ToggleSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToggleSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ToggleSwitch(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setOnBeforeCheckedChangeListener(OnBeforeCheckedChangeListener listener) {
        this.mOnBeforeListener = listener;
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean checked) {
        OnBeforeCheckedChangeListener onBeforeCheckedChangeListener = this.mOnBeforeListener;
        if (onBeforeCheckedChangeListener != null && onBeforeCheckedChangeListener.onBeforeCheckedChanged(this, checked)) {
            return;
        }
        super.setChecked(checked);
    }

    public void setCheckedInternal(boolean checked) {
        super.setChecked(checked);
    }
}
