package com.joaomgcd.taskerm.tts.wavenet;

import c.f.b.k;
import cyanogenmod.app.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public final class f extends e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8796a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(String str) {
        super(null);
        k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        this.f8796a = str;
    }

    public final String a() {
        return this.f8796a;
    }
}
