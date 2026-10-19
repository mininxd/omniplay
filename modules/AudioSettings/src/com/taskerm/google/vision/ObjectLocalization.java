package com.joaomgcd.taskerm.google.vision;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import cyanogenmod.alarmclock.ClockContract;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "cv")
public final class ObjectLocalization {
    private final String bounds;
    private final Integer score;
    private final String value;

    public ObjectLocalization(String str, Integer num, String str2) {
        this.value = str;
        this.score = num;
        this.bounds = str2;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_label_description, labelResId = R.string.google_cloud_vision_label, name = ClockContract.AlarmSettingColumns.LABEL)
    public final String getValue() {
        return this.value;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_score_description, labelResId = R.string.google_cloud_vision_score, name = "score")
    public final Integer getScore() {
        return this.score;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_bounds_crop_hints_description, labelResId = R.string.google_cloud_vision_bounds, name = "bounds")
    public final String getBounds() {
        return this.bounds;
    }
}
