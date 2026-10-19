package com.joaomgcd.taskerm.tts.wavenet;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class ResponseSynthesize {
    private final String audioContent;

    /* JADX WARN: Multi-variable type inference failed */
    public ResponseSynthesize() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ResponseSynthesize(String str) {
        this.audioContent = str;
    }

    public /* synthetic */ ResponseSynthesize(String str, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str);
    }

    public final String getAudioContent() {
        return this.audioContent;
    }
}
