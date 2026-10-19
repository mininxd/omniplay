package com.joaomgcd.taskerm.google.drive;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.joaomgcd.taskerm.net.v f7011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f7012b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final u f7013c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f7014d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f7015e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final boolean f7016f;

    public g(com.joaomgcd.taskerm.net.v vVar, String str, u uVar, boolean z, String str2, boolean z2) {
        c.f.b.k.b(vVar, "content");
        c.f.b.k.b(str, "fileName");
        c.f.b.k.b(uVar, "parent");
        c.f.b.k.b(str2, "contenName");
        this.f7011a = vVar;
        this.f7012b = str;
        this.f7013c = uVar;
        this.f7014d = z;
        this.f7015e = str2;
        this.f7016f = z2;
    }

    public /* synthetic */ g(com.joaomgcd.taskerm.net.v vVar, String str, u uVar, boolean z, String str2, boolean z2, int i, c.f.b.g gVar) {
        this(vVar, str, uVar, (i & 8) != 0 ? true : z, (i & 16) != 0 ? str : str2, (i & 32) != 0 ? false : z2);
    }

    public final com.joaomgcd.taskerm.net.v a() {
        return this.f7011a;
    }

    public final String b() {
        return this.f7012b;
    }

    public final u c() {
        return this.f7013c;
    }

    public final boolean d() {
        return this.f7014d;
    }

    public final String e() {
        return this.f7015e;
    }

    public final boolean f() {
        return this.f7016f;
    }
}
