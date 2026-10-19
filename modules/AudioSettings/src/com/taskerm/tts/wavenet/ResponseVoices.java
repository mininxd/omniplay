package com.joaomgcd.taskerm.tts.wavenet;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class ResponseVoices {
    private final Voice[] voices;

    /* JADX WARN: Multi-variable type inference failed */
    public ResponseVoices() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ResponseVoices(Voice[] voiceArr) {
        this.voices = voiceArr;
    }

    public /* synthetic */ ResponseVoices(Voice[] voiceArr, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (Voice[]) null : voiceArr);
    }

    public final Voice[] getVoices() {
        return this.voices;
    }
}
