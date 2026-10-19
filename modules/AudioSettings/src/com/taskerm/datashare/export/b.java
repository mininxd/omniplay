package com.joaomgcd.taskerm.datashare.export;

import c.f.b.k;
import c.s;

/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6115a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.f.a.a<s> f6116b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.f.a.a<s> f6117c;

    public b(String str, c.f.a.a<s> aVar, c.f.a.a<s> aVar2) {
        k.b(str, "text");
        k.b(aVar, "action");
        this.f6115a = str;
        this.f6116b = aVar;
        this.f6117c = aVar2;
    }

    public final String a() {
        return this.f6115a;
    }

    public final c.f.a.a<s> b() {
        return this.f6116b;
    }

    public /* synthetic */ b(String str, c.f.a.a aVar, c.f.a.a aVar2, int i, c.f.b.g gVar) {
        this(str, aVar, (i & 4) != 0 ? (c.f.a.a) null : aVar2);
    }

    public final c.f.a.a<s> c() {
        return this.f6117c;
    }
}
