package com.joaomgcd.taskerm.google.vision;

import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "cv")
public final class Logo {
    private final int score;
    private final String value;

    public Logo(String str, int i) {
        k.b(str, "value");
        this.value = str;
        this.score = i;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_logo_description, labelResId = R.string.google_cloud_vision_logo, name = "logo")
    public final String getValue() {
        return this.value;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_score_description, labelResId = R.string.google_cloud_vision_score, name = "score")
    public final int getScore() {
        return this.score;
    }
}
