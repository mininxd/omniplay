package com.joaomgcd.taskerm.google.drive;

/* JADX INFO: loaded from: classes.dex */
public final class n extends m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f7066a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l f7067b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(String str, l lVar) {
        super(null);
        c.f.b.k.b(str, "folderId");
        c.f.b.k.b(lVar, "spaces");
        this.f7066a = str;
        this.f7067b = lVar;
    }

    @Override // com.joaomgcd.taskerm.google.drive.m
    public l a() {
        return this.f7067b;
    }

    public final String b() {
        return this.f7066a;
    }
}
