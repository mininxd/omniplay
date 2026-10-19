package com.joaomgcd.taskerm.google.language;

import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "nl")
public final class OutputClassifyText {
    private final String[] category;
    private final Integer[] score;

    public OutputClassifyText(String[] strArr, Integer[] numArr) {
        k.b(strArr, "category");
        k.b(numArr, "score");
        this.category = strArr;
        this.score = numArr;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_natural_language_category_name_description, labelResId = R.string.google_natural_language_category_name, name = "category")
    public final String[] getCategory() {
        return this.category;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_natural_language_category_score_description, labelResId = R.string.google_natural_language_category_score, name = "score")
    public final Integer[] getScore() {
        return this.score;
    }
}
