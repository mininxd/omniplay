package com.joaomgcd.taskerm.action.input;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "gv")
public final class OutputGetVoiceSingle {
    private final int confidence;
    private final String heard;

    public OutputGetVoiceSingle(String str, int i) {
        c.f.b.k.b(str, "heard");
        this.heard = str;
        this.confidence = i;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.get_voice_heard_description, labelResId = R.string.word_heard, name = "heard")
    public final String getHeard() {
        return this.heard;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_score_description, labelResId = R.string.google_cloud_vision_confidence, name = "confidence")
    public final int getConfidence() {
        return this.confidence;
    }
}
