package com.joaomgcd.taskerm.state.sensor;

import android.content.Context;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "sl")
public final class OutputSleeping {
    private final int confidence;
    private final int light;
    private final int motion;

    public OutputSleeping(Context context, d dVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(dVar, "sleepingEvent");
        this.confidence = dVar.a();
        this.light = dVar.b();
        this.motion = dVar.c();
    }

    @TaskerOutputVariable(labelResId = R.string.google_cloud_vision_confidence, name = "confidence")
    public final int getConfidence() {
        return this.confidence;
    }

    @TaskerOutputVariable(labelResId = R.string.word_light, name = "light")
    public final int getLight() {
        return this.light;
    }

    @TaskerOutputVariable(labelResId = R.string.word_motion, name = "motion")
    public final int getMotion() {
        return this.motion;
    }
}
