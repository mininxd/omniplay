package com.sonyericsson.soundenhancement.seekbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import com.sonyericsson.soundenhancement.R;

/* JADX INFO: loaded from: classes2.dex */
public class HorizontalSeekBar extends SeekBar {
    public HorizontalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == 32768) {
            onPopulateAccessibilityEvent(event);
            return true;
        }
        return super.dispatchPopulateAccessibilityEvent(event);
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == 32768) {
            event.getText().add(getContext().getResources().getString(R.string.sound_enhance_strings_accessibility_clear_bass_txt));
        }
        super.onPopulateAccessibilityEvent(event);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(null);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(null);
    }
}
