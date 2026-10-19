package com.joaomgcd.taskerm.location;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f7659a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Boolean f7660b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final h f7661c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final MapType f7662d;

    public b() {
        this(null, null, null, null, 15, null);
    }

    public b(String str, Boolean bool, h hVar, MapType mapType) {
        this.f7659a = str;
        this.f7660b = bool;
        this.f7661c = hVar;
        this.f7662d = mapType;
    }

    public /* synthetic */ b(String str, Boolean bool, h hVar, MapType mapType, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (Boolean) null : bool, (i & 4) != 0 ? (h) null : hVar, (i & 8) != 0 ? (MapType) null : mapType);
    }

    public final String a() {
        return this.f7659a;
    }

    public final Boolean b() {
        return this.f7660b;
    }

    public final h c() {
        return this.f7661c;
    }

    public final MapType d() {
        return this.f7662d;
    }
}
