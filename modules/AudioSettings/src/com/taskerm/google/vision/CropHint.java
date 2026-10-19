package com.joaomgcd.taskerm.google.vision;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "cv")
public final class CropHint {
    private final String bounds;
    private final int confidence;
    private final int importanceFraction;

    public CropHint(String str, int i, int i2) {
        this.bounds = str;
        this.confidence = i;
        this.importanceFraction = i2;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_bounds_crop_hints_description, labelResId = R.string.google_cloud_vision_bounds, name = "bounds")
    public final String getBounds() {
        return this.bounds;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_score_description, labelResId = R.string.google_cloud_vision_confidence, name = "confidence")
    public final int getConfidence() {
        return this.confidence;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_percentage_crop_hints_description, labelResId = R.string.google_cloud_vision_percentage, name = "percentage")
    public final int getImportanceFraction() {
        return this.importanceFraction;
    }
}
