package com.joaomgcd.taskerm.google.language;

import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "nl")
public final class OutputAnalyzeSentiment {
    private final int generalSentiment;
    private final Integer[] sentenceSentiment;
    private final String[] sentenceText;

    public OutputAnalyzeSentiment(int i, String[] strArr, Integer[] numArr) {
        k.b(strArr, "sentenceText");
        k.b(numArr, "sentenceSentiment");
        this.generalSentiment = i;
        this.sentenceText = strArr;
        this.sentenceSentiment = numArr;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_natural_language_general_sentiment_description, labelResId = R.string.google_natural_language_general_sentiment, name = "general_sentiment")
    public final int getGeneralSentiment() {
        return this.generalSentiment;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_natural_language_sentece_text_description, labelResId = R.string.google_natural_language_sentece_text, name = "sentence_text")
    public final String[] getSentenceText() {
        return this.sentenceText;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_natural_language_general_sentiment_description, labelResId = R.string.google_natural_language_sentece_sentiment, name = "sentence_sentiment")
    public final Integer[] getSentenceSentiment() {
        return this.sentenceSentiment;
    }
}
