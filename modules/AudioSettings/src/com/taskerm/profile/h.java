package com.joaomgcd.taskerm.profile;

import android.content.Context;
import android.content.SharedPreferences;
import c.f.b.p;
import c.f.b.x;
import c.l.n;
import c.q;
import c.s;
import com.joaomgcd.taskerm.util.df;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8237a = {x.a(new p(x.a(h.class), "storedValue", "getStoredValue()Ljava/lang/String;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f8238b = new a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.joaomgcd.taskerm.q.h f8239c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f8240d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f8241e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f8242f;
    private boolean g;

    public static final void a(Context context) {
        f8238b.a(context);
    }

    private final void b(String str) {
        this.f8239c.a(this, f8237a[0], str);
    }

    private final String h() {
        return this.f8239c.a(this, f8237a[0]);
    }

    public h(String str, int i, String str2, boolean z) {
        c.f.b.k.b(str, "importableTypeId");
        c.f.b.k.b(str2, "variableName");
        this.f8240d = str;
        this.f8241e = i;
        this.f8242f = str2;
        this.g = z;
        Context contextG = g();
        String strD = d();
        SharedPreferences sharedPreferencesB = k.b(g());
        c.f.b.k.a((Object) sharedPreferencesB, "context.preferencesProfileVariablesStored");
        this.f8239c = new com.joaomgcd.taskerm.q.h(contextG, null, null, strD, sharedPreferencesB, 6, null);
    }

    public final String e() {
        return this.f8242f;
    }

    public final boolean f() {
        return this.g;
    }

    private final Context g() {
        return df.a("ProfileVariable");
    }

    public final String a() {
        return h();
    }

    public final void a(String str) {
        if (str == null) {
            str = "";
        }
        b(str);
    }

    public final d b() {
        return new d(this.f8240d, this.f8241e);
    }

    public final void c() {
        b((String) null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!c.f.b.k.a(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            h hVar = (h) obj;
            return ((c.f.b.k.a((Object) this.f8240d, (Object) hVar.f8240d) ^ true) || this.f8241e != hVar.f8241e || (c.f.b.k.a((Object) this.f8242f, (Object) hVar.f8242f) ^ true)) ? false : true;
        }
        throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.profile.ProfileVariableStored");
    }

    public int hashCode() {
        return (((this.f8240d.hashCode() * 31) + this.f8241e) * 31) + this.f8242f.hashCode();
    }

    public static final class a {

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.h$a$a, reason: collision with other inner class name */
        static final class C0219a extends c.f.b.l implements c.f.a.b<SharedPreferences.Editor, s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0219a f8243a = new C0219a();

            C0219a() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ s invoke(SharedPreferences.Editor editor) {
                a(editor);
                return s.f2131a;
            }

            public final void a(SharedPreferences.Editor editor) {
                c.f.b.k.b(editor, "receiver$0");
                editor.clear();
            }
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final h a(String str) {
            List listB;
            Integer numC;
            if (str == null || (listB = n.b((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null)) == null || listB.size() < 4) {
                return null;
            }
            String str2 = (String) listB.get(1);
            String str3 = (String) listB.get(2);
            String str4 = (String) listB.get(3);
            boolean zA = c.f.b.k.a(c.a.j.a(listB, 4), (Object) "1");
            if (str3 == null || str4 == null || (numC = n.c(str4)) == null) {
                return null;
            }
            int iIntValue = numC.intValue();
            if (str2 != null) {
                return new h(str3, iIntValue, str2, zA);
            }
            return null;
        }

        public final void a(Context context) {
            c.f.b.k.b(context, "context");
            SharedPreferences sharedPreferencesB = k.b(context);
            c.f.b.k.a((Object) sharedPreferencesB, "context.preferencesProfileVariablesStored");
            com.joaomgcd.taskerm.q.b.a(sharedPreferencesB, C0219a.f8243a);
        }
    }

    public final String d() {
        d dVarB = b();
        StringBuilder sb = new StringBuilder();
        sb.append("ImportableVariable:");
        sb.append(this.f8242f);
        sb.append(':');
        sb.append(dVarB.a());
        sb.append(':');
        sb.append(dVarB.b());
        sb.append(':');
        sb.append(this.g ? "1" : "0");
        return sb.toString();
    }
}
