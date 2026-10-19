package com.joaomgcd.taskerm.google.drive;

/* JADX INFO: loaded from: classes.dex */
public class v extends u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f7072a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l f7073b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(String str, l lVar) {
        super(null);
        c.f.b.k.b(lVar, "spaces");
        this.f7072a = str;
        this.f7073b = lVar;
    }

    @Override // com.joaomgcd.taskerm.google.drive.u
    public l a() {
        return this.f7073b;
    }

    public final String b() {
        return this.f7072a;
    }
}
