package com.joaomgcd.taskerm.google.vision;

import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "cv")
public final class OCR {
    private final String score;
    private final String value;

    public OCR(String str, String str2) {
        k.b(str, "value");
        this.value = str;
        this.score = str2;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_text_description, labelResId = R.string.google_cloud_vision_text, name = "text")
    public final String getValue() {
        return this.value;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_language_description, labelResId = R.string.google_cloud_vision_language, name = "language")
    public final String getScore() {
        return this.score;
    }
}
