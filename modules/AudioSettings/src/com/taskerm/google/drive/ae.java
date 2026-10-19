package com.joaomgcd.taskerm.google.drive;

/* JADX INFO: loaded from: classes.dex */
public final class ae extends z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6996a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l f6997b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ae(String str, l lVar, boolean z) {
        super(z, null);
        c.f.b.k.b(str, "path");
        c.f.b.k.b(lVar, "spaces");
        this.f6996a = str;
        this.f6997b = lVar;
    }

    public /* synthetic */ ae(String str, l lVar, boolean z, int i, c.f.b.g gVar) {
        this(str, lVar, (i & 4) != 0 ? false : z);
    }

    public final String b() {
        return this.f6996a;
    }

    public final l c() {
        return this.f6997b;
    }

    public String toString() {
        return this.f6996a;
    }
}
