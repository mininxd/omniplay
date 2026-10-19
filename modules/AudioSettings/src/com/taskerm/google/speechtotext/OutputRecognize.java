package com.joaomgcd.taskerm.google.speechtotext;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "stt")
public final class OutputRecognize {
    private final String transcription;

    public OutputRecognize(String str) {
        this.transcription = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_speech_to_text_transcription_description, labelResId = R.string.google_speech_to_text_transcription, name = "transcription")
    public final String getTranscription() {
        return this.transcription;
    }
}
