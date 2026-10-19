package com.joaomgcd.taskerm.dialog;

import cyanogenmod.app.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6400a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final net.dinglisch.android.taskerm.g f6401b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f6402c;

    public w(String str, net.dinglisch.android.taskerm.g gVar, boolean z) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        this.f6400a = str;
        this.f6401b = gVar;
        this.f6402c = z;
    }

    public /* synthetic */ w(String str, net.dinglisch.android.taskerm.g gVar, boolean z, int i, c.f.b.g gVar2) {
        this(str, (i & 2) != 0 ? (net.dinglisch.android.taskerm.g) null : gVar, (i & 4) != 0 ? false : z);
    }

    public final String a() {
        return this.f6400a;
    }

    public final net.dinglisch.android.taskerm.g b() {
        return this.f6401b;
    }

    public final boolean c() {
        return this.f6402c;
    }
}
