package com.joaomgcd.taskerm.tts.wavenet;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class Input {
    private final String ssml;
    private final String text;

    public Input(String str, String str2) {
        this.text = str;
        this.ssml = str2;
    }

    public final String getSsml() {
        return this.ssml;
    }

    public final String getText() {
        return this.text;
    }
}
