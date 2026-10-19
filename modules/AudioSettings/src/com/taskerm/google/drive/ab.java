package com.joaomgcd.taskerm.google.drive;

import cyanogenmod.app.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public final class ab extends z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6989a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f6990b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ab(String str, c cVar, boolean z) {
        super(z, null);
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        c.f.b.k.b(cVar, "argsFolder");
        this.f6989a = str;
        this.f6990b = cVar;
    }

    public /* synthetic */ ab(String str, c cVar, boolean z, int i, c.f.b.g gVar) {
        this(str, cVar, (i & 4) != 0 ? false : z);
    }

    public final String b() {
        return this.f6989a;
    }

    public final c c() {
        return this.f6990b;
    }

    public String toString() {
        return this.f6989a;
    }
}
