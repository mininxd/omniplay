package com.joaomgcd.taskerm.signin;

import c.a.j;
import c.f.b.g;
import c.f.b.k;
import c.q;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8599a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8600b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f8601c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String[] f8602d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f8603e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f8604f;

    public a() {
        this(null, null, false, null, null, false, 63, null);
    }

    public a(String str, String str2, boolean z, String[] strArr, String str3, boolean z2) {
        k.b(strArr, "extraScopes");
        this.f8599a = str;
        this.f8600b = str2;
        this.f8601c = z;
        this.f8602d = strArr;
        this.f8603e = str3;
        this.f8604f = z2;
    }

    public /* synthetic */ a(String str, String str2, boolean z, String[] strArr, String str3, boolean z2, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? new String[]{"https://www.googleapis.com/auth/userinfo.email"} : strArr, (i & 16) != 0 ? (String) null : str3, (i & 32) == 0 ? z2 : false);
    }

    public final String b() {
        return this.f8599a;
    }

    public final String c() {
        return this.f8600b;
    }

    public final boolean d() {
        return this.f8601c;
    }

    public final String[] e() {
        return this.f8602d;
    }

    public final void a(boolean z) {
        this.f8604f = z;
    }

    public final boolean f() {
        return this.f8604f;
    }

    public final GoogleSignInOptions a() {
        GoogleSignInOptions.a aVarB = new GoogleSignInOptions.a(GoogleSignInOptions.f2692f).b();
        String str = this.f8600b;
        String str2 = str;
        if (!(str2 == null || str2.length() == 0)) {
            aVarB.a(str);
            if (this.f8601c) {
                aVarB.a(str, true);
            }
        }
        String[] strArr = this.f8602d;
        if (!(strArr.length == 0)) {
            Scope scope = new Scope((String) c.a.d.c(strArr));
            List listB = c.a.d.b(strArr, 1);
            ArrayList arrayList = new ArrayList(j.a((Iterable) listB, 10));
            Iterator it = listB.iterator();
            while (it.hasNext()) {
                arrayList.add(new Scope((String) it.next()));
            }
            Object[] array = arrayList.toArray(new Scope[0]);
            if (array != null) {
                Scope[] scopeArr = (Scope[]) array;
                aVarB.a(scope, (Scope[]) Arrays.copyOf(scopeArr, scopeArr.length));
            } else {
                throw new q("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        if (this.f8603e != null) {
            if (!(this.f8603e.length() == 0)) {
                aVarB.b(this.f8603e);
            }
        }
        GoogleSignInOptions googleSignInOptionsD = aVarB.d();
        k.a((Object) googleSignInOptionsD, "signInOptions.build()");
        return googleSignInOptionsD;
    }
}
