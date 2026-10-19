package com.joaomgcd.taskerm.tts.wavenet;

import androidx.annotation.Keep;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class RequestSynthesize {
    private final AudioConfig audioConfig;
    private final Input input;
    private final Voice voice;

    public RequestSynthesize(AudioConfig audioConfig, Input input, Voice voice) {
        k.b(audioConfig, "audioConfig");
        k.b(input, "input");
        k.b(voice, "voice");
        this.audioConfig = audioConfig;
        this.input = input;
        this.voice = voice;
    }

    public final AudioConfig getAudioConfig() {
        return this.audioConfig;
    }

    public final Input getInput() {
        return this.input;
    }

    public final Voice getVoice() {
        return this.voice;
    }
}
