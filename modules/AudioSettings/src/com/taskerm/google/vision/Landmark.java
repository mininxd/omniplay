package com.joaomgcd.taskerm.google.vision;

import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "cv")
public final class Landmark {
    private final String location;
    private final int score;
    private final String value;

    public Landmark(String str, int i, String str2) {
        k.b(str, "value");
        this.value = str;
        this.score = i;
        this.location = str2;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_landmark_description, labelResId = R.string.google_cloud_vision_landmark, name = "landmark")
    public final String getValue() {
        return this.value;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_score_description, labelResId = R.string.google_cloud_vision_score, name = "score")
    public final int getScore() {
        return this.score;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_location_description, labelResId = R.string.google_cloud_vision_location, name = "location")
    public final String getLocation() {
        return this.location;
    }
}
