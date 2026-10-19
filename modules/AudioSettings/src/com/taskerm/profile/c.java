package com.joaomgcd.taskerm.profile;

import c.q;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import net.dinglisch.android.taskerm.df;
import net.dinglisch.android.taskerm.dg;

/* JADX INFO: loaded from: classes.dex */
public final class c implements df {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f8229a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private transient h f8230b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f8231c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f8232d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f8233e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f8234f;
    private String g;
    private String h;
    private final String i;
    private final boolean j;
    private final boolean k;
    private final String l;
    private final boolean m;
    private final boolean n;

    public c(String str, int i, String str2, String str3, String str4, String str5, String str6, boolean z, boolean z2, String str7, boolean z3, boolean z4) {
        c.f.b.k.b(str, "importableTypeId");
        c.f.b.k.b(str2, "variableName");
        c.f.b.k.b(str3, "displayName");
        c.f.b.k.b(str4, "_value");
        c.f.b.k.b(str5, "description");
        c.f.b.k.b(str6, "typeId");
        this.f8231c = str;
        this.f8232d = i;
        this.f8233e = str2;
        this.f8234f = str3;
        this.g = str4;
        this.h = str5;
        this.i = str6;
        this.j = z;
        this.k = z2;
        this.l = str7;
        this.m = z3;
        this.n = z4;
        a(this.g);
    }

    public final String h() {
        return this.f8233e;
    }

    public final String i() {
        return this.f8234f;
    }

    public final String j() {
        return this.h;
    }

    public final String k() {
        return this.i;
    }

    public final boolean l() {
        return this.j;
    }

    public final boolean m() {
        return this.k;
    }

    public final String n() {
        return this.l;
    }

    public final boolean o() {
        return this.m;
    }

    public final boolean p() {
        return this.n;
    }

    public final String a() {
        return this.f8234f;
    }

    public final String b() {
        return this.h;
    }

    public final String c() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f8233e);
        sb.append(':');
        sb.append(e().length() == 0 ? "not set" : "has value");
        return sb.toString();
    }

    private final h q() {
        h hVar = this.f8230b;
        if (hVar == null) {
            hVar = new h(this.f8231c, this.f8232d, this.f8233e, this.n);
        }
        this.f8230b = hVar;
        return hVar;
    }

    public final String d() {
        return this.g;
    }

    public final String e() {
        String strA = q().a();
        return strA != null ? strA : this.g;
    }

    public final void a(String str) {
        c.f.b.k.b(str, "toSet");
        this.g = str;
        q().a(str);
    }

    public final void f() {
        q().c();
    }

    public final d g() {
        return q().b();
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        private final boolean a(String str, dg dgVar, boolean z) {
            return dgVar.c(str) ? dgVar.d(str) : z;
        }

        public final c a(dg dgVar) {
            String strK;
            String strK2;
            c.f.b.k.b(dgVar, "packedObject");
            String strK3 = dgVar.k("pvit");
            if (strK3 == null) {
                return null;
            }
            int iF = dgVar.f("pvid");
            String strK4 = dgVar.k("pvn");
            if (strK4 == null || (strK = dgVar.k("pvdn")) == null) {
                return null;
            }
            a aVar = this;
            boolean zA = aVar.a("immutable", dgVar, false);
            String strA = new h(strK3, iF, strK4, zA).a();
            if (strA == null) {
                strA = dgVar.k("pvv");
            }
            if (strA == null) {
                strA = "";
            }
            String strK5 = dgVar.k("pvd");
            if (strK5 == null || (strK2 = dgVar.k("pvt")) == null) {
                return null;
            }
            boolean zA2 = aVar.a("pvci", dgVar, true);
            return new c(strK3, iF, strK4, strK, strA, strK5, strK2, zA2, aVar.a("strout", dgVar, false), dgVar.k("exportval"), aVar.a("clearout", dgVar, !zA2), zA);
        }
    }

    @Override // net.dinglisch.android.taskerm.df
    public dg a(int i) {
        dg dgVar = new dg("ProfileVariable", 1);
        dgVar.c("pvit", this.f8231c);
        dgVar.c("pvid", this.f8232d);
        dgVar.c("pvn", this.f8233e);
        dgVar.c("pvdn", this.f8234f);
        dgVar.c("pvd", this.h);
        dgVar.c("pvt", this.i);
        dgVar.b("pvci", this.j);
        dgVar.b("strout", this.k);
        String str = this.l;
        if (str == null) {
            str = "";
        }
        dgVar.c("exportval", str);
        dgVar.b("clearout", this.m);
        dgVar.b("immutable", this.n);
        String strA = ak.A((!aj.b(i, 2) || this.m) ? e() : this.l);
        if (strA != null) {
            dgVar.c("pvv", strA);
        }
        return dgVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!c.f.b.k.a(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            return !(c.f.b.k.a((Object) this.f8233e, (Object) ((c) obj).f8233e) ^ true);
        }
        throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.profile.ProfileVariable");
    }

    public int hashCode() {
        return this.f8233e.hashCode();
    }
}
