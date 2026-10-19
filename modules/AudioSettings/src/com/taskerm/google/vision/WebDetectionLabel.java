package com.joaomgcd.taskerm.google.vision;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import cyanogenmod.alarmclock.ClockContract;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "cv")
public final class WebDetectionLabel {
    private final String value;

    public WebDetectionLabel(String str) {
        this.value = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_label_description, labelResId = R.string.google_cloud_vision_label, name = ClockContract.AlarmSettingColumns.LABEL)
    public final String getValue() {
        return this.value;
    }
}
