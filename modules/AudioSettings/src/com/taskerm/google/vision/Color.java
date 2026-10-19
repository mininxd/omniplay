package com.joaomgcd.taskerm.google.vision;

import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "cv")
public final class Color {
    private final int pixelFractionPercentage;
    private final int score;
    private final String value;

    public Color(String str, int i, int i2) {
        k.b(str, "value");
        this.value = str;
        this.score = i;
        this.pixelFractionPercentage = i2;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_color_description, labelResId = R.string.google_cloud_vision_color, name = "color")
    public final String getValue() {
        return this.value;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_score_description, labelResId = R.string.google_cloud_vision_score, name = "score")
    public final int getScore() {
        return this.score;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_percentage_description, labelResId = R.string.google_cloud_vision_percentage, name = "percentage")
    public final int getPixelFractionPercentage() {
        return this.pixelFractionPercentage;
    }
}
