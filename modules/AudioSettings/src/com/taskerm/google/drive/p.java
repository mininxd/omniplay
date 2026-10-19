package com.joaomgcd.taskerm.google.drive;

/* JADX INFO: loaded from: classes.dex */
public final class p extends m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f7070a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l f7071b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(String str, l lVar) {
        super(null);
        c.f.b.k.b(str, "query");
        c.f.b.k.b(lVar, "spaces");
        this.f7070a = str;
        this.f7071b = lVar;
    }

    @Override // com.joaomgcd.taskerm.google.drive.m
    public l a() {
        return this.f7071b;
    }

    public final String b() {
        return this.f7070a;
    }
}
