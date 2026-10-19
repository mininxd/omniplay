package com.joaomgcd.taskerm.google.drive;

import cyanogenmod.app.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public final class ac extends z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f6992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final l f6993c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ac(String str, String str2, l lVar, boolean z) {
        super(z, null);
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        c.f.b.k.b(lVar, "spaces");
        this.f6991a = str;
        this.f6992b = str2;
        this.f6993c = lVar;
    }

    public /* synthetic */ ac(String str, String str2, l lVar, boolean z, int i, c.f.b.g gVar) {
        this(str, str2, lVar, (i & 8) != 0 ? false : z);
    }

    public final String b() {
        return this.f6991a;
    }

    public final String c() {
        return this.f6992b;
    }

    public final l d() {
        return this.f6993c;
    }

    public String toString() {
        return this.f6991a;
    }
}
