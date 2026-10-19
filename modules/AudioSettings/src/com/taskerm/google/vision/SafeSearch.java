package com.joaomgcd.taskerm.google.vision;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "cv")
public final class SafeSearch {
    private final int adult;
    private final int medical;
    private final int racy;
    private final int spoof;
    private final int violence;

    public SafeSearch(int i, int i2, int i3, int i4, int i5) {
        this.adult = i;
        this.spoof = i2;
        this.medical = i3;
        this.violence = i4;
        this.racy = i5;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_safesearch_likelyhood_description, labelResId = R.string.google_cloud_vision_adult, name = "adult")
    public final int getAdult() {
        return this.adult;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_safesearch_likelyhood_description, labelResId = R.string.google_cloud_vision_spoof, name = "spoof")
    public final int getSpoof() {
        return this.spoof;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_safesearch_likelyhood_description, labelResId = R.string.google_cloud_vision_medical, name = "medical")
    public final int getMedical() {
        return this.medical;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_safesearch_likelyhood_description, labelResId = R.string.google_cloud_vision_violence, name = "violence")
    public final int getViolence() {
        return this.violence;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_safesearch_likelyhood_description, labelResId = R.string.google_cloud_vision_racy, name = "racy")
    public final int getRacy() {
        return this.racy;
    }
}
