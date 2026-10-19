package com.joaomgcd.taskerm.tts.wavenet;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8812a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Voice[] f8813b;

    public i(String str, Voice[] voiceArr) {
        k.b(str, "languageCode");
        k.b(voiceArr, "variations");
        this.f8812a = str;
        this.f8813b = voiceArr;
    }

    public final Voice[] a() {
        return this.f8813b;
    }
}
