package com.joaomgcd.taskerm.google.vision;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "cv")
public final class WebDetectionPage {
    private final Integer score;
    private final String title;
    private final String url;

    public WebDetectionPage(String str, Integer num, String str2) {
        this.title = str;
        this.score = num;
        this.url = str2;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_title_description, labelResId = R.string.google_cloud_vision_title, name = "title")
    public final String getTitle() {
        return this.title;
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
