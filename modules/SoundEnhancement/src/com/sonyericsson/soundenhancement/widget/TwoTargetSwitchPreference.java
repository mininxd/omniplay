package com.sonyericsson.soundenhancement.widget;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;
import com.sonyericsson.soundenhancement.R;

/* JADX INFO: loaded from: classes2.dex */
public class TwoTargetSwitchPreference extends Preference {
    private boolean mChecked;
    private boolean mEnableSwitch;
    private Preference.OnPreferenceClickListener mOnPreferenceClickListener;
    private Switch mSwitch;

    public TwoTargetSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mEnableSwitch = true;
        init(context);
    }

    public TwoTargetSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mEnableSwitch = true;
        init(context);
    }

    public TwoTargetSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mEnableSwitch = true;
        init(context);
    }

    public TwoTargetSwitchPreference(Context context) {
        super(context);
        this.mEnableSwitch = true;
        init(context);
    }

    private void init(Context context) {
        setLayoutResource(R.layout.two_target_switch);
        setWidgetLayoutResource(R.layout.preference_widget_switch);
    }

    @Override // android.preference.Preference
    public void setOnPreferenceClickListener(Preference.OnPreferenceClickListener listener) {
        this.mOnPreferenceClickListener = listener;
    }

    @Override // android.preference.Preference
    protected void onBindView(View view) {
        super.onBindView(view);
        View widgetView = view.findViewById(android.R.id.widget_frame);
        if (widgetView != null) {
            widgetView.setOnClickListener(new View.OnClickListener() { // from class: com.sonyericsson.soundenhancement.widget.TwoTargetSwitchPreference.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (TwoTargetSwitchPreference.this.mSwitch != null && !TwoTargetSwitchPreference.this.mSwitch.isEnabled()) {
                        return;
                    }
                    TwoTargetSwitchPreference twoTargetSwitchPreference = TwoTargetSwitchPreference.this;
                    twoTargetSwitchPreference.setChecked(!twoTargetSwitchPreference.mChecked);
                    TwoTargetSwitchPreference twoTargetSwitchPreference2 = TwoTargetSwitchPreference.this;
                    if (!twoTargetSwitchPreference2.callChangeListener(Boolean.valueOf(twoTargetSwitchPreference2.mChecked))) {
                        TwoTargetSwitchPreference twoTargetSwitchPreference3 = TwoTargetSwitchPreference.this;
                        twoTargetSwitchPreference3.setChecked(!twoTargetSwitchPreference3.mChecked);
                    } else {
                        TwoTargetSwitchPreference twoTargetSwitchPreference4 = TwoTargetSwitchPreference.this;
                        twoTargetSwitchPreference4.persistBoolean(twoTargetSwitchPreference4.mChecked);
                    }
                }
            });
        }
        this.mSwitch = (Switch) view.findViewById(R.id.switchWidget);
        Switch r1 = this.mSwitch;
        if (r1 != null) {
            r1.setContentDescription(getTitle());
            this.mSwitch.setChecked(this.mChecked);
            this.mSwitch.setEnabled(this.mEnableSwitch);
        }
        View iconTextView = view.findViewById(R.id.two_target_icon_text);
        iconTextView.setOnClickListener(new View.OnClickListener() { // from class: com.sonyericsson.soundenhancement.widget.TwoTargetSwitchPreference.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (TwoTargetSwitchPreference.this.mOnPreferenceClickListener != null) {
                    TwoTargetSwitchPreference.this.mOnPreferenceClickListener.onPreferenceClick(this);
                }
            }
        });
    }

    public boolean isChecked() {
        return this.mSwitch != null && this.mChecked;
    }

    public void setChecked(boolean checked) {
        this.mChecked = checked;
        Switch r0 = this.mSwitch;
        if (r0 != null) {
            r0.setChecked(checked);
        }
    }

    public Switch getSwitch() {
        return this.mSwitch;
    }
}
