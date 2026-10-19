package com.joaomgcd.taskerm.securesettings;

/* JADX INFO: loaded from: classes.dex */
public class r extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f8551a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f8552b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(p pVar, String str, String str2, boolean z, int i, int i2) {
        super(pVar, str, z, i, i2);
        c.f.b.k.b(pVar, "type");
        c.f.b.k.b(str, "key");
        c.f.b.k.b(str2, "value");
        this.f8552b = str2;
        this.f8551a = c.f.b.k.a((Object) this.f8552b, (Object) "0") || c.f.b.k.a((Object) this.f8552b, (Object) "1");
    }

    public /* synthetic */ r(p pVar, String str, String str2, boolean z, int i, int i2, int i3, c.f.b.g gVar) {
        this(pVar, str, str2, z, (i3 & 16) != 0 ? 0 : i, (i3 & 32) != 0 ? Integer.MAX_VALUE : i2);
    }

    public final void a(String str) {
        c.f.b.k.b(str, "<set-?>");
        this.f8552b = str;
    }

    public final String h() {
        return this.f8552b;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public r(f fVar, String str) {
        this(fVar.b(), fVar.c(), str, fVar.d(), fVar.e(), fVar.f());
        c.f.b.k.b(fVar, "secureSetting");
        c.f.b.k.b(str, "value");
    }

    public String toString() {
        return c() + ": " + this.f8552b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return c.f.b.k.a((Object) rVar.c(), (Object) c()) && c.f.b.k.a((Object) rVar.f8552b, (Object) this.f8552b) && rVar.b() == b();
    }

    public int hashCode() {
        return (b().hashCode() ^ c().hashCode()) ^ this.f8552b.hashCode();
    }

    public final boolean g() {
        return this.f8551a;
    }
}
