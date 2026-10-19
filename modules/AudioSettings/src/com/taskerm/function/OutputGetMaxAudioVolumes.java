package com.joaomgcd.taskerm.function;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class OutputGetMaxAudioVolumes {
    private final Integer accessibility;
    private final Integer alarm;
    private final Integer call;
    private final Integer dtmf;
    private final Integer media;
    private final Integer notification;
    private final Integer ring;
    private final Integer system;

    public OutputGetMaxAudioVolumes(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8) {
        this.media = num;
        this.alarm = num2;
        this.notification = num3;
        this.ring = num4;
        this.accessibility = num5;
        this.call = num6;
        this.system = num7;
        this.dtmf = num8;
    }

    @TaskerOutputVariable(labelResId = R.string.an_volume_music, name = "max_volume_media")
    public final Integer getMedia() {
        return this.media;
    }

    @TaskerOutputVariable(labelResId = R.string.an_volume_alarm, name = "max_volume_alarm")
    public final Integer getAlarm() {
        return this.alarm;
    }

    @TaskerOutputVariable(labelResId = R.string.an_volume_notification, name = "max_volume_notification")
    public final Integer getNotification() {
        return this.notification;
    }

    @TaskerOutputVariable(labelResId = R.string.an_volume_ringer, name = "max_volume_ring")
    public final Integer getRing() {
        return this.ring;
    }

    @TaskerOutputVariable(labelResId = R.string.an_accessibility_volume, name = "max_volume_accessibility")
    public final Integer getAccessibility() {
        return this.accessibility;
    }

    @TaskerOutputVariable(labelResId = R.string.an_volume_call, name = "max_volume_call")
    public final Integer getCall() {
        return this.call;
    }

    @TaskerOutputVariable(labelResId = R.string.an_volume_system, name = "max_volume_system")
    public final Integer getSystem() {
        return this.system;
    }

    @TaskerOutputVariable(labelResId = R.string.an_volume_dtmf, name = "max_volume_dtmf")
    public final Integer getDtmf() {
        return this.dtmf;
    }
}
