package com.joaomgcd.taskerm.action.input;

import cyanogenmod.app.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public final class bd extends bc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f5471a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f5472b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bd(String str) {
        super(0.0f, 1, null);
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        this.f5472b = str;
        this.f5471a = this.f5472b;
    }

    @Override // com.joaomgcd.taskerm.action.input.bc
    public String b() {
        return this.f5471a;
    }

    public String toString() {
        return b();
    }
}
