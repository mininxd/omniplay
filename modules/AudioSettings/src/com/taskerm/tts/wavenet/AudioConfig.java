package com.joaomgcd.taskerm.tts.wavenet;

import androidx.annotation.Keep;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class AudioConfig {
    private final AudioEncoding audioEncoding;
    private final Float pitch;
    private final Integer sampleRateHertz;
    private final Float speakingRate;
    private final Integer volumeGainDb;

    public AudioConfig(AudioEncoding audioEncoding, Float f2, Float f3, Integer num, Integer num2) {
        k.b(audioEncoding, "audioEncoding");
        this.audioEncoding = audioEncoding;
        this.pitch = f2;
        this.speakingRate = f3;
        this.volumeGainDb = num;
        this.sampleRateHertz = num2;
    }

    public /* synthetic */ AudioConfig(AudioEncoding audioEncoding, Float f2, Float f3, Integer num, Integer num2, int i, c.f.b.g gVar) {
        this(audioEncoding, (i & 2) != 0 ? (Float) null : f2, (i & 4) != 0 ? (Float) null : f3, (i & 8) != 0 ? (Integer) null : num, (i & 16) != 0 ? (Integer) null : num2);
    }

    public final AudioEncoding getAudioEncoding() {
        return this.audioEncoding;
    }

    public final Float getPitch() {
        return this.pitch;
    }

    public final Integer getSampleRateHertz() {
        return this.sampleRateHertz;
    }

    public final Float getSpeakingRate() {
        return this.speakingRate;
    }

    public final Integer getVolumeGainDb() {
        return this.volumeGainDb;
    }
}
