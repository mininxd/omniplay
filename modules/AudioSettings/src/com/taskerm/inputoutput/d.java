package com.joaomgcd.taskerm.inputoutput;

import com.joaomgcd.taskerm.util.ak;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f7538a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f7539b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f7540c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f7541d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f7542e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f7543f;

    public d(String str, String str2, boolean z, int i, int i2, boolean z2) {
        c.f.b.k.b(str2, "nameNoSuffix");
        this.f7538a = str;
        this.f7539b = str2;
        this.f7540c = z;
        this.f7541d = i;
        this.f7542e = i2;
        this.f7543f = z2;
    }

    public /* synthetic */ d(String str, String str2, boolean z, int i, int i2, boolean z2, int i3, c.f.b.g gVar) {
        this(str, str2, (i3 & 4) != 0 ? false : z, (i3 & 8) != 0 ? -1 : i, (i3 & 16) != 0 ? Integer.MAX_VALUE : i2, (i3 & 32) != 0 ? false : z2);
    }

    public final void a(String str) {
        this.f7538a = str;
    }

    public final void a(boolean z) {
        this.f7543f = z;
    }

    public final void b(String str) {
        c.f.b.k.b(str, "<set-?>");
        this.f7539b = str;
    }

    public final String c() {
        return this.f7538a;
    }

    public final String d() {
        return this.f7539b;
    }

    public final boolean e() {
        return this.f7540c;
    }

    public final int f() {
        return this.f7541d;
    }

    public final int g() {
        return this.f7542e;
    }

    public final boolean h() {
        return this.f7543f;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0021  */
    public final String a() {
        String str;
        StringBuilder sb = new StringBuilder();
        String strA = ak.A(this.f7538a);
        if (strA != null) {
            str = strA + '_';
            if (str == null) {
                str = "";
            }
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(this.f7539b);
        return ak.a(sb.toString(), (String) null, false, 3, (Object) null);
    }

    public final String b() {
        String strA = a();
        if (!this.f7540c) {
            return strA;
        }
        return strA + "()";
    }
}
