package com.joaomgcd.taskerm.securesettings;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final r f8553a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final r f8554b;

    public s(r rVar, r rVar2) {
        c.f.b.k.b(rVar, "old");
        c.f.b.k.b(rVar2, "new");
        this.f8553a = rVar;
        this.f8554b = rVar2;
    }

    public final r a() {
        return this.f8553a;
    }

    public final r b() {
        return this.f8554b;
    }

    public String toString() {
        return '\'' + this.f8553a.c() + "': from '" + this.f8553a.h() + "' to '" + this.f8554b.h() + '\'';
    }
}
