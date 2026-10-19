package com.joaomgcd.taskerm.google.vision;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "cv")
public final class WebDetectionImage {
    private final Integer score;
    private final String url;

    public WebDetectionImage(Integer num, String str) {
        this.score = num;
        this.url = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_score_description, labelResId = R.string.google_cloud_vision_score, name = "score")
    public final Integer getScore() {
        return this.score;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_url_description, labelResId = R.string.google_cloud_vision_url, name = "url")
    public final String getUrl() {
        return this.url;
    }
}
